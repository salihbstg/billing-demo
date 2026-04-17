package com.bastug.billing.service;

import com.bastug.billing.dtos.InputInvoiceDto;
import com.bastug.billing.dtos.OutputInvoiceDto;
import com.bastug.billing.entity.Customer;
import com.bastug.billing.entity.Invoice;
import com.bastug.billing.exception.ApplicationExceptionImpl;
import com.bastug.billing.mapper.InvoicesMapper;
import com.bastug.billing.repository.CustomerRepository;
import com.bastug.billing.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoicesMapper invoicesMapper;
    private final CustomerRepository customerRepository;

    //Fatura oluşturma
    public OutputInvoiceDto createInvoice(InputInvoiceDto inputInvoiceDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(inputInvoiceDto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Invoice invoice=invoicesMapper.invoiceInputDtoToInvoice(inputInvoiceDto);
            invoice.setCustomer(customer);
            return invoicesMapper.invoiceToInvoiceOutputDto(invoiceRepository.save(invoice),customer);
        }
        else
            throw new RuntimeException("Müşteri bulunamadı!");
    }

    //Tc ile fatura görüntüleme
    public List<OutputInvoiceDto> getInvoice(String customerNationalId) {
        Optional<Customer> optionalCustomer=customerRepository.findByNationalId(customerNationalId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            List<Invoice> invoices=invoiceRepository.findByCustomer(customer);
            List<OutputInvoiceDto> outputInvoiceDtoList=new ArrayList<>();
            for (Invoice invoice:invoices) {
                outputInvoiceDtoList.add(invoicesMapper.invoiceToInvoiceOutputDto(invoice,customer));
            }
            return outputInvoiceDtoList;
        }
        else
            throw new ApplicationExceptionImpl("Müşteri ya da fatura bulunamadı!");
    }

    //Fatura ödeme
    public Boolean pay(Long invoiceId) {
        Optional<Invoice> optionalInvoice=invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice=optionalInvoice.get();
            invoice.setPaid(true);
            invoiceRepository.save(invoice);
            return true;
        }
        else
            throw new ApplicationExceptionImpl("Fatura bulunamadı!");
    }
}
