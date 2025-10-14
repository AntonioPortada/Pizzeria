package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.PizzaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final JdbcTemplate jdbcTemplate;

    public List<PizzaEntity> getAll() {
        return jdbcTemplate.query("select * from pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}
