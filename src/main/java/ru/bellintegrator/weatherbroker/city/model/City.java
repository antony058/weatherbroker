package ru.bellintegrator.weatherbroker.city.model;

import ru.bellintegrator.weatherbroker.weather.model.Weather;

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

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Weather> weathers;

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

    public Set<Weather> getWeathers() {
        if (weathers == null) {
            weathers = new HashSet<Weather>();
        }

        return weathers;
    }

    public void setWeathers(Set<Weather> weathers) {
        this.weathers = weathers;
    }

    public void addWeather(Weather weather) {
        weathers.add(weather);
        weather.setCity(this);
    }

    public void removeWeather(Weather weather) {
        weathers.remove(weather);
        weather.setCity(this);
    }
}
