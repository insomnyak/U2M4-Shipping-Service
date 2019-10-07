package com.trilogyed.shippingservice.domain;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {
    private Integer invoiceId;

    @NotNull
    @Positive
    private Integer customerId;

    @NotBlank
    @Size(min=5,max = 5)
    private String shiptoZip;

    @NotNull
    @PastOrPresent
    private LocalDate purchaseDate;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal totalCost;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal salesTax;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal surcharge;

    @NotNull
    @Valid
    private List<InvoiceItem> invoiceItems;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getShiptoZip() {
        return shiptoZip;
    }

    public void setShiptoZip(String shiptoZip) {
        this.shiptoZip = shiptoZip;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(BigDecimal surcharge) {
        this.surcharge = surcharge;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceViewModel)) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(getInvoiceId(), that.getInvoiceId()) &&
                Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getShiptoZip(), that.getShiptoZip()) &&
                Objects.equals(getPurchaseDate(), that.getPurchaseDate()) &&
                Objects.equals(getTotalCost(), that.getTotalCost()) &&
                Objects.equals(getSalesTax(), that.getSalesTax()) &&
                Objects.equals(getSurcharge(), that.getSurcharge()) &&
                Objects.equals(getInvoiceItems(), that.getInvoiceItems());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getInvoiceId(), getCustomerId(), getShiptoZip(), getPurchaseDate(), getTotalCost(), getSalesTax(),
                        getSurcharge(), getInvoiceItems());
    }
}
