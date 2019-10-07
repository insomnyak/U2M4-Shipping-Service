package com.trilogyed.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId", fetch = FetchType.EAGER)
    private Set<InvoiceItem> invoiceItems = new HashSet<>();


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

    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(getInvoiceId(), invoice.getInvoiceId()) &&
                Objects.equals(getCustomerId(), invoice.getCustomerId()) &&
                Objects.equals(getShiptoZip(), invoice.getShiptoZip()) &&
                Objects.equals(getPurchaseDate(), invoice.getPurchaseDate()) &&
                Objects.equals(getTotalCost(), invoice.getTotalCost()) &&
                Objects.equals(getSalesTax(), invoice.getSalesTax()) &&
                Objects.equals(getSurcharge(), invoice.getSurcharge());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getInvoiceId(), getCustomerId(), getShiptoZip(), getPurchaseDate(), getTotalCost(), getSalesTax(),
                        getSurcharge());
    }
}
