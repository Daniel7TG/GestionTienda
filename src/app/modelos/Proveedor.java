package app.modelos;

import app.abstractClasses.Persona;
import app.interfaces.Listable;

public class Proveedor extends Persona implements Listable {
	
	private String rfc;
	private String razonSocial;
	
	/**
	 * @param razonSocial
	 * @param nombre
	 * @param apellido
	 * @param rfc
	 * @param telefono
	 * @param domicilio
	 */
	public Proveedor(String razonSocial, String nombre, String apellido, String rfc, String telefono, Domicilio domicilio) {
		this.razonSocial = razonSocial;
		this.rfc = rfc;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.domicilio = domicilio;
	}
	/**
	 * @param rfc
	 * @param nombre
	 * @param apellido
	 * @param razonSocial
	 * @param telefono
	 * @param idDomicilio
	 * @param domicilio
	 */
	public Proveedor(String rfc, String nombre, String apellido, String razonSocial, String telefono, int idDomicilio,
			Domicilio domicilio) {
		super(nombre, apellido, telefono, idDomicilio, domicilio);
		this.rfc = rfc;
		this.nombre = nombre;
		this.apellido = apellido;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.idDomicilio = idDomicilio;
		this.domicilio = domicilio;
	}


	/**
	 * 
	 */
	public Proveedor() {
		super();
	}

	
	
	public int getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
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
	
	@Override
	public String[] toRow() {
		return new String[] {rfc, nombre + " " + apellido, razonSocial, domicilio.toString(), telefono};
	}
	
	@Override
	public boolean equals(Object obj) {
		return rfc.equals(((Proveedor)obj).rfc);
	}
	
	@Override
	public String toString() {
		return "Proveedor [rfc=" + rfc + ", razonSocial=" + razonSocial + ", domicilio=" + domicilio + ", telefono="
				+ telefono + "]";
	}
}
