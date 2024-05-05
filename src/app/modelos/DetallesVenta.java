package app.modelos;

import java.time.LocalDate;

import app.abstractClasses.Detalles;

public class DetallesVenta extends Detalles {
	
	private LocalDate fechaCaducidad;	
	
	public DetallesVenta(String codigo, double precio, int cantidad) {
		super(codigo, precio, cantidad);
	}	
	
	public DetallesVenta(String codigo, double precio, int cantidad, LocalDate fechaCaducidad) {
		super(codigo, precio, cantidad);
	}
	
	public DetallesVenta(String codigo) {
		super(codigo);
	}
	
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
		
		
	}
}
