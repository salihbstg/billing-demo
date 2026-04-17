package com.bastug.billing.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputInvoiceDto {
    private Long invoiceId;
    private Long customerId;

    private BigDecimal consumption; //m^3
    private BigDecimal unitPrice;
    private BigDecimal vatRate;
}
