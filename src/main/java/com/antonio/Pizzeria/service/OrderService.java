package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.OrderEntity;
import com.antonio.Pizzeria.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository repository;

    public List<OrderEntity> getAll() {
        return repository.findAll();
    }
}
