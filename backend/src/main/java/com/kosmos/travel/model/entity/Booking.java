package com.kosmos.travel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_package_id", nullable = false)
    private TourPackage tourPackage;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "Status is required")
    private String status;

    public Booking() {}

    // Getters and Setters...
}
