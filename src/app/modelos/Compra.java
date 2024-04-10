package app.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Compra {

	String folio;
	double total;
	String fecha;
	ArrayList<DetallesCompra> detalles;
	/**
	 * @param total
	 * @param fecha
	 * @param detalles
	 */
	public Compra(double total, String fecha, ArrayList<DetallesCompra> detalles) {
		this.total = total;
		this.fecha = fecha;
		this.detalles = new ArrayList<DetallesCompra>();
		this.detalles.addAll(detalles);	
		this.folio = UUID.randomUUID().toString().substring(0, 15);
	}
	
	
	public String getFolio() {
		return folio;
	}


	public void setFolio(String folio) {
		this.folio = folio;
	}


	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public ArrayList<DetallesCompra> getDetalles() {
		return detalles;
	}
	public void setDetalles(ArrayList<DetallesCompra> detalles) {
		this.detalles = detalles;
	}


	@Override
	public String toString() {
		return "CompraFinal [total=" + total + ", fecha=" + fecha + ", detalles=" + detalles + "]";
	}
	
	
	
}
