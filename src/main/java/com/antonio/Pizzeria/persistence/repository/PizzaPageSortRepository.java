package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPageSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
}
