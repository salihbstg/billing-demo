package com.bastug.billing.controller;

import com.bastug.billing.dtos.InputInvoiceDto;
import com.bastug.billing.dtos.OutputInvoiceDto;
import com.bastug.billing.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
@AllArgsConstructor
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
        return ResponseEntity.ok(invoiceService.getInvoice(customerNationalId));
    }

    //Fatura numarası ile ödeme
    @PostMapping("/pay/{invoiceId}")
    public ResponseEntity<Boolean> payInvoice(@PathVariable(name = "invoiceId") Long invoiceId) {
        return ResponseEntity.ok(invoiceService.pay(invoiceId));
    }
}
