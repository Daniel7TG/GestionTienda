package app.modelos.repositories;

import java.util.List;

import app.interfaces.CRUDRepository;
import app.modelos.Proveedor;

public class ProveedoresRepository implements CRUDRepository<Proveedor> {

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int save(Proveedor obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Proveedor get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean set(Proveedor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Proveedor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[][] getMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
