package com.streletsa.weatherservice.error;

public class RepositoryWeatherGettingException extends ServiceException {
    public RepositoryWeatherGettingException() {
        super(ErrorCode.REPOSITORY_WEATHER_GETTING_ERROR);
    }
}
