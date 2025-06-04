package com.jemehgoh.weatherstationserver;

import com.jemehgoh.weatherstationserver.readings.Reading;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * A REST controller that handles HTTP requests to the server.
 */

@CrossOrigin
@RestController
public class WeatherStationController {
    private ArrayList<Reading> readings;

    public WeatherStationController() {
        readings = new ArrayList<>();
    }

    @GetMapping(value = "/")
    public String greetUser() {
        return "ESP32 weather station";
    }

    // Returns a JSON of the first reading in the reading list
    @GetMapping(value = "/readings", produces = "application/json")
    public ArrayList<Reading> getReadings() {
        return readings;
    }

    // Receives sensor data from the ESP32 through HTTP POST requests
    @PostMapping(value = "/post")
    public String postData(@RequestBody Reading reading) {
        readings.add(reading);
        return "Data received successfully.";
    }
}

