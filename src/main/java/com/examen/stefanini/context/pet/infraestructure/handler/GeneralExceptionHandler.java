package com.examen.stefanini.context.pet.infraestructure.handler;

import com.examen.stefanini.context.pet.domain.exceptions.GeneralException;
import java.time.OffsetDateTime;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(GeneralException exception) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(exception.getHttpStatusCode()))
                .body(Map.of(
                        "timestamp", OffsetDateTime.now(),
                        "status", exception.getHttpStatusCode(),
                        "message", exception.getMessage()
                ));
    }
}
