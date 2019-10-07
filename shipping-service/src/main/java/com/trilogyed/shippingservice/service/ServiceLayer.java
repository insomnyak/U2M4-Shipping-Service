package com.trilogyed.shippingservice.service;

import com.insomnyak.util.MapClasses;
import com.trilogyed.shippingservice.domain.Invoice;
import com.trilogyed.shippingservice.domain.InvoiceItem;
import com.trilogyed.shippingservice.domain.InvoiceViewModel;
import com.trilogyed.shippingservice.util.feign.InvoiceItemServiceClient;
import com.trilogyed.shippingservice.util.feign.InvoiceServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ServiceLayer {
    private InvoiceItemServiceClient invoiceItemClient;
    private InvoiceServiceClient invoiceClient;

    @Autowired
    public ServiceLayer(InvoiceItemServiceClient invoiceItemClient,
                        InvoiceServiceClient invoiceClient) {
        this.invoiceItemClient = invoiceItemClient;
        this.invoiceClient = invoiceClient;
    }

    public InvoiceViewModel saveInvoice(InvoiceViewModel ivm)
    {
        calculateCostsAndSurcharge(ivm);
        Invoice invoice = build(ivm);
        invoice=invoiceClient.createInvoice(invoice);
        List<InvoiceItem> invoiceItems = ivm.getInvoiceItems();
        Invoice finalInvoice = invoice;
        invoiceItems.stream()
                .forEach(invoiceItem -> {
                    invoiceItem.setInvoiceId(finalInvoice.getInvoiceId());
                    InvoiceItem invoiceItem1 = invoiceItemClient.createInvoiceItem(invoiceItem);
                    invoiceItem.setInvoiceItemId(invoiceItem1.getInvoiceItemId());
                });
        ivm.setInvoiceId(invoice.getInvoiceId());
        return ivm;

    }

    public void updateInvoice(InvoiceViewModel ivm)
    {
        Invoice invoice = build(ivm);
        invoiceClient.updateInvoice(invoice);
        List<InvoiceItem> invoiceItems = ivm.getInvoiceItems();
        invoiceItems.stream()
                .forEach(invoiceItem -> {
                    invoiceItemClient.deleteInvoiceItemById(invoice.getInvoiceId());
                });
        invoiceItems = invoiceItemClient.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
        ivm.setInvoiceItems(invoiceItems);
    }

    public void deleteInvoice(Integer invoiceId)
    {
        try{
            invoiceClient.deleteInvoiceByInvoiceId(invoiceId);
        }catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public InvoiceViewModel getInvoice(Integer invoiceId)
    {
        try
        {
            Invoice invoice = invoiceClient.getInvoiceByInvoiceId(invoiceId);
            
        }
    }


    public Invoice build(InvoiceViewModel ivm) {
        return (new MapClasses<>(ivm, Invoice.class)).mapFirstToSecond(false);
    }

    public void calculateCostsAndSurcharge(InvoiceViewModel ivm)
    {
        List<InvoiceItem> invoiceItems = ivm.getInvoiceItems();
        AtomicReference<Integer> weight = new AtomicReference<>(0);
        ivm.setTotalCost(new BigDecimal("0.00"));
        invoiceItems.stream()
                .forEach(invoiceItem -> {
                    weight.updateAndGet(v -> v + invoiceItem.getWeight());
                    switch (ivm.getShiptoZip().charAt(0))
                    {
                        case '0':
                        case '1':
                        case '2':
                            invoiceItem.setShipCost(new BigDecimal("9.99"));
                            break;
                        case '3':
                            invoiceItem.setShipCost(new BigDecimal("14.99"));
                            break;
                        case '4':
                        case'5':
                        case '6':
                            invoiceItem.setShipCost(new BigDecimal("19.99"));
                            break;
                        case '7':
                        case '8':
                        case '9':
                            invoiceItem.setShipCost(new BigDecimal("24.99"));
                            break;
                        default:
                            throw new IllegalArgumentException ("Invalid ZipCode");
                    }
                    ivm.setTotalCost(ivm.getTotalCost().add(invoiceItem.getShipCost()));
                });
        if(weight.get()>35)
        {
            ivm.setSurcharge(new BigDecimal("50.00"));
        } else if(weight.get()>25)
        {
            ivm.setSurcharge(new BigDecimal("19.99"));
        }else if(weight.get()>17)
        {
            ivm.setSurcharge(new BigDecimal("12.50"));
        }else if(weight.get()>10)
        {
            ivm.setSurcharge(new BigDecimal("8.50"));
        }else
            ivm.setSurcharge(new BigDecimal("0.00"));


        ivm.setSalesTax(ivm.getTotalCost().add(ivm.getSurcharge()).multiply(new BigDecimal("0.072")));
        ivm.setTotalCost(ivm.getTotalCost().add(ivm.getSurcharge()).add(ivm.getSalesTax()));
    }



}
