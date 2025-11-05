package Clases;
class Retiro extends Transaccion{
    //Constructor con empleado
    public Retiro(String idCliente, String idEmpleado, Cuenta cuenta, double monto, String idTransaccion, String fecha, String hora) {
        super(idCliente, idEmpleado, cuenta, monto, idTransaccion, fecha, hora);
    }
    //Constructor sin empleado
    public Retiro(String idCliente, String idTransaccion, Cuenta cuenta, double monto, String fecha, String hora) {
        super(idCliente, idTransaccion, cuenta,monto, fecha, hora);
    }
    //Movimiento de retiro
    public void movimiento(double monto, Cuenta cuenta) {
        super.movimiento(monto, cuenta);
        System.out.println("Retiro realizado con éxito en la cuenta.");
    }
    @Override
    public String toString() {
        return "Deposito -> " + super.toString();
    }
}