package com.streletsa.weatherservice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Успешный запрос
 *
 * @param <T> тип возвращаемого объекта
 */
public class OkResponse<T> extends ResponseEntity<T> {
    public OkResponse(T body) {
        super(body, HttpStatus.ACCEPTED);
    }
}
