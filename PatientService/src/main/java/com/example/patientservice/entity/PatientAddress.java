package com.example.patientservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private Long patientId;
    private String addressLine;
    private String city;
    private String state;
    private String zipcode;
    private double latitude;
    private double longitude;
}