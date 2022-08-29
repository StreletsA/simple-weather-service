package com.streletsa.weatherservice.service;

import com.streletsa.weatherservice.entity.Weather;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с погодой
 */
public interface WeatherService {
    /**
     * Метод для получения актуальной погоды из хранилища или веб-ресурса - при отсутствии в хранилище
     *
     * @return актуальная погода
     *
     * @throws IOException при ошибке получения погоды
     */
    Optional<Weather> getCurrentWeather() throws IOException;

    /**
     * Получение истории наблюдения за погодой
     *
     * @return список погоды для всех наблюдаемых дней
     */
    List<Weather> getWeatherHistory();
}
