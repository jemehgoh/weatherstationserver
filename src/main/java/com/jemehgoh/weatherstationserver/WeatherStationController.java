package com.jemehgoh.weatherstationserver;

import com.jemehgoh.weatherstationserver.readings.Reading;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * A REST controller that handles HTTP requests to the server.
 */

@RestController
public class WeatherStationController {
    private ArrayList<Reading> readings;

    public WeatherStationController() {
        readings = new ArrayList<>();
    }

    @GetMapping(value="/")
    public String greetUser() {
        return "ESP32 weather station";
    }

    @GetMapping(value="/data")
    public String getData(@RequestParam(value = "type", defaultValue = "temperature") String dataType,
            @RequestParam(value = "count", defaultValue = "10") int count) {
        String output = "Readings:\n";

        int readingsCount = (readings.size() < count) ? readings.size() : count;

        for (int i = 0; i < readingsCount; i++) {
            output += String.format("%s: %f\n", dataType, readings.get(i).temperature());
        }

        return output;
    }

    @PostMapping(value = "/post")
    public String postData(@RequestBody Reading reading) {
       readings.add(reading);
       return "Data received successfully.";
    }
}
