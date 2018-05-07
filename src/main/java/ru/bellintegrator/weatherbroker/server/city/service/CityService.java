package ru.bellintegrator.weatherbroker.server.city.service;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.city.model.City;
import ru.bellintegrator.weatherbroker.server.city.view.CityView;

import java.util.List;

public interface CityService {
    /*
    * Создает Entity объект города и добавляет его в базу данных
    *
    * @param cityName - название города
     */
    void save(String cityName);

    /*
    * Загружает город из базы данных по названию и возвращает его Entity
    *
    * @param cityName - название города
    * @return возвращается Entity класс найденного города
    *
    * @throws NotFoundException Если запрашиваемый город не найден
     */
    City loadByName(String cityName) throws NotFoundException;

    /*
     * Загружает из базы данных список Entity объектов городов, которые содержат в своем названии
     * строку входного параметра. Полученные Entity маппятся на представление {@link CityView}
     *
     * @param cityName - название города
     *
     * return возвращается список представлений найденных городов
     */
    List<CityView> getCitiesLikeName(String cityName);
}
