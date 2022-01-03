package com.streletsa.weatherservice.service.parser;

import com.streletsa.weatherservice.entity.Weather;
import com.streletsa.weatherservice.service.parser.WeatherParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import javax.swing.text.html.parser.ParserDelegator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class YandexWeatherParser implements WeatherParser {

    private final String yandexUrl = "https://yandex.ru/";

    private HttpClient httpClient;
    private HttpRequest httpRequest;

    public YandexWeatherParser() throws IOException, InterruptedException {

        httpClient = HttpClient.newHttpClient();
        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(yandexUrl))
                .GET()
                .build();

    }

    @Override
    public Optional<Weather> parseCurrentWeather() {

        Weather weather = new Weather();

        try {

            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();

            int index = responseBody.indexOf("weather__temp") + 15;
            StringBuilder value = new StringBuilder();

            while (responseBody.charAt(index) != '\u00B0'){
                value.append(responseBody.charAt(index));
                index++;
            }

            weather.setWeatherDate(LocalDate.now().toString());
            weather.setWeatherValue(value.toString());

        } catch (InterruptedException | IOException ignored) {
            weather = null;
        }

        return Optional.of(weather);

    }
}
