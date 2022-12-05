package kz.bisen.springwebapp1.project2Boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends ResponseStatusException {
    public BookNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}