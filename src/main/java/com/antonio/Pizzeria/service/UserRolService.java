package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.UserRolEntity;
import com.antonio.Pizzeria.persistence.entity.UserRoleId;
import com.antonio.Pizzeria.persistence.repository.UserRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRolService {

    private final UserRolRepository repository;

    public boolean save(UserRolEntity rol) {
        rol.setGrantedDate(LocalDateTime.now());
        this.repository.save(rol);
        UserRoleId keyRol = new UserRoleId(rol.getUsername(), rol.getRole());
        Optional<UserRolEntity> newRol = this.repository.findById(keyRol);
        return newRol.isPresent();
    }

    public Optional<UserRolEntity> findRol(UserRolEntity rol) {
        return this.repository.findById(new UserRoleId(rol.getUsername(), rol.getRole()));
    }

    public List<UserRolEntity> getAllByUser(String username) {
        return this.repository.findAllByUsername(username);
    }
}
