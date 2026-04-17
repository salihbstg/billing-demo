package com.bastug.billing.mapper;

import com.bastug.billing.dtos.CustomerDto;
import com.bastug.billing.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Component
public class CustomerMapper {
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setNationalId(customerDto.getNationalId());
        customer.setLastName(customerDto.getLastName());
        return customer;
    }

    public CustomerDto customerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setActive(customer.getActive());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setNationalId(customer.getNationalId());
        customerDto.setLastName(customer.getLastName());
        customerDto.setCreateAt(customer.getCreateAt());
        return customerDto;
    }
}
