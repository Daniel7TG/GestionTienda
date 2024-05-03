package app.modelos.services;

import static app.dao.database.UtilityDB.getDataBaseParameters;

import java.util.List;

import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.Compra;
import app.modelos.repositories.ComprasRepository;
import app.records.DBRecord;

public class ComprasServiceImp implements Service<Compra> {

	private Database database;
	private ComprasRepository repository;
	
	
	public ComprasServiceImp(){
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
			repository = new ComprasRepository(database.getConnection());
		else 
			System.exit(0);		
	}

	
	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public boolean exists(Compra obj) {
		return repository.exists(String.valueOf(obj.getFolio()));
	}

	@Override
	public int save(Compra compra) {
		return repository.save(compra);
	}

	@Override
	public Compra get(String id) {
		return repository.get(id);
	}

	@Override
	public Compra get(Compra obj) {
		return repository.get(String.valueOf(obj.getFolio()));
	}

	@Override
	public boolean remove(Compra obj) {
		return repository.remove(String.valueOf(obj.getFolio()));
	}

	@Override
	public boolean remove(String id) {
		return repository.remove(String.valueOf(id));
	}

	@Override
	public boolean set(Compra obj) {
		return repository.set(obj);
	}

	@Override
	public List<Compra> getAll() {
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


	@Override
	@Deprecated
	public Compra getByData(String obj) {
		return null;
	}

}
