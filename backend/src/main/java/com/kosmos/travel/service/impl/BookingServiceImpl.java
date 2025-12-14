package com.kosmos.travel.service.impl;

import com.kosmos.travel.model.entity.Booking;
import com.kosmos.travel.repository.BookingRepository;
import com.kosmos.travel.service.BookingService;
import com.kosmos.travel.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        booking.setUser(bookingDetails.getUser());
        booking.setTourPackage(bookingDetails.getTourPackage());
        booking.setBookingDate(bookingDetails.getBookingDate());
        booking.setStatus(bookingDetails.getStatus());

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByTourPackageId(Integer tourPackageId) {
        return bookingRepository.findByTourPackageId(tourPackageId);
    }
}
