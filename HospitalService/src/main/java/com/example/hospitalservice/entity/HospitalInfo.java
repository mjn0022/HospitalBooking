package com.example.hospitalservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    private String name;
    private String phoneNo;

    private String addressLine;
    private String city;
    private String state;
    private String zipcode;

    private double latitude;
    private double longitude;
}