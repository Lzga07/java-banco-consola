package com.luis.banco.exception;

public class PinIncorrectoException extends RuntimeException {
    public PinIncorrectoException() {
        super("PIN incorrecto.");
    }
}
