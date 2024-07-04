package com.example.testapplication.service;

import com.example.testapplication.repository.UserRepository;
import com.example.testapplication.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;

@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> userRoleRepository.findRolesByUserId(user.getId())
                        .collectList()
                        .map(HashSet::new)
                        .doOnNext(user::setRoles)
                        .thenReturn(user));
    }
}
