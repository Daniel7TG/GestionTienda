package app.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import app.abstractClasses.Transaccion;
import app.interfaces.Listable;

public class Venta extends Transaccion<DetallesVenta> implements Listable {

	private String userName;
	private String cliente;
	public Venta(LocalDate fecha, List<DetallesVenta> detalles) {
		super(fecha, detalles);
	}

	public Venta(LocalDate fecha, List<DetallesVenta> detalles, String userName, String cliente) {
		super(fecha, detalles);
		this.userName = userName;
		this.cliente = cliente;
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
		this.userName = userName != null ? userName : "UNKNOWN";
	}
	public void setCliente(String cliente){
		this.cliente = cliente != null ? cliente : "UNKNOWN";
	}

	public String getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return super.toString() + "Venta [folio=" + userName + "]";
	}


	@Override
	public boolean equals(Object o) {
		if(o instanceof Venta c)
			return c.getFolio() == getFolio();
		return false;
	}
	
	@Override
	public Object[] toRow() {
		return new Object[] {folio, total, fecha, detalles, userName, cliente};
	}
	
}
