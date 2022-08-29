package com.streletsa.weatherservice.error;

public class ServiceException extends RuntimeException {
    public ServiceException(ErrorCode errorCode, String... params) {
        super(String.format(errorCode.getMessage(), params));
    }
}
