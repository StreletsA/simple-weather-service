package com.streletsa.weatherservice.parser.impl;

import com.streletsa.weatherservice.parser.WeatherParser;
import com.streletsa.weatherservice.parser.WebResource;
import org.springframework.stereotype.Component;

/**
 * Сервис по получению погоды с ресурса 'www.timeanddate.com'
 */
@Component
public class TimeAndDateWeatherParser extends WeatherParser {
    private static final WebResource WEB_RESOURCE = WebResource.TIME_AND_DATE;
    private static final String START_TAG = "h2";
    private static final Character STOP_CHAR = '&';

    public TimeAndDateWeatherParser() {
        super(WEB_RESOURCE, START_TAG, STOP_CHAR);
    }
}
