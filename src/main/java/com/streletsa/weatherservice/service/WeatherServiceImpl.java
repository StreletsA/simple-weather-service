package com.streletsa.weatherservice.service;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.repository.WeatherRepository;
import com.streletsa.weatherservice.parser.WeatherParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherParser weatherParser;

    public WeatherServiceImpl(WeatherRepository weatherRepository,
                              @Qualifier("timeAndDateWeatherParser") WeatherParser weatherParser) {
        this.weatherRepository = weatherRepository;
        this.weatherParser = weatherParser;
    }

    @Override
    public Optional<Weather> getCurrentWeather() {

        Optional<Weather> weatherOptional = weatherRepository.findById(LocalDate.now().toString());

        if (weatherOptional.isPresent()) {
            return weatherOptional;
        }

        weatherOptional = weatherParser.parseCurrentWeather();
        weatherOptional.ifPresent(weatherRepository::save);

        return weatherOptional;
    }

    @Override
    public List<Weather> getWeatherHistory(){
        return weatherRepository.findAll();
    }

}
