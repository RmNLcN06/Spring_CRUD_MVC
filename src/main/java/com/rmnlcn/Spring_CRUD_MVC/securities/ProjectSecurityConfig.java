package com.rmnlcn.Spring_CRUD_MVC.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

    /*
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
     */

    // Add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT member_id, pswd, active FROM members WHERE member_id=?");

        // define query to retrieve authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT member_id, role FROM roles WHERE member_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").hasRole("SIMPLE_USER")
                        .requestMatchers("/registered-users/**").hasRole("REGISTERED_USER")
                        .requestMatchers("/admin-users/**").hasRole("ADMIN_USER")
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()
                // or => .logout(LogoutConfigurer::permitAll
                )
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}
