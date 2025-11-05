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

    /** Registra un nuevo empleado */
    public void registrarEmpleado(String nombre, String id, String direccion) {
        Empleado nuevo = new Empleado(nombre, id, direccion);
        listaEmpleados.add(nuevo);
        System.out.println("Empleado registrado: " + nombre);
    }

    //Asocia una nueva cuenta a un cliente existente
    public void registrarCuenta(String idCliente, String idCuenta, boolean tipoCuenta) {
        Cliente clienteEncontrado = null;
        // Búsqueda manual del cliente
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId().equals(idCliente)) {
                clienteEncontrado = listaClientes.get(i);
            }
        }
        if (clienteEncontrado != null) {
            Cuenta nuevaCuenta = new Cuenta(idCuenta, tipoCuenta);
            ClienteCuenta nuevaRelacion = new ClienteCuenta(clienteEncontrado, nuevaCuenta);
            listaClienteCuentas.add(nuevaRelacion);
            System.out.println("Cuenta registrada para cliente: " + clienteEncontrado.getNombre());
        } else {
            System.out.println("Error: cliente no encontrado");
        }
    }
    // Genera un depósito en una cuenta específica
    public void generarDeposito(String idCliente, String idCuenta, double monto, String idEmpleado) {
        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente != null && empleado != null && cuenta != null) {
            Deposito dep = new Deposito();
            dep.movimiento(monto, cuenta);

            Transaccion transaccion = new Transaccion(idCliente, idCuenta, monto, "Depósito");
            listaTransacciones.add(transaccion);

            empleado.registrarAccion("Depósito de " + monto + " en cuenta " + idCuenta);
            System.out.println("Depósito realizado correctamente");
        } else
            System.out.println("Error en los datos del depósito");
    }

    // Genera un retiro en una cuenta si hay saldo suficiente 
    public void generarRetiro(String idCliente, String idCuenta, double monto, String idEmpleado) {
        Cliente cliente = buscarCliente(idCliente);
        Empleado empleado = buscarEmpleado(idEmpleado);
        Cuenta cuenta = buscarCuentaDeCliente(idCliente, idCuenta);

        if (cliente != null && empleado != null && cuenta != null) {
            if (cuenta.getSaldo() >= monto) {
                Retiro ret = new Retiro();
                ret.movimiento(monto, cuenta);

                Transaccion transaccion = new Transaccion(idCliente, idCuenta, -monto, "Retiro");
                listaTransacciones.add(transaccion);

                empleado.registrarAccion("Retiro de " + monto + " en cuenta " + idCuenta);
                System.out.println("Retiro realizado correctamente");
            } else 
                System.out.println("Fondos insuficientes para el retiro");
        } else 
            System.out.println("Error en los datos del retiro");
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
        } else 
            System.out.println("Empleado no encontrado");
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
            if (cc.getCliente().getId().equals(idCliente) &&
                cc.getCuenta().getIdCuenta().equals(idCuenta)) {
                return cc.getCuenta();
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