package com.example.hospitalservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availabilityId;

    private Long hospitalId;

    private String availabilityDate; // or use LocalDate if preferred

    private String slot; // "morning" or "evening"

    private boolean slotAvailable; // true = available, false = booked
}
