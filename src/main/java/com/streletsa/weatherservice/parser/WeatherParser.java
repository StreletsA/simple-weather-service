package com.streletsa.weatherservice.parser;


import com.streletsa.weatherservice.entity.Weather;

import java.io.IOException;
import java.util.Optional;

/**
 * Сервис по получению погоды из веб-ресурса
 */
public interface WeatherParser {
    /**
     * Метод для получения погоды из веб-ресурса
     *
     * @return погода
     *
     * @throws IOException при ошибке получения погоды
     */
    Optional<Weather> parseCurrentWeather() throws IOException;

    /**
     * Веб-ресурс, для которого предназначен сервис
     *
     * @return веб-ресурс
     */
    WebResource getWebResource();
}
