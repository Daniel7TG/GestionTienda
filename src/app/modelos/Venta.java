package app.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import app.abstractClasses.Transaccion;

public class Venta extends Transaccion<DetallesVenta>{

	String empleadoID;
	
	public Venta(LocalDate fecha, List<DetallesVenta> detalles) {
		super(fecha, detalles);
	}

	public Venta(int folio) {
		super(folio);
	}
	
}
