package app.modelos;

import java.time.LocalDate;

import app.abstractClasses.Detalles;
import app.interfaces.Listable;

public class DetallesVenta extends Detalles implements Listable {
	
//	private LocalDate fechaCaducidad;	
	
	public DetallesVenta() {
	}
	
	public DetallesVenta(String codigo, double precio, int cantidad) {
		super(codigo, precio, cantidad);
	}	
	
	public DetallesVenta(String codigo, double precio, int cantidad, LocalDate fechaCaducidad) {
		super(codigo, precio, cantidad);
	}
	
	public DetallesVenta(String codigo) {
		super(codigo);
	}

	@Override
	public Object[] toRow() {
		return new Object[] {codigo, cantidad, precio, total};
	}

}
