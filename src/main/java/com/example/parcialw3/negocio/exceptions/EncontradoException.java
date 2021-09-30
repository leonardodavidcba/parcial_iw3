package com.example.parcialw3.negocio.exceptions;

public class EncontradoException extends Exception{

    public EncontradoException() {
    }

    public EncontradoException(String message) {
        super(message);
    }

    public EncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncontradoException(Throwable cause) {
        super(cause);
    }

    protected EncontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
