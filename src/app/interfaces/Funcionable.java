package app.interfaces;

import java.util.List;

import app.modelos.Producto;

public interface Funcionable<T> {

	boolean exists(String id);
	boolean exists(T obj);
	void add(T obj);
	T get(int posicion);
	T get(String id);
	int getIndex(T obj);
	int getIndex(String id);
	void remove(T obj);
	void remove(String id);
	void update(T obj, int posicion);
	List<T> getList();
	String[][] getData();
	int getSize();
	boolean isEmpty();
	
	
}
