package pt.ua.tqs.openair.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cache Service Unavailable")
public class ServiceUnavailableException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceUnavailableException(String errorMessage) {
        super(errorMessage);
    }

}