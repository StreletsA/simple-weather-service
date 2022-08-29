package com.streletsa.weatherservice.service;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.repository.WeatherRepository;
import com.streletsa.weatherservice.service.parser.WeatherParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherParser weatherParser;


    public Optional<Weather> getCurrentWeather() throws IOException {

        Optional<Weather> weatherOptional = weatherRepository.findById(LocalDate.now().toString());

        if (weatherOptional.isPresent())
            return weatherOptional;

        weatherOptional = weatherParser.parseCurrentWeather();
        weatherOptional.ifPresent(weatherRepository::save);

        return weatherOptional;

    }

    public List<Weather> getWeatherHistory(){
        return weatherRepository.findAll();
    }

}
