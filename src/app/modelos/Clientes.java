package app.modelos;

import java.util.List;

import app.interfaces.Funcionable;

public class Clientes implements Funcionable<Venta>{

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Venta obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Venta obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Venta get(int posicion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(Venta obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Venta obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Venta obj, int posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Venta> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getData() {
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
