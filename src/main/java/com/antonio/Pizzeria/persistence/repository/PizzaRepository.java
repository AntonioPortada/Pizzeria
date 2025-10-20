package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    List<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainsIgnoreCase(String ingredient);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String ingredient);
    int countByVeganTrue();
}

