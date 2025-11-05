package Clases;

import java.util.ArrayList;

public class Banco {

    // Atributos (agregación con otras clases)
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<ClienteCuenta> listaClienteCuentas;
    private ArrayList<Transaccion> listaTransacciones;

    // Constructor 
    public Banco() {
        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaClienteCuentas = new ArrayList<>();
        listaTransacciones = new ArrayList<>();
    }

    //Registra un nuevo cliente en el banco
    public void registrarCliente(String nombre, String id, String direccion) {
        Cliente nuevo = new Cliente(nombre, id, direccion);
        listaClientes.add(nuevo);
        System.out.println("Cliente registrado: " + nombre);
    }

    /**
     * Registra un nuevo empleado
     */
    public void registrarEmpleado(String nombre, String id, String direccion) {
        Empleado nuevo = new Empleado(nombre, id, direccion);
        listaEmpleados.add(nuevo);
        System.out.println("Empleado registrado: " + nombre);
    }

    //Asocia una nueva cuenta a un cliente existente
    public void registrarCuenta(String idCliente, String idCuenta, boolean tipoCuenta) {
        Cliente clienteEncontrado = null;
        // Búsqueda manual del cliente
        clienteEncontrado = buscarCliente(idCliente);
        if (clienteEncontrado != null) {
            Cuenta nuevaCuenta = new Cuenta(idCuenta, tipoCuenta);
            ClienteCuenta nuevaRelacion = new ClienteCuenta(clienteEncontrado, nuevaCuenta);
            listaClienteCuentas.add(nuevaRelacion);
            System.out.println("Cuenta registrada para cliente: " + clienteEncontrado.getNombre());
        } else {
            System.out.println("Error: cliente no encontrado");
        }
    }
    // En el método generarDeposito - USO CORRECTO

    public void generarDeposito(String idCliente, String idCuenta, double monto, String idEmpleado, String fecha, String hora) {
        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);
        String idTransaccion = "d" + listaTransacciones.size();

        if (cliente == null || cuenta == null) {
            System.out.println("Error: Cliente o cuenta no encontrados");
            return;
        }

        if (monto <= 0) {
            System.out.println("Error: El monto debe ser positivo");
            return;
        }

        // Crear depósito dependiendo de si hay empleado o no
        Deposito deposito;
        if (empleado != null) {
            deposito = new Deposito(idCliente, idEmpleado, cuenta, monto, idTransaccion, fecha, hora);
            empleado.agregarAccion("Depósito de " + monto + " en cuenta " + idCuenta);
        } else {
            deposito = new Deposito(idCliente, cuenta, monto, idTransaccion, fecha, hora);
        }

        listaTransacciones.add(deposito);
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        System.out.println("Depósito realizado correctamente");
    }

    // Genera un retiro en una cuenta si hay saldo suficiente - VERSIÓN CORREGIDA
    public void generarRetiro(String idCliente, String idCuenta, double monto, String idEmpleado, String fecha, String hora) {
        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);
        String idTransaccion = "r" + listaTransacciones.size();

        if (cliente == null || cuenta == null) {
            System.out.println("Error: Cliente o cuenta no encontrados");
            return;
        }

        if (monto <= 0) {
            System.out.println("Error: El monto debe ser positivo");
            return;
        }

        if (cuenta.getSaldo() < monto) {
            System.out.println("Error: Fondos insuficientes para el retiro");
            return;
        }

        // Crear retiro dependiendo de si hay empleado o no - USANDO LOS CONSTRUCTORES CORRECTOS
        Retiro retiro;
        if (empleado != null) {
            retiro = new Retiro(idCliente, idEmpleado, cuenta, monto, idTransaccion, fecha, hora);
            empleado.agregarAccion("Retiro de " + monto + " en cuenta " + idCuenta);
        } else {
            retiro = new Retiro(idCliente, cuenta, monto, idTransaccion, fecha, hora);
        }

        listaTransacciones.add(retiro);
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        System.out.println("Retiro realizado correctamente");
    }

    // Muestra todas las transacciones realizadas por una cuenta
    public void historialTransacciones(String idCuenta) {
        System.out.println("\nHistorial de transacciones de la cuenta " + idCuenta + ":");
        for (int i = 0; i < listaTransacciones.size(); i++) {
            Transaccion t = listaTransacciones.get(i);
            if (t.getIdCuenta().equals(idCuenta)) {
                System.out.println(t);
            }
        }
    }

    // Muestra las acciones registradas por un empleado
    public void historialTransaccionesEmpleado(String idEmpleado) {
        Empleado emp = buscarEmpleado(idEmpleado);
        if (emp != null) {
            System.out.println("\nAcciones del empleado " + emp.getNombre() + ":");
            emp.mostrarAcciones();
        } else {
            System.out.println("Empleado no encontrado");
        }
    }

    // MÉTODOS DE BÚSQUEDA
    // Busca un cliente según su ID
    private Cliente buscarCliente(String id) {
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // Busca un empleado según su ID
    private Empleado buscarEmpleado(String id) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    // Busca la cuenta asociada a un cliente
    private Cuenta buscarCuentaDeCliente(String idCliente, String idCuenta) {
        for (int i = 0; i < listaClienteCuentas.size(); i++) {
            ClienteCuenta cc = listaClienteCuentas.get(i);
            for (int j = 0; j < cc.CantidadClientes(); j++) {
                if (cc.getCliente(j).getId().equals(idCliente)
                        && cc.getCuenta().getIdCuenta().equals(idCuenta)) {
                    return cc.getCuenta();
                }
            }
        }
        return null;
    }

    // MÉTODOS PARA MOSTRAR LISTAR
    // Muestra la lista de clientes registrados
    public void mostrarClientes() {
        System.out.println("\nLos clientes registrados son:");
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            System.out.println("- " + c.getNombre() + " (ID: " + c.getId() + ")");
        }
    }

    // Muestra la lista de empleados registrados 
    public void mostrarEmpleados() {
        System.out.println("\nLos Empleados registrados son:");
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            System.out.println("- " + e.getNombre() + " (ID: " + e.getId() + ")");
        }
    }

    // Muestra todas las relaciones cliente-cuenta 
    public void mostrarClienteCuentas() {
        System.out.println("\nLos Clientes y sus cuentas correspondientes son:");
        for (int i = 0; i < listaClienteCuentas.size(); i++) {
            ClienteCuenta cc = listaClienteCuentas.get(i);
            System.out.println(cc);
        }
    }

    // Muestra todas las transacciones registradas en el banco
    public void mostrarTransacciones() {
        System.out.println("\nLas Transacciones registradas son:");
        for (int i = 0; i < listaTransacciones.size(); i++) {
            System.out.println(listaTransacciones.get(i));
        }
    }
}
