package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer sl;

    @PostMapping
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return sl.save(invoice);
    }

    @PutMapping
    public void updateInvoice(@RequestBody @Valid Invoice invoice) {
        sl.update(invoice);
    }

    @DeleteMapping("/{invoiceId}")
    public void deleteInvoiceByInvoiceId(@PathVariable Integer invoiceId) {
        sl.deleteById(invoiceId);
    }

    @GetMapping
    public List<Invoice> getAllInvoice() {
        return sl.findAll();
    }

    @GetMapping("/{invoiceId}")
    public Invoice getInvoiceByInvoiceId(@PathVariable Integer invoiceId) {
        return sl.findById(invoiceId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Invoice> getInvoicesByCustomerId(@PathVariable Integer customerId) {
        return sl.findInvoiceByCustomerId(customerId);
    }
}
