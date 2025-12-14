package com.kosmos.travel.payment;

import com.kosmos.travel.exception.ResourceNotFoundException;
import com.kosmos.travel.model.entity.Booking;
import com.kosmos.travel.model.entity.Payment;
import com.kosmos.travel.repository.BookingRepository;
import com.kosmos.travel.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200") // dev only; remove/secure for production
public class PaymentChargeController {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentChargeController(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * POST /api/payments/charge
     * Accepts a PaymentRequest { bankToken, bookingId, amount } and creates a Payment record.
     * This is a simulated flow: it trusts the provided bankToken (which was returned by /api/bank/validate).
     */
    @PostMapping("/charge")
    public ResponseEntity<?> charge(@RequestBody PaymentRequest req) {

        if (req.getBankToken() == null || req.getBankToken().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "Missing bank token"));
        }

        // Verify booking exists
        Booking booking = bookingRepository.findById(req.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + req.getBookingId()));

        // Business rule: amount must match booking's price or be positive (adjust as needed)
        if (req.getAmount() == null || req.getAmount() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "Invalid amount"));
        }

        // Create and save Payment entity
        Payment p = new Payment();
        p.setAmount(req.getAmount());
        p.setPaymentDate(LocalDate.now());
        p.setBooking(booking);

        Payment saved = paymentRepository.save(p);

        // Return basic success response
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "paymentId", saved.getId(),
                "message", "Payment recorded (demo)."
        ));
    }
}
