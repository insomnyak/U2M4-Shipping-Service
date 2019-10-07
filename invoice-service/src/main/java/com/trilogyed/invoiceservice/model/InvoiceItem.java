package com.trilogyed.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice_item")
public class InvoiceItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer invoiceItemId;

    @NotNull
    private Integer invoiceId;

    @NotBlank
    @Size(max = 50)
    private String itemName;

    @NotBlank
    @Size(max = 255)
    private String itemDescription;

    @NotNull
    private Integer weight;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal shipCost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiceId", updatable = false, insertable = false)
    private Invoice invoice;

    public Integer getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Integer invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceItem)) return false;
        InvoiceItem that = (InvoiceItem) o;
        return Objects.equals(getInvoiceItemId(), that.getInvoiceItemId()) &&
                Objects.equals(getInvoiceId(), that.getInvoiceId()) &&
                Objects.equals(getItemName(), that.getItemName()) &&
                Objects.equals(getItemDescription(), that.getItemDescription()) &&
                Objects.equals(getWeight(), that.getWeight()) &&
                Objects.equals(getShipCost(), that.getShipCost());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getInvoiceItemId(), getInvoiceId(), getItemName(), getItemDescription(), getWeight(),
                        getShipCost());
    }
}
