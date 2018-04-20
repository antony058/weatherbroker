package ru.bellintegrator.weatherbroker.server.city.model;

import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String cityName;

    @OneToOne(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Weather weather;

    public City() {

    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
