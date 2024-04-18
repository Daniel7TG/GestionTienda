package app.modelos;

public class Proveedor {
	
	private String rfc;
	private String nombre;
	private String apellido;
	private String razonSocial;
	private String telefono;
	private Domicilio domicilio;
	
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
	 * 
	 */
	public Proveedor() {
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
	public boolean equals(Object obj) {
		return rfc.equals(((Proveedor)obj).rfc);
	}
	
	@Override
	public String toString() {
		return "Proveedor [rfc=" + rfc + ", razonSocial=" + razonSocial + ", domicilio=" + domicilio + ", telefono="
				+ telefono + "]";
	}
}
