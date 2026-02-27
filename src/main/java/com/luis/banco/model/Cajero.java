package com.luis.banco.model;

// Representa un cajero automático.
// Administra la cantidad de billetes disponibles
// y procesa retiros entregando distintas denominaciones.
public class Cajero {

    private int billetesMil;
    private int billetesQuinientos;
    private int billetesCien;

    // Inicializa el cajero con una cantidad específica de billetes.
    public Cajero(int mil, int quinientos, int cien){
        this.billetesMil = mil;
        this.billetesQuinientos = quinientos;
        this.billetesCien = cien;
    }

    // Procesa un retiro y devuelve el detalle de billetes entregados.
    // El monto debe ser múltiplo de 100 y el cajero debe tener billetes suficientes.
    public ResultadoRetiro retirar(int monto){

        if (monto % 100 != 0){
            throw new IllegalArgumentException("El monto debe ser múltiplo de 100");
        }

        int restante = monto;

        // Calcula cuántos billetes de cada denominación usar
        int usarMil = restante / 1000;
        restante %= 1000;

        int usarQuinientos = restante / 500;
        restante %= 500;

        int usarCien = restante / 100;

        // Verifica que haya billetes suficientes en el cajero
        if (usarMil > billetesMil || usarQuinientos > billetesQuinientos || usarCien > billetesCien){

            throw new IllegalArgumentException("Cantidad insuficiente de billetes");
        }

        // Descuenta los billetes utilizados
        billetesMil -= usarMil;
        billetesQuinientos -= usarQuinientos;
        billetesCien -= usarCien;

        return new ResultadoRetiro(usarMil, usarQuinientos, usarCien);
    }
}
