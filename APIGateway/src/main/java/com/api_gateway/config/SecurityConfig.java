package com.api_gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.aot.generate.ValueCodeGenerator.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){

        httpSecurity
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer()
                .jwt();

          /*httpSecurity
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/public/**").permitAll()     // Public access to endpoints under /public
                        .anyExchange().authenticated()              // Secure all other endpoints
                )
                .oauth2Login(withDefaults())                    // Enables OAuth2 login for Okta
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(withDefaults())); */                    // Enables JWT-based authentication

        return httpSecurity.build();
    }
}
