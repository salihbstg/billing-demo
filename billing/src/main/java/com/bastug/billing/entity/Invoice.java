package com.bastug.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @PrePersist
    public void prePersist() {
        this.dueDate = LocalDateTime.now().plusDays(30);
        this.invoiceDate = LocalDate.now();
        this.paid = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_seq")
    @SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq", initialValue = 10205325, allocationSize = 101)
    private Long invoiceId;

    private LocalDate invoiceDate;
    private LocalDateTime dueDate;
    private Boolean paid;

    private BigDecimal consumption; //m^3
    private BigDecimal unitPrice;
    private BigDecimal vatRate;
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
}
