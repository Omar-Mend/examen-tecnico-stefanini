package com.examen.stefanini.context.pet.domain.exceptions;

import org.springframework.web.client.RestClientResponseException;

public class GeneralException extends Exception {

    private static final String DEFAULT_MESSAGE = "Unexpected error";

    private final int httpStatusCode;

    public GeneralException(int httpStatusCode, Exception e) {
        super(resolveMessage(e), e);
        this.httpStatusCode = httpStatusCode;
    }

    public GeneralException(int httpStatusCode, String message) {
        super(resolveMessage(message));
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public int getCode() {
        return httpStatusCode;
    }

    private static String resolveMessage(Exception exception) {
        if (exception instanceof RestClientResponseException restClientResponseException
                && !restClientResponseException.getResponseBodyAsString().isBlank()) {
            return restClientResponseException.getResponseBodyAsString();
        }

        return resolveMessage(exception.getMessage());
    }

    private static String resolveMessage(String message) {
        if (message == null || message.isBlank()) {
            return DEFAULT_MESSAGE;
        }

        return message;
    }
}
