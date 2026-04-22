package com.bastug.billing.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOutputDto {
    private Boolean paymentStatus;
    private String paymentMessage;
}
