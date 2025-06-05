package com.jemehgoh.weatherstationserver;

import com.jemehgoh.weatherstationserver.readings.Reading;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     *
     * @param count the number of readings requested.
     * @param startsFromTop if the list is ascending or descending
     * @return a list of the first or last count readings, or the entire list, whichever is smaller.
     */
    @GetMapping(value = "/readings", produces = "application/json")
    public List<Reading> getReadings(@RequestParam(name = "count", defaultValue = "10") int count,
            @RequestParam(name = "startsFromTop", defaultValue = "true") boolean startsFromTop) {
        if (count >= readings.size()) {
            return readings;
        }

        if (startsFromTop) {
            return readings.subList(0, count);
        }

        return readings.subList((readings.size() - count), readings.size()).reversed();
    }

    // Receives sensor data from the ESP32 through HTTP POST requests
    @PostMapping(value = "/post")
    public String postData(@RequestBody Reading reading) {
        readings.add(reading);
        return "Data received successfully.";
    }
}

