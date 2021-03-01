package org.example.exception;

public enum ExceptionType {

    UNKOWN_ERROR("00", "An unknown error occurred"),
    ERROR_FILE("01", "Error reading file");

    private final String code;
    private final String description;

    ExceptionType(String code, String description) {
        this.code =  code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}