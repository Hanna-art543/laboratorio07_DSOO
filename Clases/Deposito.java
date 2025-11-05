package Clases;

class Deposito extends Transaccion {

    // Constructor con empleado
    public Deposito(String idCliente, String idEmpleado, Cuenta cuenta, double monto,
            String idTransaccion, String fecha, String hora) {
        super(idCliente, idEmpleado, cuenta, monto, idTransaccion, fecha, hora, "Depósito");
    }

    // Constructor sin empleado - CORREGIDO
    public Deposito(String idCliente, Cuenta cuenta, double monto,
            String idTransaccion, String fecha, String hora) {
        super(idCliente, cuenta, monto, idTransaccion, fecha, hora, "Depósito");
    }

    // Movimiento de depósito
    @Override
    public void movimiento(double monto, Cuenta cuenta) {
        super.movimiento(monto, cuenta);
        if (cuenta != null && monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            System.out.println("Depósito realizado con éxito en la cuenta.");
        }
    }

    @Override
    public String toString() {
        return "Deposito -> " + super.toString();
    }
}
