package com.antonio.Pizzeria.persistence.repository;

import com.antonio.Pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity, String> {

    UserEntity findByUsernameAndPassword(String username, String password);
}
