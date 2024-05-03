package app.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Compra extends Transaccion<DetallesCompra>{

	private String rfc;

	public Compra(LocalDate fecha, List<DetallesCompra> detalles) {
		super(fecha, detalles);
	}
	public Compra(LocalDate fecha, List<DetallesCompra> detalles, String rfc) {
		super(fecha, detalles);
		this.rfc = rfc;
	}
	public Compra(int folio) {
		super(folio);
	}
	
	public Compra() {
		super();
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	@Override
	public String toString() {
		return super.toString() + "Compra [rfc=" + rfc + "]";
	}
	

	
	
	
}
