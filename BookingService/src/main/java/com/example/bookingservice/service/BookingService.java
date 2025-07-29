package com.example.bookingservice.service;

import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.util.Haversine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    public Booking createBooking(Long patientId, Long addressId, String appointmentDate, String slot) {
        // Step 1: Get Patient Address Coordinates
        Map<String, Object> address = restTemplate.getForObject(
            "http://localhost:8080/patients/address/" + addressId, Map.class);
        double lat1 = (double) address.get("latitude");
        double lon1 = (double) address.get("longitude");

        // Step 2: Get List of Hospitals
        List<Map<String, Object>> hospitals = restTemplate.getForObject(
            "http://localhost:8081/hospitals", List.class);

        for (Map<String, Object> hospital : hospitals) {
            Long hospitalId = Long.valueOf((Integer) hospital.get("hospitalId"));
            double lat2 = ((Number) hospital.get("latitude")).doubleValue();
            double lon2 = ((Number) hospital.get("longitude")).doubleValue();

            double distance = Haversine.distance(lat1, lon1, lat2, lon2);
            if (distance <= 10.0) {
                List<Map<String, Object>> availability = restTemplate.getForObject(
                    "http://localhost:8081/hospitals/" + hospitalId + "/availability", List.class);

                for (Map<String, Object> slotMap : availability) {
                    if (slotMap.get("availabilityDate").equals(appointmentDate)
                            && slotMap.get("slot").equals(slot)
                            && (Boolean) slotMap.get("slotAvailable")) {

                        Booking booking = new Booking(null, patientId, addressId, hospitalId, appointmentDate, slot);
                        return bookingRepo.save(booking);
                    }
                }
            }
        }
        throw new RuntimeException("No available slots found within 10 miles.");
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}