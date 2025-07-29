package com.example.hospitalservice.repository;

import com.example.hospitalservice.entity.HospitalAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalAvailabilityRepository extends JpaRepository<HospitalAvailability, Long> {
    List<HospitalAvailability> findByHospitalId(Long hospitalId);
}