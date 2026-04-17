package com.bastug.billing.mapper;

import com.bastug.billing.dtos.InputInvoiceDto;
import com.bastug.billing.dtos.OutputInvoiceDto;
import com.bastug.billing.entity.Customer;
import com.bastug.billing.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Component
public class InvoicesMapper {

    //Fatura oluştururken dönüşüm
    public Invoice invoiceInputDtoToInvoice(InputInvoiceDto inputInvoiceDto) {
        Invoice invoice = new Invoice();
        BigDecimal bigDecimal=inputInvoiceDto.getConsumption().multiply(
                inputInvoiceDto.getUnitPrice().multiply(
                        inputInvoiceDto.getVatRate().add(BigDecimal.ONE)
                )
                );
        invoice.setAmount(bigDecimal);
        invoice.setConsumption(inputInvoiceDto.getConsumption());
        invoice.setVatRate(inputInvoiceDto.getVatRate());
        invoice.setUnitPrice(inputInvoiceDto.getUnitPrice());
        return invoice;
    }

    //Fatura çıktısı alırken dönüşüm
    public OutputInvoiceDto invoiceToInvoiceOutputDto(Invoice invoice, Customer customer) {
        OutputInvoiceDto outputInvoiceDto = new OutputInvoiceDto();
        outputInvoiceDto.setInvoiceDate(invoice.getInvoiceDate());
        outputInvoiceDto.setPaid(invoice.getPaid());
        outputInvoiceDto.setAmount(invoice.getAmount());
        outputInvoiceDto.setConsumption(invoice.getConsumption());
        outputInvoiceDto.setVatRate(invoice.getVatRate());
        outputInvoiceDto.setUnitPrice(invoice.getUnitPrice());
        outputInvoiceDto.setCustomerName(customer.getName());
        outputInvoiceDto.setCustomerLastName(customer.getLastName());
        outputInvoiceDto.setCustomerNationalNumber(customer.getNationalId());
        outputInvoiceDto.setInvoiceId(invoice.getInvoiceId());
        return outputInvoiceDto;
    }
}
