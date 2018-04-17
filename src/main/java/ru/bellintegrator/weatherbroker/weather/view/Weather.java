package ru.bellintegrator.weatherbroker.weather.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedHashMap;

public class Weather {
    private String date;
    private String temp;
    private String text;

    public Weather() {

    }

    public Weather(String date, String temp, String text) {
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
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "date: " + date + "\ntemp: " + temp + "\ntext: " + text;
    }

    @SuppressWarnings("unchecked")
    public static Weather mapToWeather(Query query) {
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

        return new Weather(
                (String) weatherData.get("date"),
                (String) weatherData.get("temp"),
                (String) weatherData.get("text"));
    }
}
