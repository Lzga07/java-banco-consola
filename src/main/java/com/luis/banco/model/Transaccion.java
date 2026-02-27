package com.luis.banco.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Representa una transacción realizada en una cuenta bancaria.
// Guarda el tipo (depósito o retiro), el monto y la fecha en que se realizó.
public class Transaccion {

    private TipoTransaccion tipo;
    private int monto;
    private LocalDateTime fecha;

    // Crea una nueva transacción con el tipo y monto indicados.
    // La fecha se asigna automáticamente al momento de creación.
    public Transaccion(TipoTransaccion tipo, int monto){
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
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
        return fecha.format(formatter) + " - " + tipo + " - $" + monto;
    }
}
