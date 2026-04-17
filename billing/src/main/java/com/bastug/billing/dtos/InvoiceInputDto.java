package com.bastug.billing.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceInputDto {
    private Long invoiceId;
    private BigDecimal amount;
    private Long customerId;
}
