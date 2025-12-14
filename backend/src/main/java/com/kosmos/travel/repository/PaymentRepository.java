package com.kosmos.travel.repository;

import com.kosmos.travel.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Add custom queries if needed
}
