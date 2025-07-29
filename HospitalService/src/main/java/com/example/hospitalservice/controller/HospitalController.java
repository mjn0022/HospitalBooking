package com.example.hospitalservice.controller;

import com.example.hospitalservice.entity.*;
import com.example.hospitalservice.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public HospitalInfo createHospital(@RequestBody HospitalInfo hospital) {
        return hospitalService.saveHospital(hospital);
    }

    @PostMapping("/availability")
    public HospitalAvailability addAvailability(@RequestBody HospitalAvailability availability) {
        return hospitalService.saveAvailability(availability);
    }

    @GetMapping
    public List<HospitalInfo> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}/availability")
    public List<HospitalAvailability> getAvailability(@PathVariable Long id) {
        return hospitalService.getAvailability(id);
    }
}