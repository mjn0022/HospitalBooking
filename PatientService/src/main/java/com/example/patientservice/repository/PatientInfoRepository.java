package com.example.patientservice.repository;

import com.example.patientservice.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {
}