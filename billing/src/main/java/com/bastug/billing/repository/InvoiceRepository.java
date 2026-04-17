package com.bastug.billing.repository;

import com.bastug.billing.dtos.OutputInvoiceDto;
import com.bastug.billing.entity.Customer;
import com.bastug.billing.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCustomer(Customer customer);
}
