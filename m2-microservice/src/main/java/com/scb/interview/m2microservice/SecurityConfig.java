package com.scb.interview.m2microservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
// ... other imports

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .saml2Login(Customizer.withDefaults())       // Enable SAML2 SSO authentication
                .saml2Metadata(Customizer.withDefaults());   // Serve SP metadata at /saml2/service-provider-metadata/{registrationId}
        return http.build();
    }
}
