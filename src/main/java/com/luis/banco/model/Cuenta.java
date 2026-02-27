package com.luis.banco.model;

import java.util.ArrayList;
import java.util.List;

// Representa una cuenta bancaria.
// Administra el saldo actual y el historial de transacciones realizadas
public class Cuenta {

    private int saldo;

    private List<Transaccion> historial;

    // Crea una cuenta con un saldo inicial.
    // No se permite un saldo inicial negativo.
    public Cuenta(int saldoInicial){
        if(saldoInicial < 0){
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }

    // Devuelve el saldo actual de la cuenta.
    public int getSaldo(){
        return saldo;
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
    }

    // Realiza un depósito en la cuenta.
    // El monto debe ser mayor a cero.
    public void depositar(int monto){
        if (monto <= 0){
            throw new IllegalArgumentException("El monto debe ser mayor a cero.");
        }

        saldo += monto;
    }

    // Agrega una transacción al historial de la cuenta.
    public void agregarTransaccion(Transaccion transaccion){
        historial.add(transaccion);
    }

    // Devuelve la lista de transacciones realizadas.
    public List<Transaccion> getHistorial(){
        return historial;
    }
}
