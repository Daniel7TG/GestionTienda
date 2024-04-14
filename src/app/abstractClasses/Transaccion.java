package app.abstractClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import app.modelos.DetallesCompra;

public class Transaccion<D extends Detalles> {

	private String folio;
	private double total;
	private String fecha;
	private List<D> detalles;
//	private String hora;
	
	/**
	 * @param total
	 * @param fecha
	 * @param detalles
	 */
	public Transaccion(double total, String fecha, List<D> detalles) {
		this.total = total;
		this.fecha = fecha;
		this.detalles = new ArrayList<D>();
		this.detalles.addAll(detalles);
		this.folio = UUID.randomUUID().toString().substring(0, 15);
	}

	public Transaccion(String fecha, List<D> detalles) {
		this.total = detalles.stream().mapToDouble(D::getTotal).sum();
		this.fecha = fecha;
		this.detalles = new ArrayList<D>();
		this.detalles.addAll(detalles);
		this.folio = UUID.randomUUID().toString().substring(0, 15);
	}
	
	public Transaccion(String folio) {
		this.folio = folio;
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

	public List<D> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<D> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Transaccion [total=" + total + ", fecha=" + fecha + ", detalles=" + detalles + "]";
	}
		
	@Override
	public boolean equals(Object obj) {
		return folio.equals(((Transaccion)obj).folio);
	}
	
}
