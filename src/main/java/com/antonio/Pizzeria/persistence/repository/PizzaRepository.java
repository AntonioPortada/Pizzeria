package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainsIgnoreCase(String ingredient);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String ingredient);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPricesAsc(double price);
    int countByVeganTrue();
}

