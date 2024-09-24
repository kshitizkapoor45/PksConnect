package com.pksconnect.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FetchEmployee fetchempoyee;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filter(HttpSecurity http)throws Exception
    {
        http.csrf().disable()
        .authorizeRequests()
        .requestMatchers("/pksconnect/login").permitAll()
        .requestMatchers("/pksconnect/newEmployee").permitAll()
        .requestMatchers("/pksconnect/roleChange").hasAuthority("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic();

        return http.build();
        
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuth()
    {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(fetchempoyee);
        dao.setPasswordEncoder(passwordEncoder());

        return dao;
        
    }
    
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    



}
