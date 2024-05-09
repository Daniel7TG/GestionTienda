package app.modelos;

import java.time.LocalDate;

import app.abstractClasses.Detalles;
import app.interfaces.Listable;

public class DetallesCompra extends Detalles implements Listable {

	
	private LocalDate fechaCaducidad;
	
	public DetallesCompra(String codigo, double precio, int cantidad) {
		super(codigo, precio, cantidad);
	}
	public DetallesCompra(String codigo, double precio, int cantidad, LocalDate fechaCaducidad) {
		super(codigo, precio, cantidad);
	}

	public DetallesCompra(String codigo) {
		super(codigo);
	}

	public DetallesCompra() {
	}
	
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	@Override
	public Object[] toRow() {
		return new Object[] {codigo, cantidad, precio, total};
	}
}
