package com.kosmos.travel.repository;

import com.kosmos.travel.model.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageRepository extends JpaRepository<TourPackage, Integer> {
    // Add custom queries if needed
}

