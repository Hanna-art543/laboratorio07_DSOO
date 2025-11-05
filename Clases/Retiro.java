package Clases;

class Retiro extends Transaccion {

    // Constructor con empleado
    public Retiro(String idCliente, String idEmpleado, Cuenta cuenta, double monto,
            String idTransaccion, String fecha, String hora) {
        super(idCliente, idEmpleado, cuenta, monto, idTransaccion, fecha, hora, "Retiro");
    }

    // Constructor sin empleado
    public Retiro(String idCliente, Cuenta cuenta, double monto,
            String idTransaccion, String fecha, String hora) {
        super(idCliente, cuenta, monto, idTransaccion, fecha, hora, "Retiro");
    }

    // Movimiento de retiro (sobrescritura)
    @Override
    public void movimiento(double monto, Cuenta cuenta) {
        if (cuenta != null && cuenta.getSaldo() >= monto) {
            super.movimiento(monto, cuenta);
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            System.out.println("Retiro realizado con éxito en la cuenta.");
        } else {
            System.out.println("Error: Fondos insuficientes para el retiro");
        }
    }

    @Override
    public String toString() {
        return "Retiro -> " + super.toString();
    }
}
