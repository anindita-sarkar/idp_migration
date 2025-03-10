package com.scb.interview.apigateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final Set<String> ALLOWED_ORIGINS = Set.of(
            "http://localhost:3000",
            "http://localhost:8082",
            "http://localhost:8084"
    );

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ✅ Allow CORS preflight requests
                        .pathMatchers("/realms/master/.well-known/**").permitAll() // ✅ Allow public Keycloak endpoints
                        .pathMatchers("/realms/master/protocol/openid-connect/**").permitAll() // ✅ Allow public Keycloak endpoints
                        .pathMatchers("/realms/master/protocol/saml/descriptor").permitAll() // ✅ Allow public Keycloak endpoints
                        .pathMatchers("/actuator/**").permitAll() // Optional for health checks or monitoring
                        .pathMatchers("/m1/actuator/**").permitAll() // Optional for m1 health checks or monitoring
                        .pathMatchers("/m2/actuator/**").permitAll() // Optional for m2 health checks or monitoring
                        .pathMatchers("/m2/api/user").permitAll() // For loading user details.
                        .pathMatchers("/public/**").permitAll()
                        .anyExchange().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                // Validate JWT tokens without client registration
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults()));

        return http.build();
    }

    /**
     * This method has been written to eliminate multiple cors headers from the response.
     *
     * @return
     */
    @Bean
    public GlobalFilter removeDuplicateCorsHeaders() {
        return (exchange, chain) ->
                chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    String origin = exchange.getRequest().getHeaders().getOrigin();

                    if (origin != null && ALLOWED_ORIGINS.contains(origin)) {
                        HttpHeaders headers = exchange.getResponse().getHeaders();
                        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
                        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                    }
                }));
    }

    /**
     * ✅ Handle CORS Preflight Requests at the Gateway Level
     */
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(ALLOWED_ORIGINS.stream().toList()); // Use the whitelist
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers",
                "Authorization"));
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true); // If credentials are required

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
