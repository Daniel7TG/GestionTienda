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
	
	@Override
	public String toString() {
		return String.join(", ", calle, colonia, ciudad, estado);
	}
	
	
	
}
