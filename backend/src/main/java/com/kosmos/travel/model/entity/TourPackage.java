package com.kosmos.travel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "tour_packages")
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Package name is required")
    private String name;

    @Column(length = 1000, nullable = false)
    @NotBlank(message = "Description is required")
    private String description;

    @Column(nullable = false)
    @Positive(message = "Price must be positive")
    private Double price;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Duration is required")
    private String duration;

    @Column(name = "available_dates", length = 50, nullable = false)
    @NotBlank(message = "Available dates required")
    private String availableDates;

    @Column(name = "available_count", nullable = false)
    @PositiveOrZero(message = "Available count must be >= 0")
    private Integer availableCount;

    public TourPackage() {}

    // getters & setters
}
