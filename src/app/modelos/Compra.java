package app.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Compra extends Transaccion<DetallesCompra>{

	private String rfc;

	public Compra(String fecha, ArrayList<DetallesCompra> detalles) {
		super(fecha, detalles);
	}
	public Compra(String fecha, ArrayList<DetallesCompra> detalles, String rfc) {
		super(fecha, detalles);
		this.rfc = rfc;
	}

	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
}
