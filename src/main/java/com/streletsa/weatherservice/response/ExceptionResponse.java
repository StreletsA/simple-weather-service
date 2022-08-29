package com.streletsa.weatherservice.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Ошибочный ответ
 */
@Data
public class ExceptionResponse {
    private final int status;
    private final String message;

    public ExceptionResponse(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }
}
