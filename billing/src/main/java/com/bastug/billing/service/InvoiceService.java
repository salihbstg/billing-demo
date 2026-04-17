package com.bastug.billing.service;

import com.bastug.billing.dtos.InvoiceInputDto;
import com.bastug.billing.dtos.InvoiceOutputDto;
import com.bastug.billing.entity.Customer;
import com.bastug.billing.entity.Invoice;
import com.bastug.billing.mapper.InvoicesMapper;
import com.bastug.billing.repository.CustomerRepository;
import com.bastug.billing.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoicesMapper invoicesMapper;
    private final CustomerRepository customerRepository;

    //Fatura oluşturma
    public InvoiceOutputDto createInvoice(InvoiceInputDto invoiceInputDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(invoiceInputDto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Invoice invoice=invoicesMapper.invoiceInputDtoToInvoice(invoiceInputDto);
            invoice.setCustomer(customer);
            return invoicesMapper.invoiceToInvoiceOutputDto(invoiceRepository.save(invoice),customer);
        }
        else
            throw new RuntimeException("Müşteri bulunamadı!");
    }
}
