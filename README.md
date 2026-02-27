#  Sistema Bancario - Java (Consola)

Proyecto de práctica desarrollado en Java que simula el funcionamiento básico de un banco con cajero automático.

##  Funcionalidades

- Crear cuenta
- Consultar saldo
- Retirar dinero
- Registro de transacciones
- Simulación de cajero automático con control de billetes

##  Conceptos aplicados

- Programación orientada a objetos
- Encapsulamiento
- Separación de responsabilidades
- Uso de Enum
- Manejo de excepciones
- Colecciones (HashMap, List)

##  Arquitectura

- `BancoService` → Lógica principal del banco
- `Cuenta` → Entidad de dominio
- `Transaccion` → Registro de operaciones
- `Cajero` → Lógica de entrega de billetes
- `ResultadoRetiro` → DTO de respuesta

##  Próxima evolución

Migración a Spring Boot con:
- REST API
- Base de datos
- JPA