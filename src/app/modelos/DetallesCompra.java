package app.modelos;

import java.time.LocalDate;

import app.abstractClasses.Detalles;

public class DetallesCompra extends Detalles {

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

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
}
