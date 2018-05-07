package ru.bellintegrator.weatherbroker.server.weather.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.weatherbroker.server.weather.service.WeatherService;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /*
    * Метод срабатывает по url <i>/weather/{city}</i> и возвращает погоду для заданного города
    *
    * @param city - название города
    * @return записывает результат о погоде, полученный из БД, в тело HTTP-ответа
    *
    * @throws NotFoundException Если погода для заданного города не найдена в БД
     */
    @RequestMapping(value = "/weather/{city}", method = {RequestMethod.GET})
    public ResponseEntity<WeatherView> getWeather(@PathVariable String city) throws NotFoundException {
        WeatherView weatherView = weatherService.getCityWeather(city);

        return new ResponseEntity<WeatherView>(weatherView, HttpStatus.OK);
    }
}