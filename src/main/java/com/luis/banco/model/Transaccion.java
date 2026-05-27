package com.luis.banco.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Representa una transacción realizada en una cuenta bancaria.
// Guarda el tipo (depósito o retiro), el monto y la fecha en que se realizó.
public class Transaccion {

    private final TipoTransaccion tipo;
    private final int monto;
    private final LocalDateTime fecha;
    private final String cuentaRelacionada;

    // Crea una nueva transacción con el tipo y monto indicados.
    // La fecha se asigna automáticamente al momento de creación.
    public Transaccion(TipoTransaccion tipo, int monto){
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo");
        }

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.cuentaRelacionada = null;
    }

    public Transaccion(TipoTransaccion tipo, int monto, String cuentaRelacionada){
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo");
        }

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.cuentaRelacionada = cuentaRelacionada;
    }

    // Devuelve el tipo de transacción.
    public TipoTransaccion getTipo(){
        return tipo;
    }

    // Devuelve el monto de la transacción.
    public int getMonto(){
        return monto;
    }

    // Devuelve la fecha en que se realizó la transacción.
    public LocalDateTime getFecha(){
        return fecha;
    }

    // Devuelve una representación en texto de la transacción
    // con formato de fecha legible.
    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String base = "[" + fecha.format(formatter) + "] " + tipo + " -> $" + monto;
        if (cuentaRelacionada != null) {
            base += " (cuenta: " + cuentaRelacionada + ")";
        }
        return base;
    }
}
