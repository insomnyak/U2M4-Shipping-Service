package com.trilogyed.shippingservice.util.feign;

import com.trilogyed.shippingservice.domain.Invoice;
import com.trilogyed.shippingservice.domain.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service", contextId = "invoice-service")
@RequestMapping("/invoices")
public interface InvoiceServiceClient {

    @PostMapping
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @PutMapping
    public void updateInvoice(@RequestBody @Valid Invoice invoice);

    @DeleteMapping("/{invoiceId}")
    public void deleteInvoiceByInvoiceId(@PathVariable Integer invoiceId);

    @GetMapping
    public List<Invoice> getAllInvoice();

    @GetMapping("/{invoiceId}")
    public Invoice getInvoiceByInvoiceId(@PathVariable Integer invoiceId);

    @GetMapping("/customer/{customerId}")
    public List<Invoice> getInvoicesByCustomerId(@PathVariable Integer customerId);
}
