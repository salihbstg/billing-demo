package com.bastug.billing.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputInvoiceDto {

    private Long invoiceId;
    private LocalDate invoiceDate;
    private LocalDateTime dueDate;
    private Boolean paid;

    private BigDecimal consumption; //m^3
    private BigDecimal unitPrice;
    private BigDecimal vatRate;
    private BigDecimal amount;

    private String customerName;
    private String customerLastName;
    private String customerNationalNumber;
}
