package com.streletsa.weatherservice.error;

/**
 * Ошибка при получении погоды
 */
public class WebResourceWeatherGettingException extends ServiceException {
    public WebResourceWeatherGettingException() {
        super(ErrorCode.WEB_RESOURCE_WEATHER_GETTING_ERROR);
    }
}
