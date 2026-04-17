package com.bastug.billing.mapper;

import com.bastug.billing.dtos.InvoiceInputDto;
import com.bastug.billing.dtos.InvoiceOutputDto;
import com.bastug.billing.entity.Customer;
import com.bastug.billing.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Component
public class InvoicesMapper {

    //Fatura oluştururken dönüşüm
    public Invoice invoiceInputDtoToInvoice(InvoiceInputDto invoiceInputDto) {
        Invoice invoice = new Invoice();
        invoice.setAmount(invoiceInputDto.getAmount());
        return invoice;
    }

    //Fatura çıktısı alırken dönüşüm
    public InvoiceOutputDto invoiceToInvoiceOutputDto(Invoice invoice, Customer customer) {
        InvoiceOutputDto invoiceOutputDto = new InvoiceOutputDto();
        invoiceOutputDto.setInvoiceDate(invoice.getInvoiceDate());
        invoiceOutputDto.setPaid(invoice.getPaid());
        invoiceOutputDto.setAmount(invoice.getAmount());
        invoiceOutputDto.setCustomerName(customer.getName());
        invoiceOutputDto.setCustomerLastName(customer.getLastName());
        invoiceOutputDto.setCustomerNationalNumber(customer.getNationalId());
        invoiceOutputDto.setInvoiceId(invoice.getInvoiceId());
        return invoiceOutputDto;
    }
}
