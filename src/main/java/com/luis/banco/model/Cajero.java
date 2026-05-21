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

    // Procesa un retiro de dinero del cajero automático.
    // Valida que el monto sea múltiplo de 100 y que exista una combinación
    // posible de billetes disponibles para entregar el dinero solicitado.
    //
    // El algoritmo intenta diferentes combinaciones de billetes (1000, 500 y 100)
    // empezando por la mayor cantidad posible de billetes grandes y reduciendo,
    // hasta encontrar una combinación válida.
    //
    // Si encuentra una combinación válida:
    // - Descuenta los billetes del cajero
    // - Devuelve un ResultadoRetiro exitoso con el detalle de billetes entregados
    //
    // Si no encuentra ninguna combinación posible:
    // - Devuelve un ResultadoRetiro indicando el error
    public ResultadoRetiro retirar(int monto){

        if (monto % 100 != 0){
            return new ResultadoRetiro("El monto debe ser múltiplo de 100");
        }

        for (int mil = Math.min(monto / 1000, billetesMil); mil >= 0; mil--) {

            int restante = monto - (mil * 1000);

            for (int quinientos = Math.min(restante / 500, billetesQuinientos); quinientos >= 0; quinientos--) {

                int resto = restante - (quinientos * 500);

                if (resto % 100 != 0) continue;

                int cien = resto / 100;

                if (cien <= billetesCien) {

                    // descontar billetes
                    billetesMil -= mil;
                    billetesQuinientos -= quinientos;
                    billetesCien -= cien;

                    return new ResultadoRetiro(mil, quinientos, cien);
                }
            }
        }

        return new ResultadoRetiro("Cantidad insuficiente de billetes");
    }
}
