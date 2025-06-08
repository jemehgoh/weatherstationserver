package com.jemehgoh.weatherstationserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jemehgoh.weatherstationserver.readings.Reading;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
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
        try {
            File dataFile = new File("data.json");
            ObjectMapper mapper = new ObjectMapper();
            if (!dataFile.createNewFile()) {
                readings = mapper.readValue(dataFile, new TypeReference<ArrayList<Reading>>() {
                });
            }
            System.out.println("Data file loaded");
        } catch (IOException e) {
            System.out.println("Unable to load data");
            readings = new ArrayList<>();
        }
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
        saveData();
        return "Data received successfully.";
    }

    private void saveData() {
        try {
            File dataFile = new File("data.json");
            ObjectMapper mapper = new ObjectMapper();
            if (dataFile.exists()) {
                mapper.writeValue(dataFile, readings);
            }
        } catch (IOException e) {
            System.out.println("Sensor data cannot be saved");
        }
    }
}

