package com.vallettatourcompany.stubs;

import com.vallettatourcompany.weather.WeatherService;

public class WeatherServiceNotFavourable implements WeatherService {
    public boolean isSeaFavourable() {
        return false;
    }
}
