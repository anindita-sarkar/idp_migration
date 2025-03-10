package com.scb.interview.m2microservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Enable CORS
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        "/login/saml2/sso/**",
                        "/saml2/**",
                        "/actuator/**"
                )) // ✅ Ignore CSRF for SAML endpoints and health checks
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/login/saml2/sso/**").permitAll()
                        .requestMatchers("/saml2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .saml2Login(Customizer.withDefaults()) // ✅ Enable SAML2 SSO authentication
                .saml2Metadata(Customizer.withDefaults()); // ✅ Serve SP metadata

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allow specific frontend and backend URLs + allow 'null' origin
        config.setAllowedOriginPatterns(List.of(
                "http://localhost:*",
                "http://127.0.0.1:*",
                "null"  // ✅ Allow 'null' origin for SAML login redirects
        ));

        // ✅ Allow credentials for session-based SAML authentication
        config.setAllowCredentials(true);

        // ✅ Allow common headers for SAML and REST calls
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Set-Cookie",
                "Referer"
        ));

        // ✅ Expose headers for session and SAML tokens
        config.setExposedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Set-Cookie"
        ));

        // ✅ Allow standard HTTP methods
        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}