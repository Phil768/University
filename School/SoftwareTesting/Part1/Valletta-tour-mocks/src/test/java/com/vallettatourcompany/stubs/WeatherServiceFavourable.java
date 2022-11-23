package com.vallettatourcompany.stubs;

import com.vallettatourcompany.weather.WeatherService;

public class WeatherServiceFavourable implements WeatherService {
    public boolean isSeaFavourable() {
        return true;
    }
}
