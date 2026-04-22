package com.bastug.billing.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInputDto {
    private Long invoiceId;
    private String cardNumber;
    private String cardExpiryDate;
    private String ccv;
}
