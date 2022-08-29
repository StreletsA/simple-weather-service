package com.streletsa.weatherservice.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /**
     * Ошибка получения погоды из веб-ресурса
     */
    WEB_RESOURCE_WEATHER_GETTING_ERROR("Ошибка получения погоды из веб-ресурса"),

    /**
     * Ошибка получения погоды из базы данных
     */
    REPOSITORY_WEATHER_GETTING_ERROR("Ошибка получения погоды из базы данных");

    private final String message;
}
