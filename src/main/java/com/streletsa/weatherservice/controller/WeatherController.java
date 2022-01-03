package com.streletsa.weatherservice.controller;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<Weather> getCurrentWeather(){

        Optional<Weather> weatherOptional = weatherService.getCurrentWeather();

        return weatherOptional.map(weather -> new ResponseEntity<>(weather, HttpStatus.ACCEPTED))
                              .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED));
    }

    @GetMapping("/weather/history")
    public List<Weather> getWeatherHistory(){
        return weatherService.getWeatherHistory();
    }

}
