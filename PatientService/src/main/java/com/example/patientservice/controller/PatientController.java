package com.example.patientservice.controller;

import com.example.patientservice.entity.*;
import com.example.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public PatientInfo createPatient(@RequestBody PatientInfo patient) {
        return patientService.savePatient(patient);
    }

    @PostMapping("/address")
    public PatientAddress addAddress(@RequestBody PatientAddress address) {
        return patientService.saveAddress(address);
    }

    @PostMapping("/availability")
    public PatientAvailability addAvailability(@RequestBody PatientAvailability availability) {
        return patientService.saveAvailability(availability);
    }

    @GetMapping
    public List<PatientInfo> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/address/{id}")
    public PatientAddress getAddressById(@PathVariable Long id) {
        return patientService.getAddressById(id);
    }

    @GetMapping("/availability")
    public List<PatientAvailability> getAllAvailability() {
        return patientService.getAllAvailability();
    }
}