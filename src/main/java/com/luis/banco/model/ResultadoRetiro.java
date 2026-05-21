package com.luis.banco.model;

// Representa el resultado de un retiro realizado en el cajero.
// Indica la cantidad de billetes entregados por denominación.
public class ResultadoRetiro {

    private int billetesMil;
    private int billetesQuinientos;
    private int billetesCien;
    private boolean exitoso;
    private String mensaje;

    // Crea un resultado con la cantidad de billetes de cada tipo.
    public ResultadoRetiro(int mil, int quinientos, int cien){
        this.exitoso = true;
        this.billetesMil = mil;
        this.billetesQuinientos = quinientos;
        this.billetesCien = cien;
    }

    // Constructor error
    public ResultadoRetiro(String mensaje){
        this.exitoso = false;
        this.mensaje = mensaje;
    }

    public boolean esExitoso(){
        return exitoso;
    }

    public String getMensaje(){
        return mensaje;
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
