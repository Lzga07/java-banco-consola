package com.luis.banco.service;

import com.luis.banco.model.*;
import com.luis.banco.exception.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Servicio que administra las operaciones del banco.
// Permite crear cuentas, consultar saldo, retirar dinero
// y obtener el historial de transacciones.
public class BancoService {

    private Map<String, Cuenta> cuentas;
    private Cajero cajero;

    // Inicializa el servicio con un cajero.
    // Las cuentas se almacenan en memoria usando un HashMap.
    public BancoService(Cajero cajero){
        this.cuentas = new HashMap<>();
        this.cajero = cajero;
    }

    // Crea una nueva cuenta si el número no existe previamente.
    public void crearCuenta(String numeroCuenta, int saldoInicial){
        if (cuentas.containsKey(numeroCuenta)){
            throw new CuentaYaExisteException(numeroCuenta);
        }
        cuentas.put(numeroCuenta, new Cuenta(saldoInicial));
    }

    // Devuelve el saldo actual de la cuenta indicada.
    public double verSaldo(String numeroCuenta){
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        return cuenta.getSaldo();
    }

    // Realiza un retiro desde una cuenta.
    // Valida que exista la cuenta y que tenga saldo suficiente.
    // Registra la transacción en el historial.
    public ResultadoRetiro retirar(String numeroCuenta, int monto){

        Cuenta cuenta = obtenerCuenta(numeroCuenta);

        if(monto > cuenta.getSaldo()){
            throw new SaldoInsuficienteException();
        }
        ResultadoRetiro resultado = cajero.retirar(monto);

        if (!resultado.esExitoso()) {
            throw new CajeroSinBilletesException();
        }

        cuenta.debitar(monto);
        return resultado;
    }

    public void depositar(String numeroCuenta, int monto){
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        cuenta.depositar(monto);
    }

    public void transferir(String origen, String destino, int monto){

        if (origen.equals(destino)){
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        Cuenta cuentaOrigen = obtenerCuenta(origen);
        Cuenta cuentaDestino = obtenerCuenta(destino);

        cuentaOrigen.registrarTransferenciaEnviada(monto, destino);
        cuentaDestino.registrarTransferenciaRecibida(monto, origen);
    }

    // Busca una cuenta por su número.
    // Lanza excepción si no existe.
    private Cuenta obtenerCuenta(String numeroCuenta){
        Cuenta cuenta = cuentas.get(numeroCuenta);

        if (cuenta == null){
            throw new CuentaNoEncontradaException(numeroCuenta);
        }
        return cuenta;
    }

    public Set<String> obtenerCuentas(){
        return cuentas.keySet();
    }

    public boolean existeCuenta(String numeroCuenta){
        return cuentas.containsKey(numeroCuenta);
    }

    // Devuelve el historial de transacciones de la cuenta.
    public List<Transaccion> obtenerHistorial(String numeroCuenta){
            Cuenta cuenta = obtenerCuenta(numeroCuenta);
            return cuenta.getHistorial();
    }
}
