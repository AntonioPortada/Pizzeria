package com.antonio.Pizzeria.web.controller;

import com.antonio.Pizzeria.persistence.entity.UserRolEntity;
import com.antonio.Pizzeria.service.UserRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/rol")
@RequiredArgsConstructor
public class UserRolController {

    private final UserRolService service;

    @PostMapping
    public ResponseEntity<UserRolEntity> save(@RequestBody UserRolEntity rol) {
        if(this.service.save(rol)) {
            return ResponseEntity.ok(this.service.findRol(rol).get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserRolEntity>> getAll(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.service.getAllByUser(username));
    }
}
