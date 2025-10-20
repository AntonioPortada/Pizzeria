package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.OrderEntity;
import com.antonio.Pizzeria.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";


    public final OrderRepository repository;

    public List<OrderEntity> getAll() {
        return repository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime date = LocalDate.now().atTime(0, 0);
        return this.repository.findAllByDateAfter(date);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.repository.findAllByMethodIn(methods);
    }
}
