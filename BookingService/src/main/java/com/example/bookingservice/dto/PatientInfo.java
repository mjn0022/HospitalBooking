package com.example.bookingservice.dto;

import lombok.Data;

@Data
public class PatientInfo {
    private Long id;
    private String name;
    private String dob;
    private String gender;
    private String phoneNo;
    private String email;
}
