package com.example.hospitalservice.repository;

import com.example.hospitalservice.entity.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {
}