package app.modelos;

public class Proveedor {
	
	private String rfc;
	private String razonSocial;
	private Domicilio domicilio;
	private String telefono;
	
	/**
	 * @param razonSocial
	 * @param rfc
	 * @param telefono
	 * @param domicilio
	 */
	public Proveedor(String razonSocial, String rfc, String telefono, Domicilio domicilio) {
		this.razonSocial = razonSocial;
		this.rfc = rfc;
		this.telefono = telefono;
		this.domicilio = new Domicilio();
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
	
}
