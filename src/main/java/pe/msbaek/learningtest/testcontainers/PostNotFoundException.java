package pe.msbaek.learningtest.testcontainers;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Post Not Found", code = org.springframework.http.HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
}