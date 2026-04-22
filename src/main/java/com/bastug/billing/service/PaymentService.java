package com.bastug.billing.service;

import com.bastug.billing.dtos.PaymentInputDto;
import com.bastug.billing.dtos.PaymentOutputDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class PaymentService {
    public PaymentOutputDto payRequest(PaymentInputDto paymentInputDto){
        PaymentOutputDto paymentOutputDto = new PaymentOutputDto();
        int a= ThreadLocalRandom.current().nextInt(10);
        paymentOutputDto.setPaymentStatus(a < 7)/*%70 success*/;
        return paymentOutputDto;
    }
}
