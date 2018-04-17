package ru.bellintegrator.weatherbroker.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.weatherbroker.weather.service.WeatherService;
import ru.bellintegrator.weatherbroker.weather.view.Weather;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/weather/{city}", method = {RequestMethod.GET})
    public ResponseEntity<Weather> getWeather(@PathVariable String city) {
        Weather weather = weatherService.sendWeatherRequest(city);

        return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }
}