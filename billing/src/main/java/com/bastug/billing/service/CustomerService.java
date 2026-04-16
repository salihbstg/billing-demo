package com.bastug.billing.service;

import com.bastug.billing.dtos.CustomerDto;
import com.bastug.billing.entity.Customer;
import com.bastug.billing.mapper.CustomerMapper;
import com.bastug.billing.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    //Müşteri kayıt
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(customerMapper.customerDtoToCustomer(customerDto));
        return customerMapper.customerToCustomerDto(customer);
    }

    //Müşteriyi müşteri numarasına göre çekme
    public CustomerDto findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customerMapper.customerToCustomerDto(customer.get());
        } else {
            return null;
        }
    }

    //Müşterici tc numarasına göre çekme
    public CustomerDto findCustomerByNationalId(String nationalId) {
        Optional<Customer> customer = customerRepository.findByNationalId(nationalId);
        if (customer.isPresent()) {
            return customerMapper.customerToCustomerDto(customer.get());
        } else {
            return null;
        }
    }

    //Müşteri bilgilerini güncelleme
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findByNationalId(customerDto.getNationalId());
        if (customer.isPresent()) {
            Customer customerEntity = customer.get();
            customerEntity.setAddress(customerDto.getAddress());
            customerEntity.setName(customerDto.getName());
            customerEntity.setPhone(customerDto.getPhone());
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setLastName(customerDto.getLastName());
            customerRepository.save(customerEntity);
            return customerMapper.customerToCustomerDto(customerEntity);
        } else {
            return null;
        }
    }

    //Müşteri aktiflik değiştirme
    public Boolean isActive(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer customerEntity = customer.get();
            customerEntity.setActive(!customerEntity.getActive());
            customerRepository.save(customerEntity);
            return customerEntity.getActive();
        }
        else
            return null;
    }

    //Müşteri silme
    public Boolean deleteCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        }
        return false;
    }

    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(customerMapper.customerToCustomerDto(customer));
        }
        return customerDtos;
    }
}