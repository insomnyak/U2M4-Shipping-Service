package com.trilogyed.shippingservice.util.feign;

import com.trilogyed.shippingservice.domain.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
@RequestMapping("/invoiceItem")
public interface InvoiceItemServiceClient {
    @PostMapping
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @PutMapping
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @DeleteMapping("/{invoiceItemId}")
    public void deleteInvoiceItemById(@PathVariable Integer invoiceItemId);

    @GetMapping
    public List<InvoiceItem> getAllInvoiceItems();

    @GetMapping("/{invoiceItemId}")
    public InvoiceItem getInvoiceItemById(@PathVariable Integer invoiceItemId);

    @GetMapping("/invoice/{invoiceId}")
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(@PathVariable Integer invoiceId);
}