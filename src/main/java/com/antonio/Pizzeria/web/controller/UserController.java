package com.antonio.Pizzeria.web.controller;

import com.antonio.Pizzeria.persistence.entity.UserEntity;
import com.antonio.Pizzeria.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> findUser(@PathVariable(value = "username") String username) {
        Optional<UserEntity> user = this.service.findUser(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
        if(this.service.save(user)) {
            return ResponseEntity.ok(this.service.findUser(user.getUsername()).get());
        }
        return ResponseEntity.badRequest().build();
    }
}
