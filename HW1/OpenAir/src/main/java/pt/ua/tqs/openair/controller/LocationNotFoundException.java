package pt.ua.tqs.openair.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Location Not Found")
public class LocationNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public LocationNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
