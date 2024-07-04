package com.example.testapplication.repository;

import com.example.testapplication.entity.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {
}
