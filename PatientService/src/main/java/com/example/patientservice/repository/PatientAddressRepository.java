package com.example.patientservice.repository;

import com.example.patientservice.entity.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {
}