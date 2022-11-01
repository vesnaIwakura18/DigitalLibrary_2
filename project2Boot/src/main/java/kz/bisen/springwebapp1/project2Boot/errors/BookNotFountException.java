package kz.bisen.springwebapp1.project2Boot.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFountException extends ResponseStatusException {
    public BookNotFountException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
