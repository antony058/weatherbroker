package ru.bellintegrator.weatherbroker.server.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.weatherbroker.server.city.model.City;
import ru.bellintegrator.weatherbroker.server.city.service.CityService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/city", produces = APPLICATION_JSON_VALUE)
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/list/{city}", method = {RequestMethod.GET})
    public ResponseEntity<List<City>> getCitiesByName(@PathVariable String city) {
        List<City> cities = cityService.getCitiesByName(city);
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK); // ERROR
    }
}
