package com.example.testapplication.repository;

import com.example.testapplication.entity.Role;
import com.example.testapplication.entity.UserRole;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRoleRepository extends ReactiveCrudRepository<UserRole, Long> {

    @Query("SELECT r.* " +
            "FROM ROLE r " +
            "INNER JOIN USER_ROLE ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = :userId")
    Flux<Role> findRolesByUserId(Long userId);
}
