package Clases;

class Cliente extends Persona {

    //ATRIBUTOS
    private Cuenta cuenta;

    //Constructor sobrecargado
    public Cliente(String nombre, String id, String direccion, Cuenta cuenta) {
        super(nombre, id, direccion);
        this.cuenta = cuenta;
    }

    public Cliente(String nombre, String id, String direccion) {
        super(nombre, id, direccion);
    }

    //MÉTODOS (getters y setters)
    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    //Mostrar información
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Cuenta del cliente: " + cuenta);;
    }
}
