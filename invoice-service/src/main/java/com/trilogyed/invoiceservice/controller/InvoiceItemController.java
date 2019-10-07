package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.dao.InvoiceItemRepository;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.service.InvoiceItemServiceLayer;
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
    InvoiceItemServiceLayer sl;

    @PostMapping
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        return sl.save(invoiceItem);
    }

    @PutMapping
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        sl.update(invoiceItem);
    }

    @DeleteMapping("/{invoiceItemId}")
    public void deleteInvoiceItemById(@PathVariable Integer invoiceItemId) {
        sl.deleteById(invoiceItemId);
    }

    @Transactional
    @DeleteMapping("/invoice/{invoiceId}")
    public void deleteInvoiceItemByInvoiceId(@PathVariable Integer invoiceId) {
        sl.deleteInvoiceItemByInvoiceId(invoiceId);
    }

    @GetMapping
    public List<InvoiceItem> getAllInvoiceItems() {
        return sl.findAll();
    }

    @GetMapping("/{invoiceItemId}")
    public InvoiceItem getInvoiceItemById(@PathVariable Integer invoiceItemId) {
        return sl.findById(invoiceItemId);
    }

    @GetMapping("/invoice/{invoiceId}")
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(@PathVariable Integer invoiceId) {
        return sl.findInvoiceItemByInvoiceId(invoiceId);
    }
}
