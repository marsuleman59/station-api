package com.marsuleman59.stationapi.exceptions;

public class StationNotFoundException extends  RuntimeException {

    public StationNotFoundException(String message) {
        super(message);
    }

    public StationNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
