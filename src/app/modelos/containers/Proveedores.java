package app.modelos.containers;

import java.util.ArrayList;
import java.util.List;

import app.interfaces.Funcionable;
import app.modelos.Producto;
import app.modelos.Proveedor;
import app.modelos.Venta;
import app.util.Util;

public class Proveedores implements Funcionable<Proveedor>{
	
	private List<Proveedor> contenedor;

	/**
	 * @param contenedor
	 */
	public Proveedores() {
		this.contenedor = new ArrayList<>();
	}

	@Override
	public boolean exists(String id) {
		Proveedor p = new Proveedor();
		p.setRfc(id);
		return contenedor.contains(p);	}

	@Override
	public boolean exists(Proveedor obj) {
		return contenedor.contains(obj);
	}

	@Override
	public void add(Proveedor obj) {
		contenedor.add(obj);			
	}

	@Override
	public Proveedor get(int posicion) {
		return contenedor.get(posicion);
	}

	@Override
	public Proveedor get(String id) {
		int index = getIndex(id);
		return index != -1 ? contenedor.get(index) : null;
	}
	@Override
	public Proveedor get(Proveedor obj) {
		int index = contenedor.indexOf(obj);
		return index == -1 ? null : contenedor.get(index); 
	}

	@Override
	public int getIndex(Proveedor obj) {
		return contenedor.indexOf(obj);
	}

	@Override
	public int getIndex(String id) {
		Proveedor p = new Proveedor();
		p.setRfc(id);
		return getIndex(p);
	}

	@Override
	public void remove(Proveedor obj) {
		contenedor.remove(obj);
	}

	@Override
	public void remove(String id) {
		Proveedor p = new Proveedor();
		p.setRfc(id);
		remove(p);
	}

	@Override
	public void update(Proveedor obj, int posicion) {
		contenedor.set(posicion, obj);
	}

	@Override
	public List<Proveedor> getList() {
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
