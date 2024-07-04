package com.example.testapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/fe").hasAnyRole("ADMIN", "FRONTEND")
                        .pathMatchers("/be").hasAnyRole("ADMIN", "BACKEND")
                        .pathMatchers("/admin", "/hello/*").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/hello"))
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                );

        return http.build();
    }
}
