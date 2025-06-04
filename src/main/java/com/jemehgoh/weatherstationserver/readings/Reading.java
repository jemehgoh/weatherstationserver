package com.jemehgoh.weatherstationserver.readings;

public record Reading(String time, double temperature, double pressure, double humidity,
        int co2Ppm, int tvoc) {

}
