package com.example.parcialw3.negocio.exceptions;

public class NoEncontradoException extends Exception{

    public NoEncontradoException() {
    }

    public NoEncontradoException(String message) {
        super(message);
    }

    public NoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEncontradoException(Throwable cause) {
        super(cause);
    }

    protected NoEncontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
