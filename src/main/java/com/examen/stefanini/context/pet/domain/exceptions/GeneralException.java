package com.examen.stefanini.context.pet.domain.exceptions;

public class GeneralException extends Exception {

    private final int httpStatusCode;

    public GeneralException() {
        this(500, "Unexpected error");
    }

    public GeneralException(Exception e) {
        this(500, validateMessage(e), e);
    }

    public GeneralException(int httpStatusCode) {
        this(httpStatusCode, "Unexpected error");
    }

    public GeneralException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public GeneralException(int httpStatusCode, String message, Throwable cause) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public int getCode() {
        return httpStatusCode;
    }

    private static String validateMessage(Exception exception) {
        if (exception.getMessage() == null || exception.getMessage().isBlank()) {
            return "Unexpected error";
        }

        return exception.getMessage();
    }
}
