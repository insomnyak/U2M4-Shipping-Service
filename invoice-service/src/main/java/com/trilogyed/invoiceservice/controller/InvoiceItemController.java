package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.dao.InvoiceItemRepository;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/invoiceItem")
public class InvoiceItemController {

    @Autowired
    InvoiceItemRepository invoiceItemRepo;

    @Transactional
    @PostMapping
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        return invoiceItemRepo.save(invoiceItem);
    }

    @Transactional
    @PutMapping
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        invoiceItemRepo.save(invoiceItem);
    }

    @Transactional
    @DeleteMapping("/{invoiceItemId}")
    public void deleteInvoiceItemById(@PathVariable Integer invoiceItemId) {
        invoiceItemRepo.deleteById(invoiceItemId);
    }

    @GetMapping
    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemRepo.findAll();
    }

    @GetMapping("/{invoiceItemId}")
    public InvoiceItem getInvoiceItemById(@PathVariable Integer invoiceItemId) {
        return invoiceItemRepo.findById(invoiceItemId).orElse(null);
    }

    @GetMapping("/invoice/{invoiceId}")
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(@PathVariable Integer invoiceId) {
        return invoiceItemRepo.findInvoiceItemByInvoiceId(invoiceId);
    }
}
