package com.kosmos.travel.repository;

import com.kosmos.travel.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByTourPackageId(Integer tourPackageId);
}
