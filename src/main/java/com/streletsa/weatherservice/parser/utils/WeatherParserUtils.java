package com.streletsa.weatherservice.parser.utils;

import com.streletsa.weatherservice.parser.WebResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Утильный класс для получения погоды из веб-ресурсов
 */
public final class WeatherParserUtils {

    /**
     * Получение значения температуры из веб-ресурса
     *
     * @param webResource     тип веб-ресурса
     * @param startTag        тэг для начала парсинга
     *
     * @return значение температуры
     *
     * @throws IOException при ошибке парсинга
     */
    public static String getWeatherValue(WebResource webResource,
                                         String startTag) throws IOException {
        int startIndexDelta = startTag.length() + 2;
        char stopChar = '<';

        return getWeatherValue(webResource, startTag, startIndexDelta, stopChar);
    }

    /**
     * Получение значения температуры из веб-ресурса
     *
     * @param webResource     тип веб-ресурса
     * @param startTag        тэг для начала парсинга
     * @param stopChar        символ для прекращения парсинга
     *
     * @return значение температуры
     *
     * @throws IOException при ошибке парсинга
     */
    public static String getWeatherValue(WebResource webResource,
                                         String startTag,
                                         char stopChar) throws IOException {
        int startIndexDelta = startTag.length() + 1;

        return getWeatherValue(webResource, startTag, startIndexDelta, stopChar);
    }

    /**
     * Получение значения температуры из веб-ресурса
     *
     * @param webResource     тип веб-ресурса
     * @param startTag        тэг для начала парсинга
     * @param startIndexDelta количество символов от начала тэга до необходимого значения
     * @param stopChar        символ для прекращения парсинга
     *
     * @return значение температуры
     *
     * @throws IOException при ошибке парсинга
     */
    public static String getWeatherValue(WebResource webResource,
                                         String startTag,
                                         int startIndexDelta,
                                         char stopChar) throws IOException {
        String content = getContent(webResource);

        return parseWeatherValueFromContent(content, startTag, startIndexDelta, stopChar);
    }

    /**
     * Метод для получения HTML-кода с ресурса
     *
     * @param webResource тип веб-ресурса
     *
     * @return HTML-код страницы
     *
     * @throws IOException при ошибке получения кода страницы
     */
    public static String getContent(WebResource webResource) throws IOException {
        final String url = webResource.getUrl();

        URLConnection connection = new URL(url).openConnection();

        return getContentFromInputStream(connection.getInputStream());
    }

    /**
     * Метод для конвертации полученного набора байтов в HTML-код
     *
     * @param inputStream набор байтов
     *
     * @return HTML-код страницы
     *
     * @throws IOException при ошибке конвертации набора байтов в HTML-код
     */
    public static String getContentFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();

        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }

        return content.toString();
    }

    /**
     * Получение значения температуры из HTML-кода страницы
     *
     * @param content         HTML-код страницы
     * @param startTag        тэг для начала парсинга
     * @param startIndexDelta количество символов от начала тэга до необходимого значения
     * @param stopChar        символ для прекращения парсинга
     *
     * @return значение температуры
     */
    public static String parseWeatherValueFromContent(String content,
                                                      String startTag,
                                                      int startIndexDelta,
                                                      char stopChar) {
        StringBuilder weatherValue = new StringBuilder();

        int index = content.indexOf(startTag) + startIndexDelta;

        while (content.charAt(index) != stopChar) {
            weatherValue.append(content.charAt(index));
            index++;
        }

        return weatherValue.toString();
    }
}
