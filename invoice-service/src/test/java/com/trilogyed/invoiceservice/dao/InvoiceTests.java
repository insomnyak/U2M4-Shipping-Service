package com.trilogyed.invoiceservice.dao;

import com.netflix.discovery.converters.Auto;
import com.trilogyed.invoiceservice.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceTests {

    @Autowired
    InvoiceRepository invoiceRepo;
    @Autowired
    InvoiceItemRepository invoiceItemRepo;

    @Before
    public void setUp() throws Exception
    {
        invoiceItemRepo.deleteAll();
        invoiceRepo.deleteAll();
    }

    @Test
    public void addGetDelete()
    {
        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(10);
        invoice1.setShiptoZip("10019");
        invoice1.setPurchaseDate(LocalDate.now());
        invoice1.setTotalCost(new BigDecimal("50.02"));
        invoice1.setSalesTax(new BigDecimal("2.01"));
        invoice1.setSurcharge(new BigDecimal("8.50"));

        invoiceRepo.save(invoice1);

        Invoice invoice2 = invoiceRepo.findById(invoice1.getInvoiceId()).orElse(null);
        invoice2.setPurchaseDate(invoice2.getPurchaseDate().plusDays(1L));
        assertEquals(invoice1,invoice2);

        List<Invoice> invoices= invoiceRepo.findAll();
        assertEquals(invoices.size(),1);

        invoices= invoiceRepo.findInvoiceByCustomerId(10);
        assertEquals(invoices.size(),1);

        invoiceRepo.deleteById(invoice1.getInvoiceId());
        invoice2 = invoiceRepo.findById(invoice1.getInvoiceId()).orElse(null);
        assertNull(invoice2);
    }

    @Test
    public void updateInvoice()
    {
        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(10);
        invoice1.setShiptoZip("10019");
        invoice1.setPurchaseDate(LocalDate.now());
        invoice1.setTotalCost(new BigDecimal("50.02"));
        invoice1.setSalesTax(new BigDecimal("2.01"));
        invoice1.setSurcharge(new BigDecimal("8.50"));

        invoiceRepo.save(invoice1);

        invoice1.setTotalCost(new BigDecimal("100.02"));

        invoiceRepo.save(invoice1);

        Invoice invoice2 = invoiceRepo.findById(invoice1.getInvoiceId()).orElse(null);
        invoice2.setPurchaseDate(invoice2.getPurchaseDate().plusDays(1L));
        assertEquals(invoice1,invoice2);
    }

    @Test
    public void findConstraints()
    {
        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(10);
            invoice1.setShiptoZip("100134");
            invoice1.setPurchaseDate(LocalDate.now());
            invoice1.setTotalCost(new BigDecimal("50.02"));
            invoice1.setSalesTax(new BigDecimal("2.01"));
            invoice1.setSurcharge(new BigDecimal("8.50"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(10);
            invoice1.setShiptoZip("10019");
            invoice1.setTotalCost(new BigDecimal("50.02"));
            invoice1.setSalesTax(new BigDecimal("2.01"));
            invoice1.setSurcharge(new BigDecimal("8.50"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(10);
            invoice1.setShiptoZip("10134");
            invoice1.setPurchaseDate(LocalDate.now());
            invoice1.setSalesTax(new BigDecimal("2.01"));
            invoice1.setSurcharge(new BigDecimal("8.50"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(10);
            invoice1.setShiptoZip("10014");
            invoice1.setPurchaseDate(LocalDate.now());
            invoice1.setTotalCost(new BigDecimal("50.02"));
            invoice1.setSurcharge(new BigDecimal("8.50"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(10);
            invoice1.setShiptoZip("10034");
            invoice1.setPurchaseDate(LocalDate.now());
            invoice1.setTotalCost(new BigDecimal("50.02"));
            invoice1.setSalesTax(new BigDecimal("2.01"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(10);
            invoice1.setShiptoZip("10014");
            invoice1.setPurchaseDate(LocalDate.now());
            invoice1.setTotalCost(new BigDecimal("50.02"));
            invoice1.setSalesTax(new BigDecimal("2.01"));
            invoice1.setSurcharge(new BigDecimal("8.5660"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        try{
            Invoice invoice1 = new Invoice();
            invoice1.setCustomerId(-10);
            invoice1.setShiptoZip("10134");
            invoice1.setPurchaseDate(LocalDate.now());
            invoice1.setTotalCost(new BigDecimal("50.02"));
            invoice1.setSalesTax(new BigDecimal("2.01"));
            invoice1.setSurcharge(new BigDecimal("8.50"));

            invoiceRepo.save(invoice1);
        }catch(Exception e)
        {

        }

        List<Invoice> invoices = invoiceRepo.findAll();
        assertEquals(invoices.size(),0);

    }

}
