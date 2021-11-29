package com.clever;

public class IncorrectDataException extends RuntimeException{
    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
