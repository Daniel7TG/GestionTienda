package app.modelos.services;

import static app.dao.database.UtilityDB.getDataBaseParameters;

import java.util.List;

import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.Producto;
import app.modelos.repositories.ProductosRepository;
import app.records.DBRecord;

public class ProductosServiceImp implements Service<Producto> {
	
	
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
		return exists(obj.getCodigoBarras());
	}

	@Override
	public int save(Producto obj) {
		return repository.save(obj);		
	}


	@Override
	public Producto get(String id) {
		return repository.get(id);
	}

	@Override
	public Producto get(Producto obj) {
		return get(obj.getCodigoBarras());
	}
	
	public Producto getByData(String data) {
		return repository.getByData(data);
	}


	@Override
	public boolean remove(Producto obj) {
		return remove(obj.getCodigoBarras());
	}

	@Override
	public boolean remove(String id) {
		return repository.remove(id);
	}

	@Override
	public boolean set(Producto obj) {
		return repository.set(obj);
	}

	@Override
	public List<Producto> getAll() {
		return repository.getAll();
	}

	@Override
	public Object[][] getMatrix() {
		return repository.getMatrix();
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
