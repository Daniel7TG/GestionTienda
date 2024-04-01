package app.modelos;

import java.util.ArrayList;
import java.util.Objects;

public class Venta {

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
		this.detalles = detalles;
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
		return detalles;
	}

	public void setDetalles(ArrayList<DetallesVenta> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "VentaFinal [total=" + total + ", fecha=" + fecha + ", detalles=" + detalles + "]";
	}
		
}
