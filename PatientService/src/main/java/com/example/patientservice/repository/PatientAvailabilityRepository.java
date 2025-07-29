package com.example.patientservice.repository;

import com.example.patientservice.entity.PatientAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAvailabilityRepository extends JpaRepository<PatientAvailability, Long> {
}