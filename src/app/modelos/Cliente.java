package app.modelos;

import app.interfaces.Listable;

public class Cliente implements Listable {

    private String nombre;
    private String apellido;
    private String telefono;
    private String tarjeta;


    public Cliente(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        generarTarjeta();
    }

    public Cliente() {

    }

    private void generarTarjeta() {
        StringBuilder t = new StringBuilder();
        for(int i = 0; i < 16; i++){
             t.append((int)(Math.random() * 10));
        }
        this.tarjeta = t.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public Object[] toRow() {
        return new String[]{tarjeta, nombre, apellido, telefono};
    }
}
