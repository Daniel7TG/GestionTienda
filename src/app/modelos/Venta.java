package app.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Venta {

	String folio;
	double total;
	String fecha;
	ArrayList<DetallesVenta> detalles;
	/**
	 * @param total
	 * @param fecha
	 * @param detalles
	 */
	public Venta(double total, String fecha, ArrayList<DetallesVenta> detalles) {
		this.total = total;
		this.fecha = fecha;
		this.detalles = new ArrayList<DetallesVenta>();
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

	public ArrayList<DetallesVenta> getDetalles() {
		System.out.println("obteniendo detalles: " + detalles.toString());
		return detalles;
	}

	public void setDetalles(ArrayList<DetallesVenta> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "VentaFinal [total=" + total + ", fecha=" + fecha + ", detalles=" + detalles + "]";
	}
		
	@Override
	public boolean equals(Object obj) {
		return fecha.equals(((Venta)obj).fecha) & total == ((Venta)obj).total;
	}
	
}
