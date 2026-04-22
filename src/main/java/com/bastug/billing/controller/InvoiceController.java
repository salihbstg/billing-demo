package com.bastug.billing.controller;

import com.bastug.billing.dtos.InputInvoiceDto;
import com.bastug.billing.dtos.OutputInvoiceDto;
import com.bastug.billing.dtos.PaymentInputDto;
import com.bastug.billing.dtos.PaymentOutputDto;
import com.bastug.billing.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
@AllArgsConstructor
@CrossOrigin("*")
public class InvoiceController {
    private final InvoiceService invoiceService;
    //Fatura oluşturma
    @PostMapping
    public ResponseEntity<com.bastug.billing.dtos.OutputInvoiceDto> createInvoice(@RequestBody InputInvoiceDto inputInvoiceDto) {
        return ResponseEntity.ok(invoiceService.createInvoice(inputInvoiceDto));
    }

    //Müşteri tcsine göre fatura çekme
    @GetMapping("/{customerNationalId}")
    public ResponseEntity<List<OutputInvoiceDto>> getInvoices(@PathVariable(name = "customerNationalId") String customerNationalId) {
        return ResponseEntity.ok(invoiceService.getAllInvoiceByNationalNumber(customerNationalId));
    }

    //Müşteri tcsi ve ödendi/ödenmedi olarak görüntüleme
    @GetMapping("/isPaid")
    public ResponseEntity<List<OutputInvoiceDto>> getInvoices(@RequestParam(name = "customerNationalId") String customerNationalId, @RequestParam(name = "isPaid") Boolean isPaid) {
        return ResponseEntity.ok(invoiceService.getInvoicesByPaymentStatus(customerNationalId,isPaid));
    }

    //Fatura numarası ile ödeme
    @PostMapping("/pay")
    public ResponseEntity<PaymentOutputDto> payInvoice(@RequestBody PaymentInputDto paymentInputDto) {
        return ResponseEntity.ok(invoiceService.pay(paymentInputDto));
    }

}
