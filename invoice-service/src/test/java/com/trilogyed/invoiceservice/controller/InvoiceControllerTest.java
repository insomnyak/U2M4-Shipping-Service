package com.trilogyed.invoiceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.service.InvoiceItemServiceLayer;
import com.trilogyed.invoiceservice.service.InvoiceServiceLayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    InvoiceServiceLayer invoiceSl;

    @MockBean
    InvoiceItemServiceLayer invoiceItemSl;

    private Invoice invoice1a;
    private Invoice invoice1b;

    private Invoice invoice2a;
    private Invoice invoice2b;
    private Invoice invoice2c;

    private List<Invoice> invoiceList1;
    private List<Invoice> invoiceList2;

    private InvoiceItem invoiceItem1a;
    private InvoiceItem invoiceItem1b;

    private InvoiceItem invoiceItem2a;
    private InvoiceItem invoiceItem2b;
    private InvoiceItem invoiceItem2c;

    private Set<InvoiceItem> invoiceItemList1;
    private Set<InvoiceItem> invoiceItemList2;

    @Before
    public void setUp() throws Exception {
        constructSampleData();

    }

    @After
    public void tearDown() throws Exception {
        destroySampleData();
    }

    public void constructSampleData() {
        invoice1a = new Invoice();
        invoice1b = new Invoice();

        invoice2a = new Invoice();
        invoice2b = new Invoice();
        invoice2c = new Invoice();

        invoiceList1 = new ArrayList<>();
        invoiceList2 = new ArrayList<>();

        invoiceItem1a = new InvoiceItem();
        invoiceItem1b = new InvoiceItem();

        invoiceItem2a = new InvoiceItem();
        invoiceItem2b = new InvoiceItem();
        invoiceItem2c = new InvoiceItem();

        invoiceItemList1 = new HashSet<>();
        invoiceItemList2 = new HashSet<>();

        // Invoice 1 received
        invoiceItem1a.setWeight(2);
        invoiceItem1a.setItemName("pizza");
        invoiceItem1a.setItemDescription("meat lover");

        Set<InvoiceItem> tempInvoiceItemList = new HashSet<>();
        tempInvoiceItemList.add(invoiceItem1a);

        invoice1a.setCustomerId(10);
        invoice1a.setShiptoZip("10019");
        invoice1a.setPurchaseDate(LocalDate.now());
        invoice1a.setInvoiceItems(tempInvoiceItemList);

        // Invoice 1 sent
        invoiceItem1b.setWeight(2);
        invoiceItem1b.setItemName("pizza");
        invoiceItem1b.setItemDescription("meat lover");
        invoiceItem1b.setInvoiceId(1);
        invoiceItem1b.setInvoiceItemId(10);
        invoiceItem1b.setShipCost(new BigDecimal("10.00"));

        invoiceItemList1.add(invoiceItem1b);

        invoice1b.setCustomerId(10);
        invoice1b.setShiptoZip("10019");
        invoice1b.setPurchaseDate(LocalDate.now());
        invoice1b.setInvoiceItems(tempInvoiceItemList);
        invoice1b.setInvoiceId(1);
        invoice1b.setSurcharge(new BigDecimal("5.15"));
        invoice1b.setSalesTax(new BigDecimal("2.00"));
        invoice1b.setTotalCost(new BigDecimal("25.00"));
        invoice1b.setInvoiceItems(invoiceItemList1);

        invoiceList1.add(invoice1b);

        when(invoiceSl.save(invoice1a)).thenReturn(invoice1b);
        when(invoiceSl.findById(1)).thenReturn(invoice1b);
        when(invoiceSl.findAll()).thenReturn(invoiceList1);
        when(invoiceSl.findInvoiceByCustomerId(10)).thenReturn(invoiceList1);

        // invoice


        doNothing().when(invoiceSl).update(invoice2c);

    }

    public void destroySampleData() {
        invoice1a = null;
        invoice1b = null;

        invoice2a = null;
        invoice2b = null;
        invoice2c = null;

        invoiceList1 = null;
        invoiceList2 = null;

        invoiceItem1a = null;
        invoiceItem1b = null;

        invoiceItem2a = null;
        invoiceItem2b = null;
        invoiceItem2c = null;

        invoiceItemList1 = null;
        invoiceItemList2 = null;
    }

    @Test
    public void createInvoice() {
    }

    @Test
    public void updateInvoice() {
    }

    @Test
    public void deleteInvoiceByInvoiceId() {
    }

    @Test
    public void getAllInvoice() {
    }

    @Test
    public void getInvoiceByInvoiceId() {
    }

    @Test
    public void getInvoicesByCustomerId() {
    }
}