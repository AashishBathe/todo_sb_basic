package com.aashish.todo_app_basic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    // LDAP or Database
    Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = createNewUser("aash", "bath");
        UserDetails userDetails2 = createNewUser("aashish", "bathe");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        return User
                .builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        ); // Protect all URLs
        http.formLogin(Customizer.withDefaults()); // Custom Login Page
//        http.csrf().disable(); // TO access console of h2
//        http.headers().frameOptions().disable(); // Need it to view console
        http.csrf(csrf -> csrf.disable());
        http.headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }
}
