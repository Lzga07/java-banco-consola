package com.luis.banco.main;

import java.util.InputMismatchException;
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
        Cajero cajero = new Cajero(500, 1000, 5000);

        //Creo servicio principal del banco.
        //Actua como capa intermediaria entre el Main y el modelo
        BancoService bancoService = new BancoService(cajero);

        //Creo cuenta para prueba
        bancoService.crearCuenta("4545", 25000);
        String cuentaActual = "4545";

        int opcion;

        do {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("Cuenta actual: " + cuentaActual);
            System.out.println();
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Retirar");
            System.out.println("3 - Depositar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Ver Historial");
            System.out.println("6 - Crear cuenta");
            System.out.println("7 - Cambiar cuenta");
            System.out.println("8 - Listar cuentas");
            System.out.println("0 - Salir");
            System.out.print("Ingrese opción: ");
            opcion = entrada.nextInt();

            try {

                //Se evalúa la opción seleccionada por el usuario
                switch (opcion) {

                    case 1:
                        //Consulta de saldo, encargado por BancoService
                        System.out.println("Saldo actual: $" + bancoService.verSaldo(cuentaActual));
                        break;

                    case 2:
                        System.out.print("Ingrese monto a retirar: ");
                        int monto = entrada.nextInt();

                        //Se ejecuta la operación de retiro a través del servicio
                        ResultadoRetiro resultado = bancoService.retirar(cuentaActual, monto);

                        System.out.println("Retiro exitoso.");
                        System.out.println("Se entregan: ");

                        // Se muestran solo los billetes que efectivamente se entregan
                        //Evita imprimir valores en cero.
                        if (resultado.getBilletesMil() > 0)
                            System.out.println(resultado.getBilletesMil() + " billetes de 1000");

                        if (resultado.getBilletesQuinientos() > 0)
                            System.out.println(resultado.getBilletesQuinientos() + " billetes de 500");

                        if (resultado.getBilletesCien() > 0)
                            System.out.println(resultado.getBilletesCien() + " billetes de 100");

                        break;

                    case 3:
                        System.out.print("Ingrese monto a depositar: ");
                        int deposito = entrada.nextInt();

                        bancoService.depositar(cuentaActual, deposito);
                        System.out.println("Depósito realizado con éxito.");
                        break;
                    case 4:
                        System.out.print("Ingrese cuenta destino: ");
                        String cuentaDestino = entrada.next();

                        System.out.print("Ingrese monto a transferir: ");
                        int montoTransferencia = entrada.nextInt();

                        bancoService.transferir(cuentaActual, cuentaDestino, montoTransferencia);

                        System.out.println("Transferencia realizada con éxito.");
                        break;
                    case 5:
                        // Consulta historial transacciones
                        // Se obtiene la lista desde el BancoService. Mantiene encapsulamiento
                        List<Transaccion> historial = bancoService.obtenerHistorial(cuentaActual);

                        if (historial.isEmpty()) {
                            System.out.println("No hay transacciones registradas.");
                        } else {
                            System.out.println("=== Historial de Transacciones ===");

                            //Se recorre la lista
                            // Cada Transaccion utiliza su método toString()
                            // para mostrar la información formateada.
                            for (Transaccion t : historial) {
                                System.out.println(t);
                            }
                        }
                        break;
                    case 6:
                        System.out.print("Ingrese número de cuenta: ");
                        String nuevaCuenta = entrada.next();

                        System.out.print("Ingrese saldo inicial: ");
                        int saldo = entrada.nextInt();

                        bancoService.crearCuenta(nuevaCuenta, saldo);
                        System.out.println("Cuenta creada con éxito.");
                        break;
                    case 7:
                        System.out.print("Ingrese número de cuenta: ");
                        String nueva = entrada.next();

                        if (!bancoService.existeCuenta(nueva)) {
                            System.out.println("ERROR: La cuenta no existe.");
                            break;
                        }

                        cuentaActual = nueva;
                        System.out.println("Cuenta cambiada con éxito.");
                        break;
                    case 8:
                        System.out.println("==== CUENTAS DISPONIBLES ====");
                        for (String c : bancoService.obtenerCuentas()) {
                            System.out.println(c);
                        }
                        break;
                    case 0:
                        System.out.println("Gracias por usar el sistema.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            } catch (Exception e) {
                //Manejo global de excepciones.
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);

        // Se cierra el scanner para liberar recursos del sistema
        entrada.close();
    }
}