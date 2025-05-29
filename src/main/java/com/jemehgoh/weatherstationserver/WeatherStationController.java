package com.jemehgoh.weatherstationserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller that handles HTTP requests to the server.
 */

@RestController
public class WeatherStationController {

    @RequestMapping(value="/")
    public String greetUser() {
        return "ESP32 weather station";
    }

    @RequestMapping(value="/data")
    public String getData(@RequestParam(value = "type", defaultValue = "density") String dataType) {
        return String.format("Type: %s\n", dataType);
    }
}
