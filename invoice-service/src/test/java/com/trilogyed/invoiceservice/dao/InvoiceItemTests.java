package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceItemTests {

    @Autowired
    InvoiceItemRepository invoiceItemRepo;
    @Autowired
    InvoiceRepository invoiceRepo;

    private Invoice invoice1;

    @Before
    public void setUp() throws Exception
    {
        invoiceItemRepo.deleteAll();
        invoiceRepo.deleteAll();

        /* ***** Creating invoice *****/
        invoice1 = new Invoice();
        invoice1.setCustomerId(10);
        invoice1.setShiptoZip("10019");
        invoice1.setPurchaseDate(LocalDate.now());
        invoice1.setTotalCost(new BigDecimal("50.02"));
        invoice1.setSalesTax(new BigDecimal("2.01"));
        invoice1.setSurcharge(new BigDecimal("8.50"));
        invoiceRepo.save(invoice1);
    }

    @Test
    public void addGetDeleteFindAll()
    {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice1.getInvoiceId());
        invoiceItem.setItemName("Pizza");
        invoiceItem.setItemDescription("Vegetables");
        invoiceItem.setWeight(2);
        invoiceItem.setShipCost(new BigDecimal("5.00"));

        invoiceItemRepo.save(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemRepo.findById(invoiceItem.getInvoiceItemId()).orElse(null);

        assertEquals(invoiceItem,invoiceItem1);

        List<InvoiceItem> invoiceItems = invoiceItemRepo.findAll();
        assertEquals(invoiceItems.size(),1);

        invoiceItems = invoiceItemRepo.findInvoiceItemByInvoiceId(invoice1.getInvoiceId());
        assertEquals(invoiceItems.size(),1);

        invoiceItem.setItemName("Deluxe Pizza");
        invoiceItemRepo.save(invoiceItem);

        invoiceItem1 = invoiceItemRepo.findById(invoiceItem.getInvoiceItemId()).orElse(null);
        assertEquals(invoiceItem,invoiceItem1);

        invoiceItemRepo.delete(invoiceItem);

        invoiceItem1 = invoiceItemRepo.findById(invoiceItem.getInvoiceItemId()).orElse(null);
        assertNull(invoiceItem1);
    }

    @Test
    public void findConstraints()
    {
        try
        {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoice1.getInvoiceId());
            invoiceItem.setItemName("PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza");
            invoiceItem.setItemDescription("Vegetables");
            invoiceItem.setWeight(2);
            invoiceItem.setShipCost(new BigDecimal("5.00"));

            invoiceItemRepo.save(invoiceItem);
        }catch(Exception e)
        {

        }

        try
        {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoice1.getInvoiceId());
            invoiceItem.setItemName("Pizza");
            invoiceItem.setWeight(2);
            invoiceItem.setShipCost(new BigDecimal("5.00"));

            invoiceItemRepo.save(invoiceItem);
        }catch(Exception e)
        {

        }

        try
        {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoice1.getInvoiceId());
            invoiceItem.setItemName("Pizza");
            invoiceItem.setItemDescription("Vegetables");
            invoiceItem.setShipCost(new BigDecimal("5.00"));

            invoiceItemRepo.save(invoiceItem);
        }catch(Exception e)
        {

        }

        try
        {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoice1.getInvoiceId());
            invoiceItem.setItemName("Pizza");
            invoiceItem.setItemDescription("Vegetables");
            invoiceItem.setWeight(2);
            invoiceItem.setShipCost(new BigDecimal("5.0034"));

            invoiceItemRepo.save(invoiceItem);
        }catch(Exception e)
        {

        }

        List<InvoiceItem> invoices = invoiceItemRepo.findAll();
        assertEquals(invoices.size(),0);
    }

}
