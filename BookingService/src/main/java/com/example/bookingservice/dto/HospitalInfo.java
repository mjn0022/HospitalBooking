package com.example.bookingservice.dto;

import lombok.Data;

@Data
public class HospitalInfo {
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
