package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceItemRepository;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InvoiceItemServiceLayer {

    private InvoiceItemRepository invoiceItemRepo;

    @Autowired
    public InvoiceItemServiceLayer(InvoiceItemRepository invoiceItemRepo) {
        this.invoiceItemRepo = invoiceItemRepo;
    }

    @Transactional
    public InvoiceItem save(InvoiceItem invoiceItem) {
        return invoiceItemRepo.save(invoiceItem);
    }

    @Transactional
    public void update(InvoiceItem invoiceItem) {
        invoiceItemRepo.save(invoiceItem);
    }

    @Transactional
    public void deleteById(Integer invoiceItemId) {
        invoiceItemRepo.deleteById(invoiceItemId);
    }

    @Transactional
    public void deleteInvoiceItemByInvoiceId(Integer invoiceId) {
        invoiceItemRepo.deleteInvoiceItemByInvoiceId(invoiceId);
    }

    public List<InvoiceItem> findAll() {
        return invoiceItemRepo.findAll();
    }

    public InvoiceItem findById(Integer invoiceItemId) {
        return invoiceItemRepo.findById(invoiceItemId).orElse(null);
    }

    public List<InvoiceItem> findInvoiceItemByInvoiceId(Integer invoiceId) {
        return invoiceItemRepo.findInvoiceItemByInvoiceId(invoiceId);
    }
}
