package com.streletsa.weatherservice.parser.impl;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.error.WebResourceWeatherGettingException;
import com.streletsa.weatherservice.parser.WeatherParser;
import com.streletsa.weatherservice.parser.WebResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Сервис по получению погоды с ресурса 'www.timeanddate.com'
 */
@Component
public class TimeAndDateWeatherParser implements WeatherParser {
    private static final WebResource WEB_RESOURCE = WebResource.TIME_AND_DATE;
    private static final String START_CHAR_SEQUENCE = "h2";
    private static final Character STOP_CHAR = '&';

    @Override
    public Optional<Weather> parseCurrentWeather() {
        Weather weather = new Weather();

        try {
            weather.setWeatherDate(LocalDate.now().toString());
            weather.setWeatherValue(getWeatherValue());
        } catch (IOException e) {
            throw new WebResourceWeatherGettingException();
        }

        return Optional.of(weather);
    }

    @Override
    public WebResource getWebResource() {
        return WEB_RESOURCE;
    }

    private String getWeatherValue() throws IOException {
        String content = getContent();

        return parseWeatherValueFromContent(content);
    }

    private String getContent() throws IOException {
        final String url = WEB_RESOURCE.getUrl();

        URLConnection connection = new URL(url).openConnection();

        return getContentFromInputStream(connection.getInputStream());
    }

    private String getContentFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();

        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }

        return content.toString();
    }

    private String parseWeatherValueFromContent(String content) {
        StringBuilder weatherValue = new StringBuilder();

        int index = content.indexOf(START_CHAR_SEQUENCE) + 3;

        while (content.charAt(index) != STOP_CHAR) {
            weatherValue.append(content.charAt(index));
            index++;
        }

        return weatherValue.toString();
    }
}
