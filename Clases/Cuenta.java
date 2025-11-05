package Clases;

import java.util.*;

class Cuenta {

    private String idCuenta;
    private Double saldo;
    private ArrayList<Transaccion> Transacciones;
    private boolean tipoCuenta;

    //CONSTRUCTOR
    public Cuenta(String numeroCuenta, Double saldo, boolean tipoCuenta) {
        this.idCuenta = numeroCuenta;
        this.saldo = saldo;
        Transacciones = new ArrayList<>();
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta(String numeroCuenta, boolean tipoCuenta) {
        this.idCuenta = numeroCuenta;
        Transacciones = new ArrayList<>();
        this.tipoCuenta = tipoCuenta;
    }

    //GET Y SET DE ID Y SALDO
    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    //METODO PARA REGISTRAR UNA TRANSACCION A LA CUENTA
    public void agregarTransferencia(Transaccion transaccion) {
        Transacciones.add(transaccion);
    }

    //MUESTRA EL SALDO
    public Double getSaldo() {
        return saldo;
    }

    //IMPRIME EL HISTORIAL DE TRANSACCIONES
    public void mostrarHistorial() {
        for (Transaccion objeto : Transacciones) {
            System.out.println(objeto.toString());
        }
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Cuenta{"
                + "idCuenta='" + idCuenta + '\''
                + ", saldo=" + saldo
                + ", cantidadTransacciones=" + Transacciones.size()
                + '}';
    }
}
