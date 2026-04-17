package com.bastug.billing.controller;

import com.bastug.billing.dtos.InputInvoiceDto;
import com.bastug.billing.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
