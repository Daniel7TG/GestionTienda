package app.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Venta extends Transaccion<DetallesVenta>{

	private String userName;
	
	String empleadoID;
	
	public Venta(LocalDate fecha, List<DetallesVenta> detalles) {
		super(fecha, detalles);
	}

	public Venta(LocalDate fecha, List<DetallesVenta> detalles, String userName) {
		super(fecha, detalles);
		this.folio = folio;
	}
	
	public Venta(int folio) {
		super(folio);
	}
	
	public Venta() {
		detalles = new ArrayList<DetallesVenta>();
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName= userName;
	}

	@Override
	public String toString() {
		return super.toString() + "Compra [rfc=" + userName + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Venta c)
			return c.getFolio() == getFolio();
		return false;
	}
	
	@Override
	public Object[] toRow() {
		return new Object[] {folio, total, fecha, detalles, userName};
	}



	
}
