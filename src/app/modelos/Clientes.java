package app.modelos;

import java.util.ArrayList;
import java.util.List;

import app.interfaces.Funcionable;
import app.util.Util;

public class Clientes implements Funcionable<Venta>{

	private List<Venta> contenedor;

	public Clientes() {
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
	/**
	 * @deprecated
	 **/
	public Venta get(String id) {
		return null;
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
		System.out.println("tamano:" + contenedor.size());
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
