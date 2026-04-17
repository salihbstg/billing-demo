package com.bastug.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Customer {
    public Customer(){
        this.active=true;
        this.createAt=LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq",initialValue = 365325,allocationSize = 101)
    private Long customerId;

    private String name;
    private LocalDateTime createAt;
    private String lastName;
    @Column(unique=true)
    private String nationalId;
    private String phone;
    private String email;
    private String address;
    private Boolean active;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;
}
