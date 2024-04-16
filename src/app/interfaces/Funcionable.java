package app.interfaces;

import java.util.ArrayList;
import java.util.List;

import app.modelos.Producto;

public interface Funcionable<T> {

	boolean exists(String id);
	boolean exists(T obj);
	void add(T obj);
	T get(int posicion);
	T get(String id);
	T get(T obj);
	int getIndex(T obj);
	int getIndex(String id);
	void remove(T obj);
	void remove(String id);
	void update(T obj, int posicion);
	List<T> getList();
	Object[][] getData();
	int getSize();
	boolean isEmpty();
	
	
	
}
