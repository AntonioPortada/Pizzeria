package com.antonio.Pizzeria.web.config;

import com.antonio.Pizzeria.utils.EncriptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final EncriptUtils encript;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/customer/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers( "/api/user/**").hasRole("ADMIN")
                        .requestMatchers("/api/orders/random").hasAuthority("random_order") //check the order to prevent not allowed
                        .requestMatchers("/api/orders/**").hasRole("ADMIN")
                        .requestMatchers("/api/rol/**").hasRole("ROLES")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /*@Bean //se creó el service para proveer los usuarios de db
    public UserDetailsService memoryUser() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(encript.passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(encript.passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }*/
}
