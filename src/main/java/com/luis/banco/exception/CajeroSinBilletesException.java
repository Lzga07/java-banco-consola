package com.luis.banco.exception;

public class CajeroSinBilletesException extends RuntimeException {
    public CajeroSinBilletesException() {
        super("El cajero no tiene billetes suficientes para entregar el monto solicitado");
    }
}
