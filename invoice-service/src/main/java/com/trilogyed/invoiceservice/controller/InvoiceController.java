package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.dao.InvoiceItemRepository;
import com.trilogyed.invoiceservice.dao.InvoiceRepository;
import com.trilogyed.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepo;

    @Transactional
    @PostMapping
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    @Transactional
    @PutMapping
    public void updateInvoice(@RequestBody @Valid Invoice invoice) {
        invoiceRepo.save(invoice);
    }

    @Transactional
    @DeleteMapping("/{invoiceId}")
    public void deleteInvoiceByInvoiceId(@PathVariable Integer invoiceId) {
        invoiceRepo.deleteById(invoiceId);
    }

    @GetMapping
    public List<Invoice> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    @GetMapping("/{invoiceId}")
    public Invoice getInvoiceByInvoiceId(@PathVariable Integer invoiceId) {
        return invoiceRepo.findById(invoiceId).orElse(null);
    }

    @GetMapping("/customer/{customerId}")
    public List<Invoice> getInvoicesByCustomerId(@PathVariable Integer customerId) {
        return invoiceRepo.findInvoiceByCustomerId(customerId);
    }
}
