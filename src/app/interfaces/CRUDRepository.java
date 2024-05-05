package app.interfaces;

import java.util.List;

public interface CRUDRepository<T> {

	boolean exists(String id);
	int save(T obj);
	int saveAll(List<T> obj);
	T get(String id);
	boolean remove(String id);
	boolean set(T obj);
	List<T> getAll();
	Object[][] getMatrix();
	int getSize();
	boolean isEmpty();
	
}
