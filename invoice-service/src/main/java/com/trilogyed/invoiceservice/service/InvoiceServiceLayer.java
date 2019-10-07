package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceItemRepository;
import com.trilogyed.invoiceservice.dao.InvoiceRepository;
import com.trilogyed.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InvoiceServiceLayer {

    private InvoiceRepository invoiceRepo;
    private InvoiceItemRepository invoiceItemRepo;

    @Autowired
    public InvoiceServiceLayer(InvoiceRepository invoiceRepo,
                               InvoiceItemRepository invoiceItemRepo) {
        this.invoiceRepo = invoiceRepo;
        this.invoiceItemRepo = invoiceItemRepo;
    }

    @Transactional
    public Invoice save(Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    @Transactional
    public void update(Invoice invoice) {
        invoiceRepo.save(invoice);
    }

    @Transactional
    public void deleteById(Integer invoiceId) {
        invoiceRepo.deleteById(invoiceId);
    }

    public List<Invoice> findAll() {
        return invoiceRepo.findAll();
    }

    public Invoice findById(Integer invoiceId) {
        return invoiceRepo.findById(invoiceId).orElse(null);
    }

    public List<Invoice> findInvoiceByCustomerId(Integer customerId) {
        return invoiceRepo.findInvoiceByCustomerId(customerId);
    }
}
