package com.example.testapplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public Mono<String> hello(@AuthenticationPrincipal Mono<UserDetails> userDetailsMono) {
        return userDetailsMono.map(userDetails -> "Hello, " + userDetails.getUsername());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userName}")
    public Mono<String> adminHello(@PathVariable String userName) {
        return Mono.just("This is your personal page, " + userName);
    }
}
