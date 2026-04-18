package com.bastug.billing.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private Long customerId;
    private String name;
    private String lastName;
    private String nationalId;
    private String phone;
    private String email;
    private String address;
    private Boolean active;
    private LocalDateTime createAt;
}
