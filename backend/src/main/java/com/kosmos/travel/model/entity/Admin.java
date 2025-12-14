package com.kosmos.travel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Username or Email is required")
    private String username;

    @Column(name = "password", length = 15, nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 15, message = "Password must be 8 to 15 characters")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,15}$",
        message = "Password must contain uppercase, lowercase, digit, special character"
    )
    private String password;

    public Admin() {}

    // getters & setters
}
