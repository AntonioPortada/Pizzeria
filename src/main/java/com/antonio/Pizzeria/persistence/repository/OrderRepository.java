package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM pizza_order where id_customer = :id")
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}
