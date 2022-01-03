package com.streletsa.weatherservice.repository;

import com.streletsa.weatherservice.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<Weather, String> {
}
