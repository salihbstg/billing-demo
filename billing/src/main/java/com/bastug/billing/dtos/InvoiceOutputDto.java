package com.bastug.billing.dtos;

import com.bastug.billing.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceOutputDto {
    private Long invoiceId;
    private LocalDate invoiceDate;
    private BigDecimal amount;
    private Boolean paid;
    private String customerName;
    private String customerLastName;
    private String customerNationalNumber;
}
