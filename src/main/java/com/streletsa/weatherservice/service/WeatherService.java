package com.streletsa.weatherservice.service;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.repository.WeatherRepository;
import com.streletsa.weatherservice.service.parser.WeatherParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherParser weatherParser;


    public Optional<Weather> getCurrentWeather(){

        Optional<Weather> weatherOptional = weatherRepository.findById(LocalDate.now().toString());

        if (weatherOptional.isPresent())
            return weatherOptional;

        weatherOptional = weatherParser.parseCurrentWeather();
        weatherOptional.ifPresent(weather -> weatherRepository.save(weather));

        return weatherOptional;

    }

    public List<Weather> getWeatherHistory(){

        return weatherRepository.findAll();

    }

}
