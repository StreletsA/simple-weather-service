package com.streletsa.weatherservice.service.parser;


import com.streletsa.weatherservice.entity.Weather;

import java.io.IOException;
import java.util.Optional;

/**
 * Сервис по получению погоды из веб-ресурса
 */
public interface WeatherParser {

    Optional<Weather> parseCurrentWeather() throws IOException;

}
