package com.luis.banco.exception;

public class CuentaYaExisteException extends RuntimeException {
    public CuentaYaExisteException(String numeroCuenta) {
        super("La cuenta ya existe: " + numeroCuenta);
    }
}
