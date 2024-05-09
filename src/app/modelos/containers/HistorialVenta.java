package app.modelos.containers;

import java.util.ArrayList;
import java.util.List;

import app.interfaces.Funcionable;
import app.modelos.Compra;
import app.modelos.Venta;
import app.util.Util;

public class HistorialVenta implements Funcionable<Venta>{

	private List<Venta> contenedor;

	public HistorialVenta() {
		contenedor = new ArrayList<Venta>();
	}
	
	@Override
	/**
	 * @deprecated
	 **/
	public boolean exists(String id) {
		return false;
	}

	@Override
	public boolean exists(Venta obj) {
		return contenedor.contains(obj);
	}

	@Override
	public void add(Venta obj) {
		contenedor.add(obj);
	}

	@Override
	public Venta get(int posicion) {
		return contenedor.get(posicion);
	}

	@Override
	public Venta get(String folio) {
		Venta venta = new Venta(folio);
		int index = contenedor.indexOf(venta);
		return index == -1 ? null : contenedor.get(index); 
	}	
	@Override
	public Venta get(Venta obj) {
		int index = contenedor.indexOf(obj);
		return index == -1 ? null : contenedor.get(index); 
	}
	@Override
	public int getIndex(Venta obj) {
		return contenedor.indexOf(obj);
	}

	@Override
	/**
	 * @deprecated
	 **/
	public int getIndex(String id) {
		return 0;
	}

	@Override
	public void remove(Venta obj) {
		contenedor.remove(obj);
	}

	@Override
	/**
	 * @deprecated
	 **/
	public void remove(String id) {		
	}

	@Override
	public void update(Venta obj, int posicion) {
		contenedor.set(posicion, obj);
	}

	@Override
	public List<Venta> getList() {
		return contenedor;
	}

	@Override
	public Object[][] getData() {
		return Util.anyToString(contenedor);
	}

	@Override
	public int getSize() {
		return contenedor.size();
	}

	@Override
	public boolean isEmpty() {
		return contenedor.isEmpty();
	}

}
