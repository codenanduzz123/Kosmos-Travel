package com.kosmos.travel.service.impl;

import com.kosmos.travel.model.entity.TourPackage;
import com.kosmos.travel.repository.TourPackageRepository;
import com.kosmos.travel.service.TourPackageService;
import com.kosmos.travel.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourPackageServiceImpl implements TourPackageService {

    private final TourPackageRepository tourPackageRepository;

    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    @Override
    public TourPackage createTourPackage(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    @Override
    public Optional<TourPackage> getTourPackageById(Integer id) {
        return tourPackageRepository.findById(id);
    }

    @Override
    public List<TourPackage> getAllTourPackages() {
        return tourPackageRepository.findAll();
    }

    @Override
    public TourPackage updateTourPackage(Integer id, TourPackage packageDetails) {
        TourPackage tourPackage = tourPackageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tour package not found with id: " + id));

        tourPackage.setName(packageDetails.getName());
        tourPackage.setDescription(packageDetails.getDescription());
        tourPackage.setPrice(packageDetails.getPrice());
        tourPackage.setDuration(packageDetails.getDuration());
        tourPackage.setAvailableDates(packageDetails.getAvailableDates());
        tourPackage.setAvailableCount(packageDetails.getAvailableCount());

        return tourPackageRepository.save(tourPackage);
    }

    @Override
    public void deleteTourPackage(Integer id) {
        TourPackage tourPackage = tourPackageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tour package not found with id: " + id));
        tourPackageRepository.delete(tourPackage);
    }
}
