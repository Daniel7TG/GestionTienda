package app.abstractClasses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import app.modelos.DetallesCompra;

public class Transaccion<D extends Detalles> {

	private int folio;
	private double total;
	private LocalDate fecha;
	private List<D> detalles;
//	private String hora;
	
	/**
	 * @param total
	 * @param fecha
	 * @param detalles
	 */
	public Transaccion(double total, LocalDate fecha, List<D> detalles) {
		this.total = total;
		this.fecha = fecha;
		this.detalles = new ArrayList<D>();
		this.detalles.addAll(detalles);
//		this.folio = UUID.randomUUID().toString().substring(0, 15);
	}

	public Transaccion(LocalDate fecha, List<D> detalles) {
		this.total = detalles.stream().mapToDouble(D::getTotal).sum();
		this.fecha = fecha;
		this.detalles = new ArrayList<D>();
		this.detalles.addAll(detalles);
//		this.folio = UUID.randomUUID().toString().substring(0, 15);
	}
	
	public Transaccion(int folio) {
		this.folio = folio;
	}

	public Transaccion() {
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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
		if(obj instanceof Transaccion<?> tr)
			return folio == tr.folio;
		return false;
	}
	
}
