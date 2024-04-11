package app.modelos;

import app.abstractClasses.Detalles;

public class DetallesCompra extends Detalles {

	public DetallesCompra(String codigo, double precio, int cantidad) {
		super(codigo, precio, cantidad);
	}

	public DetallesCompra(String codigo) {
		super(codigo);
	}
	
}
