package com.example.hospitalservice.service;

import com.example.hospitalservice.entity.*;
import com.example.hospitalservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalInfoRepository infoRepo;

    @Autowired
    private HospitalAvailabilityRepository availabilityRepo;

    @Autowired
    private GeocodingService geoService;

    public HospitalInfo saveHospital(HospitalInfo hospital) {
        String fullAddress = hospital.getAddressLine() + ", " +
                             hospital.getCity() + ", " +
                             hospital.getState() + ", " +
                             hospital.getZipcode();
        double[] latlon = geoService.getLatLonFromAddress(fullAddress);
        hospital.setLatitude(latlon[0]);
        hospital.setLongitude(latlon[1]);
        return infoRepo.save(hospital);
    }

    public HospitalAvailability saveAvailability(HospitalAvailability availability) {
        return availabilityRepo.save(availability);
    }

    public List<HospitalInfo> getAllHospitals() {
        return infoRepo.findAll();
    }

    public List<HospitalAvailability> getAvailability(Long hospitalId) {
        return availabilityRepo.findByHospitalId(hospitalId);
    }
}