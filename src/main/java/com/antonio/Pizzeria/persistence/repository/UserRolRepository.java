package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.UserRolEntity;
import com.antonio.Pizzeria.persistence.entity.UserRoleId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRolRepository extends CrudRepository<UserRolEntity, UserRoleId> {

    List<UserRolEntity> findAllByUsername(String username);
}
