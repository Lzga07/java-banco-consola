package com.luis.banco.model;

import com.luis.banco.exception.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

// Representa una cuenta bancaria.
// Administra el saldo actual y el historial de transacciones realizadas
public class Cuenta {

    private double saldo;
    private final String titular;
    private final String pin;

    private List<Transaccion> historial;

    // Crea una cuenta con un saldo inicial.
    // No se permite un saldo inicial negativo.
    public Cuenta(String titular, String pin, int saldoInicial){
        if(saldoInicial < 0){
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular no puede estar vacío");
        }
        if (pin == null || pin.isBlank()) {
            throw new IllegalArgumentException("El PIN no puede estar vacío");
        }
        this.titular = titular;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }

    // Devuelve el saldo actual de la cuenta.
    public double getSaldo(){
        return saldo;
    }

    public String getTitular(){
        return titular;
    }

    public boolean validarPin(String pinIngresado){
        return this.pin.equals(pinIngresado);
    }

    // Realiza un débito (retiro) del saldo.
    // Valida que el monto sea positivo y que haya saldo suficiente
    public void debitar(int monto){
        if(monto <= 0){
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        if (monto > saldo){
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        saldo -= monto;
        historial.add(new Transaccion(TipoTransaccion.RETIRO,monto));
    }

    // Realiza un depósito en la cuenta.
    // El monto debe ser mayor a cero.
    public void depositar(int monto){
        if (monto <= 0){
            throw new IllegalArgumentException("El monto debe ser mayor a cero.");
        }

        saldo += monto;
        historial.add(new Transaccion(TipoTransaccion.DEPOSITO,monto));
    }

    // Registra una transferencia enviada hacia otra cuenta
    public void registrarTransferenciaEnviada(int monto, String cuentaDestino){
        if (monto > saldo){
            throw new SaldoInsuficienteException();
        }
        saldo -= monto;
        historial.add(new Transaccion(TipoTransaccion.TRANSFERENCIA_ENVIADA, monto, cuentaDestino));
    }

    // Registra una transferencia recibida desde otra cuenta
    public void registrarTransferenciaRecibida(int monto, String cuentaOrigen){
        saldo += monto;
        historial.add(new Transaccion(TipoTransaccion.TRANSFERENCIA_RECIBIDA, monto, cuentaOrigen));
    }

    // Agrega una transacción al historial de la cuenta.
    public void agregarTransaccion(Transaccion transaccion){
        historial.add(transaccion);
    }

    // Devuelve la lista de transacciones realizadas.
    public List<Transaccion> getHistorial(){
        return List.copyOf(historial);
    }
}
