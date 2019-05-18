package com.equipeor.isepu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EleveIntrouvableException extends RuntimeException {
    public EleveIntrouvableException(String s) {
        super(s);
    }
}
