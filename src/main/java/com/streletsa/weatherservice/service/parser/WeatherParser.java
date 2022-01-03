package com.streletsa.weatherservice.service.parser;


import com.streletsa.weatherservice.entity.Weather;

import java.util.Optional;

public interface WeatherParser {

    Optional<Weather> parseCurrentWeather();

}
