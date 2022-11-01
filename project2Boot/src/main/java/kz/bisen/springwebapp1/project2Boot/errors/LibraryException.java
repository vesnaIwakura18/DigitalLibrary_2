package kz.bisen.springwebapp1.project2Boot.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LibraryException extends ResponseStatusException {

    public LibraryException(HttpStatus status, String reason) {
        super(status, reason);
    }
}