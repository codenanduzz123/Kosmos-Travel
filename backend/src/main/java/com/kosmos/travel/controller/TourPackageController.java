package com.kosmos.travel.controller;

import com.kosmos.travel.model.entity.TourPackage;
import com.kosmos.travel.service.TourPackageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tour-packages")
public class TourPackageController {

    private final TourPackageService tourPackageService;

    public TourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    // Create tour package
    @PostMapping
    public ResponseEntity<TourPackage> createTourPackage(@Valid @RequestBody TourPackage tourPackage) {
        TourPackage savedPackage = tourPackageService.createTourPackage(tourPackage);
        return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
    }

    // Get all tour packages
    @GetMapping
    public ResponseEntity<List<TourPackage>> getAllTourPackages() {
        List<TourPackage> packages = tourPackageService.getAllTourPackages();
        return ResponseEntity.ok(packages);
    }

    // Get tour package by ID
    @GetMapping("/{id}")
    public ResponseEntity<TourPackage> getTourPackageById(@PathVariable Integer id) {
        Optional<TourPackage> packageOpt = tourPackageService.getTourPackageById(id);
        return packageOpt.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    // Update tour package
    @PutMapping("/{id}")
    public ResponseEntity<TourPackage> updateTourPackage(@PathVariable Integer id, @Valid @RequestBody TourPackage tourPackage) {
        TourPackage updatedPackage = tourPackageService.updateTourPackage(id, tourPackage);
        return ResponseEntity.ok(updatedPackage);
    }

    // Delete tour package
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourPackage(@PathVariable Integer id) {
        tourPackageService.deleteTourPackage(id);
        return ResponseEntity.noContent().build();
    }
}
