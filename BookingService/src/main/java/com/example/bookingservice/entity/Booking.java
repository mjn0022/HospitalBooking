package com.example.bookingservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Long patientId;
    private Long addressId;
    private Long hospitalId;

    private String appointmentDate;
    private String slot;
}