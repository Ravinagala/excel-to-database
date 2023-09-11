package com.example.exceltodatabase.security;

import com.example.exceltodatabase.services.SecurityInfoSecurityDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("ravi")
//                .password(passwordEncoder.encode("ravi@"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("jai")
//                .password(passwordEncoder.encode("jai@"))
//                .roles("USER","ADMIN","HR")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,user);
        return new SecurityInfoSecurityDetailsService();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/customers/**","/roles/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/users/**").authenticated()
                .and().formLogin()
                .and().build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


}
