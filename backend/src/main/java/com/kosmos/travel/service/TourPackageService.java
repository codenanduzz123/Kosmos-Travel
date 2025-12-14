package com.kosmos.travel.service;

import com.kosmos.travel.model.entity.TourPackage;
import java.util.List;
import java.util.Optional;

public interface TourPackageService {
    TourPackage createTourPackage(TourPackage tourPackage);
    Optional<TourPackage> getTourPackageById(Integer id);
    List<TourPackage> getAllTourPackages();
    TourPackage updateTourPackage(Integer id, TourPackage tourPackage);
    void deleteTourPackage(Integer id);
}
