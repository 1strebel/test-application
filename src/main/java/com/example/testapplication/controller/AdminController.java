package com.example.testapplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AdminController {

    @GetMapping("/fe")
    @PreAuthorize("hasAnyRole('ROLE_FRONTEND', 'ROLE_ADMIN')")
    public Mono<String> frontendDeveloper(@AuthenticationPrincipal Mono<UserDetails> userDetailsMono) {
        return userDetailsMono.map(userDetails -> "You are frontend developer, " + userDetails.getUsername());
    }

    @GetMapping("/be")
    @PreAuthorize("hasAnyRole('ROLE_BACKEND', 'ROLE_ADMIN')")
    public Mono<String> backendDeveloper(@AuthenticationPrincipal Mono<UserDetails> userDetailsMono) {
        return userDetailsMono.map(userDetails -> "You are backend developer, " + userDetails.getUsername());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<String> admin(@AuthenticationPrincipal Mono<UserDetails> userDetailsMono) {
        return userDetailsMono.map(userDetails -> "You are admin, " + userDetails.getUsername());
    }
}
