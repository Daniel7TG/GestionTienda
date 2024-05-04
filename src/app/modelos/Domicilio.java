package app.modelos;

import app.enums.Orientacion;

public class Domicilio {
	
	private int numero;
	private String calle;
	private Orientacion orientacion;
	private String colonia;
	private String ciudad;
	private String estado;
	private String codigoPostal;
	/**
	 * @param numero
	 * @param calle
	 * @param orientacion
	 * @param colonia
	 * @param ciudad
	 * @param estado
	 * @param codigoPostal
	 */
	public Domicilio(int numero, String calle, Orientacion orientacion, String colonia, String ciudad, String estado,
			String codigoPostal) {
		this.numero = numero;
		this.calle = calle;
		this.orientacion = orientacion;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
	}
	/**
	 * 
	 */
	public Domicilio() {
	}
	
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Orientacion getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	@Override
	public String toString() {
		return String.join(", ", calle, colonia, ciudad, estado);
	}
	
	
	
}
