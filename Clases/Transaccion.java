package Clases;

class Transaccion {

    protected String idCliente;
    protected String idEmpleado;
    protected Cuenta cuenta;
    protected double monto;
    protected String idTransaccion;
    protected String fecha;
    protected String hora;
    protected String tipoTransaccion;

    // Constructor con empleado
    public Transaccion(String idCliente, String idEmpleado, Cuenta cuenta, double monto,
            String idTransaccion, String fecha, String hora, String tipoTransaccion) {
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.cuenta = cuenta;
        this.monto = monto;
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTransaccion = tipoTransaccion;
    }

    // Constructor sin empleado
    public Transaccion(String idCliente, Cuenta cuenta, double monto,
            String idTransaccion, String fecha, String hora, String tipoTransaccion) {
        this.idCliente = idCliente;
        this.cuenta = cuenta;
        this.monto = monto;
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTransaccion = tipoTransaccion;
        this.idEmpleado = null;
    }

    // Método movimiento (si lo necesitas)
    public void movimiento(double monto, Cuenta cuenta) {
        if (monto <= 0) {
            System.out.println("Movimiento no realizado, introducir un monto correcto");
            return;
        }
        if (cuenta == null) {
            System.out.println("Cuenta no válida, ingresar una cuenta válida");
            return;
        }
        this.monto = monto;
        this.cuenta = cuenta;
        System.out.println("Movimiento Realizado");
        System.out.println("Cuenta: " + cuenta.getIdCuenta());
        System.out.println("Monto: " + monto);
    }

    public String getIdCuenta() {
        return cuenta != null ? cuenta.getIdCuenta() : null;
    }

    @Override
    public String toString() {
        return "Transaccion{"
                + "idCliente='" + idCliente + '\''
                + ", idEmpleado='" + (idEmpleado != null ? idEmpleado : "N/A") + '\''
                + ", cuenta=" + (cuenta != null ? cuenta.getIdCuenta() : "null")
                + ", monto=" + monto
                + ", idTransaccion='" + idTransaccion + '\''
                + ", fecha='" + fecha + '\''
                + ", hora='" + hora + '\''
                + ", tipoTransaccion='" + tipoTransaccion + '\''
                + '}';
    }
}
