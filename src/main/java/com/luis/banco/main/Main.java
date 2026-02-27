package com.luis.banco.main;

import com.luis.banco.model.Cajero;
import com.luis.banco.model.Cuenta;
import com.luis.banco.service.BancoService;
import com.luis.banco.model.ResultadoRetiro;
import com.luis.banco.model.Transaccion;

import java.util.List;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Objeto Scanner, para leer datos ingresados por el usuario.
        Scanner entrada = new Scanner(System.in);

        // Cantidad inicial de billetes
        Cajero cajero = new Cajero(3, 4, 10);

        //Creo servicio principal del banco.
        //Actua como capa intermediaria entre el Main y el modelo
        BancoService bancoService = new BancoService(cajero);

        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = entrada.next();

        System.out.print("Ingrese saldo inicial: ");
        int saldoInicial = entrada.nextInt();

        //Se crea la cuenta dentro del sistema
        bancoService.crearCuenta(numeroCuenta, saldoInicial);

        int opcion;

        do {
            System.out.println("\nBANCO NACION");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Retirar");
            System.out.println("3 - Depositar");
            System.out.println("4 - Ver Historial");
            System.out.println("0 - Salir");
            System.out.print("Ingrese opción: ");
            opcion = entrada.nextInt();

            try {

                //Se evalúa la opción seleccionada por el ususario
                switch (opcion){

                    case 1:
                        //Comsulta de saldo, encargado por BancoService
                        System.out.println("Saldo actual: $" + bancoService.verSaldo(numeroCuenta));
                        break;

                    case 2:
                        System.out.print("Ingrese monto a retirar: ");
                        int monto = entrada.nextInt();

                        //Se ejecuta la operacion de retiro a través del servicio
                        ResultadoRetiro resultado = bancoService.retirar(numeroCuenta, monto);
                        System.out.println("Retiro exitoso.");
                        System.out.println("Se entregan: ");

                        // Se muestran solo los billetes que efectivamente se entragan
                        //Evita imprimir valores en cero.
                        if (resultado.getBilletesMil() > 0)
                            System.out.println(resultado.getBilletesMil() + " billetes de 1000");

                        if (resultado.getBilletesQuinientos() > 0)
                            System.out.println(resultado.getBilletesQuinientos() + " billetes de 500");

                        if (resultado.getBilletesCien() > 0)
                            System.out.println(resultado.getBilletesCien() + " billetes de 100");

                        break;

                    case 3:
                        System.out.println("Depositar.");
                        break;

                    case 4:
                        // Consulta historial transacciones
                        // Se obtiene la lista desde el BancoService. Mantiene encapsulamiento
                        List<Transaccion> historial = bancoService.obtenerHistorial(numeroCuenta);

                        if (historial.isEmpty()){
                            System.out.println("No hay transacciones registradas.");
                        } else {
                            System.out.println("=== Historial de Transacciones ===");

                            //Se recorre la lista
                            // Cada Transaccion utiliza su método toString()
                            // para mostrar la información formateada.
                            for (Transaccion t : historial){
                                System.out.println(t);
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Gracias por usar el sistema.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (Exception e){
                //Manejo global de excepciones.
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);

        // Se cierra el scanner para liberar recursos del sistema
        entrada.close();
    }
}