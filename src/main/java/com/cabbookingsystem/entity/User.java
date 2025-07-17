package com.cabbookingsystem.entity;

import com.cabbookingsystem.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String createdAt;
}
