package com.bastug.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Invoice {
    public Invoice(){
        this.paid= false;
        this.setInvoiceDate(LocalDate.now());
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_seq")
    @SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq",initialValue = 10205325,allocationSize = 101)
    private Long invoiceId;
    private LocalDate invoiceDate;
    private BigDecimal amount;
    private Boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
}
