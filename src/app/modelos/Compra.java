package app.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Compra extends Transaccion<DetallesCompra>{

	private Proveedor proveedor;

	public Compra(String fecha, ArrayList<DetallesCompra> detalles) {
		super(fecha, detalles);
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
