package app.modelos.containers;

import java.util.ArrayList;
import java.util.List;

import app.interfaces.Funcionable;
import app.modelos.Producto;
import app.util.Util;

public class Catalogo implements Funcionable<Producto> {
	
	private List<Producto> contenedor;

	/**
	 * @param contenedor
	 */
	public Catalogo() {
		this.contenedor = new ArrayList<>();
	}


	@Override
	public boolean exists(String codigoBarras) {
		Producto p = new Producto();
		p.setCodigoBarras(codigoBarras);
		return contenedor.contains(p);
	}
		
	@Override
	public boolean exists(Producto p) {
		return contenedor.contains(p);
	}


	@Override
	public void add(Producto producto) {
		contenedor.add(producto);			
		
	}


	@Override
	public Producto get(int posicion) {
		Producto p = contenedor.get(posicion);
		return p;
	}


	@Override
	public Producto get(String codigoBarras) {
		int index = contenedor.indexOf(new Producto(codigoBarras));
		return index == -1 ? null : contenedor.get(index); 
	}


	@Override
	public int getIndex(Producto producto) {
		int index = contenedor.indexOf(producto);
		return index;
	}


	@Override
	public int getIndex(String codigoBarras) {
		int index = contenedor.indexOf(new Producto(codigoBarras));
		return index;
	}


	@Override
	public void remove(Producto producto) {
		contenedor.remove(producto);
	}


	@Override
	public void remove(String codigoBarras) {
		contenedor.remove(new Producto(codigoBarras));
	}


	@Override
	public void update(Producto producto, int posicion) {
		contenedor.set(posicion, producto);
	}


	@Override
	public boolean isEmpty() {
		return contenedor.isEmpty();
	}
	
	
	@Override
	public List<Producto> getList() {
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
	public Producto get(Producto obj) {
		int index = contenedor.indexOf(obj);
		return index == -1 ? null : contenedor.get(index); 
	}
	
	
	
}
