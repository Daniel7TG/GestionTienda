package app.modelos;

import java.util.ArrayList;
import java.util.List;

import app.interfaces.Funcionable;
import app.util.Util;

public class Proveedores implements Funcionable<Compra>{

	private List<Compra> contenedor;

	public Proveedores() {
		contenedor = new ArrayList<Compra>();
	}

	@Override
	public boolean exists(String id) {
		return false;
	}

	@Override
	public boolean exists(Compra obj) {
		return contenedor.contains(obj);
	}

	@Override
	public void add(Compra obj) {
		contenedor.add(obj);
	}

	@Override
	public Compra get(int posicion) {
		return contenedor.get(posicion);
	}

	@Override
	public Compra get(String id) {
		return null;
	}

	@Override
	public int getIndex(Compra obj) {
	return contenedor.indexOf(obj);
	}

	@Override
	public int getIndex(String id) {
		return 0;
	}

	@Override
	public void remove(Compra obj) {
		contenedor.remove(obj);
	}

	@Override
	public void remove(String id) {
	}

	@Override
	public void update(Compra obj, int posicion) {
		contenedor.set(posicion, obj);

	}

	@Override
	public List<Compra> getList() {
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
