package com.example.bookingservice.controller;

import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody Map<String, Object> request) {
        try {
            Long patientId = Long.valueOf(request.get("patientId").toString());
            Long addressId = Long.valueOf(request.get("addressId").toString());
            String appointmentDate = request.get("appointmentDate").toString();
            String slot = request.get("slot").toString();

            Booking booking = bookingService.createBooking(patientId, addressId, appointmentDate, slot);

            if (booking != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Appointment booked successfully");
                response.put("bookingId", booking.getBookingId());
                response.put("appointmentDate", booking.getAppointmentDate());
                response.put("slot", booking.getSlot());
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("message", "No available hospital slot within 10 miles for the given date and time");
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", " " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}

