## Weather Station Server

This is a program for a local server for the ESP32 weather station, written in Java using the Spring Web MVC framework.

It is meant to be run together with two other programs, namely:

* The [weather-station](https://github.com/jemehgoh/weather-station) program running on an ESP32-S2.
* The [weather-station-frontend](https://github.com/jemehgoh/weather-station-frontend) program running locally on your machine.

## Description

The weather station comprises three components, namely:
* An ESP32-S2 MCU which takes in data from two sensors and transmits it to the local server,
* A local server, which stores the sensor data received,
* A web frontend, which shows the sensor data to the user.

The sensor data is sent from the ESP32 to the local server using Wi-Fi. 

## Quick start

1. Ensure that you have Java 21 or above installed.

2. Download the latest version of `WeatherStationServer` from [here](https://github.com/jemehgoh/weatherstationserver/releases) 

3. Copy the JAR into a new folder

4. Connect your computer to the Wi-Fi network you have set up your ESP32 to connect to. 

5. Open a terminal in folder your JAR is in, and run the program with the following command: 
```
java -jar weather-station-server
```


