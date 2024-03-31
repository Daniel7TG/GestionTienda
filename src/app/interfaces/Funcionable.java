package app.interfaces;

import java.util.List;

import app.modelos.Producto;

public interface Funcionable {

	boolean exists(String codigoBarras);
	boolean exists(Producto codigoBarras);
	void add(Producto producto);
	Producto getProducto(int posicion);
	Producto getProducto(String codigoBarras);
	int getIndex(Producto producto);
	int getIndex(String codigoBarras);
	void remove(Producto producto);
	void remove(String codigoBarras);
	void update(Producto producto, int posicion);
	List<Producto> getList();
	String[][] getData();
	int getSize();
	boolean isEmpty();
	
	
}
