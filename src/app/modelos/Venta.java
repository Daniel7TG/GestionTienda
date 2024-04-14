package app.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Venta extends Transaccion<DetallesVenta>{

	public Venta(String fecha, List<DetallesVenta> detalles) {
		super(fecha, detalles);
	}

	public Venta(String folio) {
		super(folio);
	}
	
}
