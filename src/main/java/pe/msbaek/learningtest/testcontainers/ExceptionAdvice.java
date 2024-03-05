package pe.msbaek.learningtest.testcontainers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CustomErrorResponse> handleJpaSystemException(DataAccessException e) {
        System.out.println("e = " + e.getCause());
        System.out.println("e = " + e);
        CustomErrorResponse error =
                new CustomErrorResponse(e.getClass().getSimpleName(), e.getMessage()
                        , LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

record CustomErrorResponse(String exceptionClassName, String message, LocalDateTime timestamp) {
}