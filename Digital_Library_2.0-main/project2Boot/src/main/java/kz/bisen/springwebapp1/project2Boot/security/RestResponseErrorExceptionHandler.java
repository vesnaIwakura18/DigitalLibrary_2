package kz.bisen.springwebapp1.project2Boot.security;

import kz.bisen.springwebapp1.project2Boot.error.ResponseError;
import kz.bisen.springwebapp1.project2Boot.exception.ReaderBookNotCreatedException;
import kz.bisen.springwebapp1.project2Boot.exception.ReaderBookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class RestResponseErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseError handleAccessDeniedException() {
        return new ResponseError(HttpStatus.FORBIDDEN, "Access denied");
    }

    @ExceptionHandler(value = {ReaderBookNotCreatedException.class, ReaderBookNotFoundException.class})
    private ResponseError handle(ResponseStatusException exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseError(exception.getStatus(), exception.getMessage());
    }
}