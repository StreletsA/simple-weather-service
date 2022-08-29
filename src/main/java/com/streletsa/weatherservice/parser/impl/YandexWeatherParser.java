package com.streletsa.weatherservice.parser.impl;

import com.streletsa.weatherservice.parser.WeatherParser;
import com.streletsa.weatherservice.parser.WebResource;
import org.springframework.stereotype.Component;

/**
 * Сервис по получению погоды с ресурса 'www.yandex.ru'
 */
@Component
public class YandexWeatherParser extends WeatherParser {
    private static final WebResource WEB_RESOURCE = WebResource.YANDEX;
    private static final String START_TAG = "weather__temp";


    public YandexWeatherParser() {
        super(WEB_RESOURCE, START_TAG);
    }
}
