package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.UserEntity;
import com.antonio.Pizzeria.persistence.repository.UserRepository;
import com.antonio.Pizzeria.utils.EncriptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final EncriptUtils encript;

    public Optional<UserEntity> findUser(String username) {
        return this.repository.findById(username);
    }

    public UserEntity findUser(String username, String password) {
        return this.repository.findByUsernameAndPassword(username, password) ;
    }

    public boolean save(UserEntity user) {
        String encodedPass = this.encript.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPass);
        this.repository.save(user);
        Optional<UserEntity> userExist = this.repository.findById(user.getUsername());
        return userExist.isPresent();
    }
}
