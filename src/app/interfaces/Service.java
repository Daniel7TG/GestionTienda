package app.interfaces;

import java.util.List;

public interface Service<T> {

	boolean exists(String id);
	boolean exists(T obj);
	int save(T obj);
	T get(String id);
	T get(T obj);
	T getByData(String obj);
	boolean remove(T obj);
	boolean remove(String id);
	boolean set(T obj);
	List<T> getAll();
	Object[][] getMatrix();
	int getSize();
	boolean isEmpty();
	
}
