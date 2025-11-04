package com.antonio.Pizzeria.web.controller;

import com.antonio.Pizzeria.persistence.entity.CustomerEntity;
import com.antonio.Pizzeria.persistence.entity.OrderEntity;
import com.antonio.Pizzeria.service.CustomerService;
import com.antonio.Pizzeria.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    public final CustomerService customerService;
    public final OrderService orderService;

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomer(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
}
