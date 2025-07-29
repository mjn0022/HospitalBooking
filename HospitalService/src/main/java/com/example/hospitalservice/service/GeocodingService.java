package com.example.hospitalservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;

@Service
public class GeocodingService {

    public double[] getLatLonFromAddress(String fullAddress) {
        String url = "https://nominatim.openstreetmap.org/search?q=" +
                     fullAddress.replace(" ", "+") + "&format=json&limit=1";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONArray arr = new JSONArray(response);
        if (arr.length() > 0) {
            JSONObject obj = arr.getJSONObject(0);
            double lat = Double.parseDouble(obj.getString("lat"));
            double lon = Double.parseDouble(obj.getString("lon"));
            return new double[]{lat, lon};
        } else {
            throw new RuntimeException("Geocoding failed for address: " + fullAddress);
        }
    }
}