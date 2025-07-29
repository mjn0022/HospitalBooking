package com.example.patientservice.service;

import com.example.patientservice.entity.*;
import com.example.patientservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientInfoRepository patientInfoRepo;

    @Autowired
    private PatientAddressRepository addressRepo;

    @Autowired
    private PatientAvailabilityRepository availabilityRepo;

    @Autowired
    private GeocodingService geocodingService;

    public PatientInfo savePatient(PatientInfo patient) {
        return patientInfoRepo.save(patient);
    }

    public PatientAddress saveAddress(PatientAddress address) {
        String fullAddress = address.getAddressLine() + ", " +
                             address.getCity() + ", " +
                             address.getState() + ", " +
                             address.getZipcode();

        double[] latlon = geocodingService.getLatLonFromAddress(fullAddress);
        address.setLatitude(latlon[0]);
        address.setLongitude(latlon[1]);

        return addressRepo.save(address);
    }

    public PatientAvailability saveAvailability(PatientAvailability availability) {
        return availabilityRepo.save(availability);
    }

    public List<PatientInfo> getAllPatients() {
        return patientInfoRepo.findAll();
    }

    public PatientAddress getAddressById(Long id) {
        return addressRepo.findById(id).orElse(null);
    }

    public List<PatientAvailability> getAllAvailability() {
        return availabilityRepo.findAll();
    }
}