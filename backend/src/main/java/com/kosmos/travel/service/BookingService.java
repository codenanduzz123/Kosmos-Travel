package com.kosmos.travel.service;

import com.kosmos.travel.model.entity.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking createBooking(Booking booking);
    Optional<Booking> getBookingById(Long id);
    List<Booking> getAllBookings();
    Booking updateBooking(Long id, Booking booking);
    void deleteBooking(Long id);
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByTourPackageId(Integer tourPackageId);
}
