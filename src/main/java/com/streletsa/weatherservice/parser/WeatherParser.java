package com.streletsa.weatherservice.parser;


import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.error.WebResourceWeatherGettingException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static com.streletsa.weatherservice.parser.utils.WeatherParserUtils.getWeatherValue;

/**
 * Сервис по получению погоды из веб-ресурса
 */
public abstract class WeatherParser {
    private final WebResource webResource;
    private final String startTag;
    private final Integer startIndexDelta;
    private final Character stopChar;

    public WeatherParser(WebResource webResource, String startTag) {
        this(webResource, startTag, null);
    }

    public WeatherParser(WebResource webResource, String startTag, Character stopChar) {
        this(webResource, startTag, null, stopChar);
    }

    public WeatherParser(WebResource webResource, String startTag, Integer startIndexDelta,  Character stopChar) {
        this.webResource = webResource;
        this.startTag = startTag;
        this.startIndexDelta = startIndexDelta;
        this.stopChar = stopChar;
    }

    /**
     * Метод для получения погоды из веб-ресурса
     *
     * @return погода
     *
     * @throws IOException при ошибке получения погоды
     */
    public Optional<Weather> parseCurrentWeather() {
        Weather weather = new Weather();

        try {
            weather.setWeatherDate(LocalDate.now().toString());

            if (startIndexDelta == null && stopChar == null) {
                weather.setWeatherValue(getWeatherValue(webResource, startTag));
            } else if (startIndexDelta == null) {
                weather.setWeatherValue(getWeatherValue(webResource, startTag, stopChar));
            } else {
                weather.setWeatherValue(getWeatherValue(webResource, startTag, startIndexDelta, stopChar));
            }
        } catch (IOException e) {
            throw new WebResourceWeatherGettingException();
        }

        return Optional.of(weather);
    }

    /**
     * Веб-ресурс, для которого предназначен сервис
     *
     * @return веб-ресурс
     */
    public WebResource getWebResource() {
        return webResource;
    }
}
