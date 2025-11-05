package Clases;
class Transaccion {
    protected String idCliente;
    protected String idEmpleado;
    protected Cuenta cuenta;
    protected double monto;
    protected String idTransaccion;
    protected String fecha;
    protected String hora;
    //Transaccion que actua el empleado
    public Transaccion(String idCliente, String idEmpleado, Cuenta cuenta, double monto, String idTransaccion, String fecha, String hora){
        this.idCliente=idCliente;
        this.idEmpleado=idEmpleado;
        this.cuenta=cuenta;
        this.monto=monto;
        this.idTransaccion=idTransaccion;
        this.fecha=fecha;
        this.hora=hora;
    }
    //Transaccion que no actua el empleado
    public Transaccion(String idCliente, String idTransaccion, Cuenta cuenta, double monto, String fecha, String hora){
        this.idCliente=idCliente;
        this.cuenta=cuenta;
        this.monto=monto;
        this.idTransaccion=idTransaccion;
        this.fecha=fecha;
        this.hora=hora;
    }
    //Movimiento que heredaran las clases Deposito y Retiro
    public void movimiento(double monto, Cuenta cuenta){
        if(monto<=0){
            System.out.println("Movimiento no realizado, introducir un monto correcto");
            return;
        }
        if(cuenta==null){
            System.out.println("Cuenta no valida, ingresar una cuenta valida");
            return;
        }
        this.monto = monto;
        this.cuenta=cuenta;
        System.out.println("Movimiento Realizado");
        System.out.println("Cuenta: " + cuenta);
        System.out.println("Monto: " + monto);
    }
    //ToString
    @Override
    public String toString() {
        return "Transaccion{" +
                "idCliente='" + idCliente + '\'' +
                ", idEmpleado='" + idEmpleado + '\'' +
                ", cuenta=" + (cuenta != null ? cuenta.toString() : "null") +
                ", monto=" + monto +
                ", idTransaccion='" + idTransaccion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}