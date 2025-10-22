package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.CustomerEntity;
import com.antonio.Pizzeria.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerEntity findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}
