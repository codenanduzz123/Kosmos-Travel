package com.kosmos.travel.service;

import com.kosmos.travel.model.entity.Payment;
import com.kosmos.travel.payment.PaymentRequest;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    /**
     * Process a payment request (validate booking, create Payment record).
     * This is the primary entrypoint used by controllers when a user pays.
     */
    Payment processPayment(PaymentRequest paymentRequest);

    /**
     * Create a payment entity directly (useful for simple CRUD or tests).
     */
    Payment createPayment(Payment payment);

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getAllPayments();

    Payment updatePayment(Long id, Payment payment);

    void deletePayment(Long id);
}
