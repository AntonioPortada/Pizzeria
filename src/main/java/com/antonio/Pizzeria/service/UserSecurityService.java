package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.UserEntity;
import com.antonio.Pizzeria.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User"+ username +"not found"));
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles("ADMIN")
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }
}
