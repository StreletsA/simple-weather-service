package com.streletsa.weatherservice.dto;

import lombok.Data;

@Data
public class WeatherDto {
    /**
     * Дата представления погоды
     */
    private String weatherDate;

    /**
     * Температура в градусах цельсия
     */
    private String weatherValue;
}
