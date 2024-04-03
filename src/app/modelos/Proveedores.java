package app.modelos;

import java.util.List;

import app.interfaces.Funcionable;

public class Proveedores implements Funcionable<Compra>{

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Compra obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Compra obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compra get(int posicion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(Compra obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Compra obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Compra obj, int posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Compra> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[][] getData() {
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
