package app.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import app.abstractClasses.Transaccion;
import app.interfaces.Listable;

public class Compra extends Transaccion<DetallesCompra> implements Listable {

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
		detalles = new ArrayList<DetallesCompra>();
	}
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc != null ? rfc : "UNKNOWN";
	}
	@Override
	public String toString() {
		return super.toString() + "Compra [rfc=" + rfc + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Compra c)
			return c.getFolio() == getFolio();
		return false;
	}
	
	@Override
	public Object[] toRow() {
		return new Object[] {folio, total, fecha, detalles, rfc};
	}
	
	
	
}
