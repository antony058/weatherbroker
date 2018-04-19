package ru.bellintegrator.weatherbroker.weather.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherbroker.weather.model.Weather;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class WeatherView implements Serializable {
    private String date;
    private Integer temp;
    private String text;
    private String city;

    public WeatherView() {

    }

    public WeatherView(String city, String date, Integer temp, String text) {
        this.city = city;
        this.date = date;
        this.temp = temp;
        this.text = text;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "date: " + date + "\ntemp: " + temp + "\ntext: " + text;
    }

    @SuppressWarnings("unchecked")
    public static WeatherView mapToWeather(Query query, String city) {
        LinkedHashMap<String, Object> weatherData =
                (LinkedHashMap<String, Object>) query.getQuery();
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("results");
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("channel");
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("item");
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("condition");

        return new WeatherView(
                city,
                (String) weatherData.get("date"),
                Integer.valueOf((String) weatherData.get("temp")),
                (String) weatherData.get("text"));
    }

    public static WeatherView mapToWeatherView(Weather weather, String cityName) {
        return new WeatherView(
                cityName,
                null,
                weather.getTemperature(),
                weather.getDescription()
        );
    }
}
