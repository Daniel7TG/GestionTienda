package app.abstractClasses;

import app.modelos.Domicilio;

public abstract class Persona {
	
	protected String nombre;
	protected String apellido;
	protected String telefono;
	protected int idDomicilio;
	protected Domicilio domicilio;

	
	/**
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param idDomicilio
	 * @param domicilio
	 */
	public Persona(String nombre, String apellido, String telefono, int idDomicilio, Domicilio domicilio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.idDomicilio = idDomicilio;
		this.domicilio = domicilio;
	}
	
	
	public Persona() {
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
	public int getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	
	
}
