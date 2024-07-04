package com.example.testapplication.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER_ROLE")
@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class UserRole {

    @Id
    private Long id;

    private Long userId;

    private Long roleId;
}
