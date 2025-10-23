package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.PizzaEntity;
import com.antonio.Pizzeria.service.dto.UpdatePizzaDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainsIgnoreCase(String ingredient);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String ingredient);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    @Query(nativeQuery = true,
            //value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :idPizza" //primera forma
            value = """
                    UPDATE pizza 
                        SET price = :#{#dto.newPrice} 
                    WHERE id_pizza = :#{#dto.pizzaId}
                    """)
    @Modifying
    //void updatePrice(@Param("idPizza") int idPizza, @Param("newPrice") double newPrice); //primera forma
    void updatePrice(@Param("idPizza")UpdatePizzaDTO dto);

    int countByVeganTrue();
}

