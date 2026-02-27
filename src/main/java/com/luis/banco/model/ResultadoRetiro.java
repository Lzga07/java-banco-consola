package com.luis.banco.model;

// Representa el resultado de un retiro realizado en el cajero.
// Indica la cantidad de billetes entregados por denominación.
public class ResultadoRetiro {

    private int billetesMil;
    private int billetesQuinientos;
    private int billetesCien;

    // Crea un resultado con la cantidad de billetes de cada tipo.
    public ResultadoRetiro(int mil, int quinientos, int cien){
        this.billetesMil = mil;
        this.billetesQuinientos = quinientos;
        this.billetesCien = cien;
    }

    // Devuelve la cantidad de billetes de $1000 entregados.
    public int getBilletesMil(){
        return billetesMil;
    }

    // Devuelve la cantidad de billetes de $500 entregados.
    public int getBilletesQuinientos(){
        return billetesQuinientos;
    }

    // Devuelve la cantidad de billetes de $100 entregados.
    public int getBilletesCien(){
        return billetesCien;
    }
}
