package kz.bisen.springwebapp1.project2Boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReaderBookNotFoundException extends ResponseStatusException {
    public ReaderBookNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}