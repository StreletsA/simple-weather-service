package com.streletsa.weatherservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "weather_history")
public class Weather {
    /**
     * Дата представления погоды
     */
    @Id
    String weatherDate;

    /**
     * Температура в градусах цельсия
     */
    String weatherValue;
}
