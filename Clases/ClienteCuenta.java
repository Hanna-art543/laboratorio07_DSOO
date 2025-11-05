package Clases;

import java.util.*;

class ClienteCuenta {

    private ArrayList<Cliente> clientes;
    private Cuenta cuenta;

    //CONSTRUCTOR
    public ClienteCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        clientes = new ArrayList<>();
    }

    public ClienteCuenta(Cliente cliente, Cuenta cuenta) {
        clientes.add(cliente);
        this.cuenta = cuenta;
    }

    public ClienteCuenta(ArrayList<Cliente> clientes, Cuenta cuenta) {
        this.clientes = clientes;
        this.cuenta = cuenta;
    }

    public void asociarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public Cliente getCliente(int Index) {
        return clientes.get(Index);

    }

    public int CantidadClientes() {
        return clientes.size();
    }
}
