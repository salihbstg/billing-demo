package com.bastug.billing.service;

import com.bastug.billing.dtos.InputInvoiceDto;
import com.bastug.billing.dtos.OutputInvoiceDto;
import com.bastug.billing.dtos.PaymentInputDto;
import com.bastug.billing.dtos.PaymentOutputDto;
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
    private final PaymentService paymentService;
    //Fatura oluşturma
    public OutputInvoiceDto createInvoice(InputInvoiceDto inputInvoiceDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(inputInvoiceDto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Invoice invoice = invoicesMapper.invoiceInputDtoToInvoice(inputInvoiceDto);
            invoice.setCustomer(customer);
            return invoicesMapper.invoiceToInvoiceOutputDto(invoiceRepository.save(invoice), customer);
        } else
            throw new RuntimeException("Müşteri bulunamadı!");
    }

    //Tc ile fatura görüntüleme
    public List<OutputInvoiceDto> getAllInvoiceByNationalNumber(String customerNationalId) {

        Customer customer = optionalCustomerByNationalId(customerNationalId);
        List<Invoice> invoices = invoiceRepository.findByCustomer(customer);
        List<OutputInvoiceDto> outputInvoiceDtoList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            outputInvoiceDtoList.add(invoicesMapper.invoiceToInvoiceOutputDto(invoice, customer));
        }

        return outputInvoiceDtoList;
    }

    //Ödenmemiş/Ödenmemiş fatura listeleme
    public List<OutputInvoiceDto> getInvoicesByPaymentStatus(String customerNationalId, Boolean paymentStatus) {

        Customer customer = optionalCustomerByNationalId(customerNationalId);
        List<Invoice> invoices = invoiceRepository.findByCustomerAndPaid(customer,paymentStatus);
        List<OutputInvoiceDto> outputInvoiceDtoList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            outputInvoiceDtoList.add(invoicesMapper.invoiceToInvoiceOutputDto(invoice, customer));
        }
        return outputInvoiceDtoList;
    }

    //Fatura ödeme
    public PaymentOutputDto pay(PaymentInputDto paymentInputDto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentInputDto.getInvoiceId());
        if (optionalInvoice.isPresent()) {
            PaymentOutputDto paymentOutputDto = new PaymentOutputDto();
            if(paymentService.payRequest(paymentInputDto).getPaymentStatus()){
                Invoice invoice = optionalInvoice.get();
                invoice.setPaid(true);
                invoiceRepository.save(invoice);
                paymentOutputDto.setPaymentStatus(true);
                paymentOutputDto.setPaymentMessage("Ödeme başarılı bir şekilde gerçekleşti");
                return paymentOutputDto;
            }
            throw new ApplicationExceptionImpl("Ödeme Başarısız!");
        } else
            throw new ApplicationExceptionImpl("Fatura bulunamadı!");
    }

    private Customer optionalCustomerByNationalId(String customerNationalId) {
        return customerRepository.findByNationalId(customerNationalId).orElseThrow(() -> new ApplicationExceptionImpl("Müşteri bulunamadı!"));
    }
}
