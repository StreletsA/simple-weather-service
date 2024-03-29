package com.streletsa.weatherservice.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Веб-ресурсы для получения погоды
 */
@Getter
@RequiredArgsConstructor
public enum WebResource {
    /**
     * www.timeanddate.com
     */
    TIME_AND_DATE("https://www.timeanddate.com/weather/russia/moscow"),

    /**
     * www.yandex.ru
     */
    YANDEX("https://www.yandex.ru");

    private final String url;
}
