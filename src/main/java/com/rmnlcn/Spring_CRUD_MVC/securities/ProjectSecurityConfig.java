package com.rmnlcn.Spring_CRUD_MVC.securities;

import com.rmnlcn.Spring_CRUD_MVC.services.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

    // bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


    // authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(MemberService memberService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(memberService); //set the custom member details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    /*
    // Add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT member_id, pswd, active FROM members WHERE member_id=?");

        // define query to retrieve authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT member_id, role FROM roles WHERE member_id=?");

        return jdbcUserDetailsManager;
    } */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {


        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/simple-users/**").hasRole("SIMPLE_USER")
                        .requestMatchers("/registered-users/**").hasRole("REGISTERED_USER")
                        .requestMatchers("/admin-users/**").hasRole("ADMIN_USER")
                        .requestMatchers("/registers/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll().logoutSuccessUrl("/")
                // or => .logout(LogoutConfigurer::permitAll
                )
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}
