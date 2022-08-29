package com.streletsa.weatherservice.controller;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<Weather> getCurrentWeather() throws IOException {

        Optional<Weather> weatherOptional = weatherService.getCurrentWeather();

        return weatherOptional.map(weather -> new ResponseEntity<>(weather, HttpStatus.ACCEPTED))
                              .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED));
    }

    @GetMapping("/history")
    public List<Weather> getWeatherHistory(){
        return weatherService.getWeatherHistory();
    }

}
