package com.kosmos.travel.service.impl;

import com.kosmos.travel.exception.ResourceNotFoundException;
import com.kosmos.travel.model.entity.Booking;
import com.kosmos.travel.model.entity.Payment;
import com.kosmos.travel.payment.PaymentRequest;
import com.kosmos.travel.repository.BookingRepository;
import com.kosmos.travel.repository.PaymentRepository;
import com.kosmos.travel.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Process a payment request:
     * - verify booking exists
     * - (optional) additional checks (amount matching, duplicate payments)
     * - create Payment entity and persist
     */
    @Override
    @Transactional
    public Payment processPayment(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            throw new IllegalArgumentException("PaymentRequest cannot be null");
        }

        Long bookingId = paymentRequest.getBookingId();
        if (bookingId == null) {
            throw new IllegalArgumentException("BookingId is required");
        }

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        // Optional: check for existing payment for this booking if business requires unique payment per booking
        // Uncomment if you want to enforce unique payment per booking:
        // if (paymentRepository.findByBookingId(bookingId).isPresent()) {
        //     throw new IllegalStateException("Payment already exists for booking id: " + bookingId);
        // }

        // Optional: validate amount vs booking (if booking has price info)
        // If booking or tourPackage has price, you can compare here. For now we accept provided amount when positive:
        Double amount = paymentRequest.getAmount();
        if (amount == null || amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        // Build Payment entity
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDate.now());

        // Save and return
        return paymentRepository.save(payment);
    }

    @Override
    public Payment createPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }
        // Basic validation: booking must be set
        if (payment.getBooking() == null || payment.getBooking().getId() == null) {
            throw new IllegalArgumentException("Payment must be associated with a booking");
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        // Update allowed fields
        if (paymentDetails.getAmount() != null) {
            payment.setAmount(paymentDetails.getAmount());
        }
        if (paymentDetails.getPaymentDate() != null) {
            payment.setPaymentDate(paymentDetails.getPaymentDate());
        }
        if (paymentDetails.getBooking() != null && paymentDetails.getBooking().getId() != null) {
            // Validate booking exists
            Long bookingId = paymentDetails.getBooking().getId();
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
            payment.setBooking(booking);
        }

        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        paymentRepository.delete(payment);
    }
}
