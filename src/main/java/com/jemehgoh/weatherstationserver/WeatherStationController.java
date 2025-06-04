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
        int readingCount = (readings.size() < count) ? readings.size() : count;
        return getReadings(dataType, readingCount);
    }

    @PostMapping(value = "/post")
    public String postData(@RequestBody Reading reading) {
       readings.add(reading);
       return "Data received successfully.";
    }

    /**
     * Returns a string with all requested readings, given the data type and reading count.
     */
    private String getReadings(String dataType, int readingCount) {
        return switch(dataType.toLowerCase()) {
            case "time" -> getTimeStamps(readingCount);
            case "temperature" -> getTemperatureReadings(readingCount);
            case "pressure" -> getPressureReadings(readingCount);
            case "humidity" -> getHumidityReadings(readingCount);
            case "co2ppm" -> getCo2PpmReadings(readingCount);
            case "tvoc" -> getTvocReadings(readingCount);
            default -> "Invalid data type";
        };
    }

    private String getTimeStamps(int readingCount) {
        String output = "Timestamps:\n";

        for (int i = 0; i < readingCount; i++) {
            output += String.format("%s: %s\n", "time", readings.get(i).time());
        }

        return output;
    }

    private String getTemperatureReadings(int readingCount) {
        String output = "Readings:\n";

        for (int i = 0; i < readingCount; i++) {
            output += String.format("%s: %f\n", "temperature", readings.get(i).temperature());
        }

        return output;
    }

    private String getPressureReadings(int readingCount) {
        String output = "Readings:\n";

        for (int i = 0; i < readingCount; i++) {
            output += String.format("%s: %f\n", "pressure", readings.get(i).pressure());
        }

        return output;
    }

    private String getHumidityReadings(int readingCount) {
        String output = "Readings:\n";

        for (int i = 0; i < readingCount; i++) {
            output += String.format("%s: %f\n", "humidity", readings.get(i).humidity());
        }

        return output;
    }

    private String getCo2PpmReadings(int readingCount) {
        String output = "Readings:\n";

        for (int i = 0; i < readingCount; i++) {
            output += String.format("%s: %d\n", "CO2 ppm", readings.get(i).co2Ppm());
        }

        return output;
    }

    private String getTvocReadings(int readingCount) {
        String output = "Readings:\n";

        for (int i = 0; i < readingCount; i++) {
            output += String.format("%s: %d\n", "TVOC", readings.get(i).tvoc());
        }

        return output;
    }
}
