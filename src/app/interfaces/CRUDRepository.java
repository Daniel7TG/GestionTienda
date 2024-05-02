package app.interfaces;

import java.util.List;

public interface CRUDRepository<T> {

	boolean exists(String id);
	boolean exists(T obj);
	int save(T obj);
	T get(String id);
	T get(T obj);
	boolean remove(T obj);
	boolean remove(String id);
	boolean set(T obj);
	List<T> getAll();
	Object[][] getMatrix();
	int getSize();
	boolean isEmpty();
	
}
