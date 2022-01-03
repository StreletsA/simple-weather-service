package com.streletsa.weatherservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weather_history")
@Getter
@Setter
public class Weather {

    @Id
    String weatherDate;
    String weatherValue;

}
