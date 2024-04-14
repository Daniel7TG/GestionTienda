package app.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Compra extends Transaccion<DetallesCompra>{

	private String rfc;

	public Compra(String fecha, List<DetallesCompra> detalles) {
		super(fecha, detalles);
	}
	public Compra(String fecha, List<DetallesCompra> detalles, String rfc) {
		super(fecha, detalles);
		this.rfc = rfc;
	}
	public Compra(String folio) {
		super(folio);
	}

	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
}
