package com.marsuleman59.stationapi.exceptions;

public class DuplicateStationException extends RuntimeException {

    public DuplicateStationException(String message) {
        super(message);
    }

    public DuplicateStationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
