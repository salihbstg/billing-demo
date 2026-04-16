package com.bastug.billing.controller;

import com.bastug.billing.dtos.CustomerDto;
import com.bastug.billing.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;

    //Tüm müşterileri çek
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    //Müşteri kayıt
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    //Müşteri numarasıyla çekme
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    //Müşteri tc kimlik numarası ile çekme
    @GetMapping
    public ResponseEntity<CustomerDto> getCustomerByNationalId(@RequestParam(name="nationalId")String nationalId) {
        return ResponseEntity.ok(customerService.findCustomerByNationalId(nationalId));
    }

    //Müşteri aktif deaktif etme
    @PostMapping("/isActive/{customerId}")
    public ResponseEntity<Boolean> setActive(@PathVariable(name = "customerId") Long customerId) {
        return ResponseEntity.ok(customerService.isActive(customerId));
    }

    //Müşteri numarası ile müşteri kaydı silme
    @DeleteMapping("{customerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable(name = "customerId") Long customerId) {
        return ResponseEntity.ok(customerService.deleteCustomerById(customerId));
    }
}
