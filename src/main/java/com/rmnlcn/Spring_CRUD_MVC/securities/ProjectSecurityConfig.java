package com.rmnlcn.Spring_CRUD_MVC.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails lilas = User.builder()
                .username("lilas")
                .password("{noop}test123")
                .roles("SIMPLE_USER")
                .build();

        UserDetails romain = User.builder()
                .username("romain")
                .password("{noop}test123")
                .roles("SIMPLE_USER", "REGISTERED_USER")
                .build();

        UserDetails isabelle = User.builder()
                .username("isabelle")
                .password("{noop}test123")
                .roles("SIMPLE_USER", "REGISTERED_USER", "ADMIN_USER")
                .build();

        return new InMemoryUserDetailsManager(lilas, romain, isabelle);
    }
}
