package app.modelos.services;

import static app.dao.database.UtilityDB.getDataBaseParameters;

import java.util.List;

import app.dao.database.Database;
import app.interfaces.Funcionable;
import app.modelos.Producto;
import app.modelos.repositories.ProductosRepository;
import app.records.DBRecord;

public class ProductosServiceImp implements Funcionable<Producto> {
	
	
	private Database database;
	private ProductosRepository repository;
	
	
	public ProductosServiceImp(){
		initDatabase();
	}

	
	private void initDatabase() {
		DBRecord record = getDataBaseParameters();
		String name = record.dataBase();
		String driver = record.driver();
		String password = record.password();
		String protocolo = record.protocolo();
		String usuario = record.usuario();
		
		database = Database.newInstance(name, usuario, password, protocolo, driver);
		if(database.doConnection()) 
			repository = new ProductosRepository(database.getConnection());
		else 
			System.exit(0);		
	}
	
	
	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public boolean exists(Producto obj) {
		return repository.exists(obj);
	}

	@Override
	public void add(Producto obj) {
		repository.add(obj);		
	}

	@Override
	public Producto get(int posicion) {return null;}

	@Override
	public Producto get(String id) {
		return repository.get(id);
	}

	@Override
	public Producto get(Producto obj) {
		return repository.get(obj);
	}

	@Override
	public int getIndex(Producto obj) {return 0;}

	@Override
	public int getIndex(String id) {return 0;}

	@Override
	public void remove(Producto obj) {
		repository.remove(obj);
	}

	@Override
	public void remove(String id) {
		repository.remove(id);
	}

	@Override
	public void update(Producto obj, int posicion) {
		repository.update(obj, posicion);
	}

	@Override
	public List<Producto> getList() {
		return repository.getList();
	}

	@Override
	public Object[][] getData() {
		return repository.getData();
	}

	@Override
	public int getSize() {
		return repository.getSize();
	}

	@Override
	public boolean isEmpty() {
		return repository.isEmpty();
	}

}
