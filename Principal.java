import Clases.*; // Importa todas tus clases

public class Principal {

    public static void main(String[] args) {
        
        // 1. Crear el Banco
        Banco miBanco = new Banco();
        System.out.println("Sistema de Banco iniciado.");

        // 2. Registrar Empleados y Clientes
        miBanco.registrarEmpleado("Carlos Ruiz", "E-001", "Oficina Principal");
        miBanco.registrarCliente("Ana Gómez", "C-101", "Av. Siempre Viva 123");
        miBanco.registrarCliente("Luis Paz", "C-102", "Calle Falsa 456");

        // 3. Mostrar listas iniciales
        miBanco.mostrarEmpleados();
        miBanco.mostrarClientes();

        // 4. Registrar Cuentas para los clientes
        System.out.println("\n--- Registrando Cuentas ---");
        miBanco.registrarCuenta("C-101", "A-1001", true); // Cuenta para Ana
        miBanco.registrarCuenta("C-102", "S-2002", false); // Cuenta para Luis
        miBanco.registrarCuenta("C-999", "A-3003", true); // Cliente que no existe

        miBanco.mostrarClienteCuentas();

        // 5. Realizar Operaciones (Depósitos y Retiros)
        System.out.println("\n--- Realizando Operaciones ---");
        miBanco.generarDeposito("C-101", "A-1001", 500.0, "E-001");
        miBanco.generarDeposito("C-102", "S-2002", 300.0, "E-001");
        
        System.out.println("---");
        miBanco.generarRetiro("C-101", "A-1001", 100.0, "E-001");
        miBanco.generarRetiro("C-102", "S-2002", 400.0, "E-001"); // Fondos insuficientes
        miBanco.generarRetiro("C-101", "A-1001", 1000.0, "E-001"); // Fondos insuficientes
        miBanco.generarDeposito("C-101", "A-1001", 50.0, "E-001");

        // 6. Consultar Historiales
        miBanco.historialTransacciones("A-1001"); // Historial de Ana
        miBanco.historialTransacciones("S-2002"); // Historial de Luis
        
        miBanco.historialTransaccionesEmpleado("E-001"); // Historial de Carlos
        
        // 7. Mostrar estado final
        System.out.println("\n--- Estado Final del Banco ---");
        miBanco.mostrarClienteCuentas(); // Muestra saldos actualizados
        miBanco.mostrarTransacciones();
    }
}
