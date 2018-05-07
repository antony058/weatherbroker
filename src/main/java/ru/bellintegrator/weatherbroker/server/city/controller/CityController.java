package ru.bellintegrator.weatherbroker.server.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.weatherbroker.server.city.service.CityService;
import ru.bellintegrator.weatherbroker.server.city.view.CityView;

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

    /*
    * Метод срабатывает по url <i>/city/list/{city}</i> и возвращает список городов,
    * которые содержат в своем названии принимаемую строку
    *
    * @param city - название города или часть этого названия. Является частью url
    * @return записывает полученный список городов из БД в тело HTTP-ответа
     */
    @RequestMapping(value = "/list/{city}")
    public ResponseEntity<List<CityView>> getCitiesLikeName(@PathVariable(value = "city") String city) {
        List<CityView> cityViews = cityService.getCitiesLikeName(city);

        return new ResponseEntity<List<CityView>>(cityViews, HttpStatus.OK);
    }
}
