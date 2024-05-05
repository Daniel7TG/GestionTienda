package app.modelos.services;

import static app.dao.database.UtilityDB.getDataBaseParameters;

import java.util.List;

import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.Proveedor;
import app.modelos.repositories.DomicilioRepository;
import app.modelos.repositories.ProductosRepository;
import app.modelos.repositories.ProveedoresRepository;
import app.records.DBRecord;

public class ProveedoresServiceImp implements Service<Proveedor> {

	
	private Database database;
	private ProveedoresRepository repository;
	private DomicilioRepository repositoryDomicilio;
	
	public ProveedoresServiceImp(){
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
		if(database.doConnection()) {
			repositoryDomicilio = new DomicilioRepository(database.getConnection());
			repository = new ProveedoresRepository(database.getConnection()); 	
		}
		else 
			System.exit(0);		
	}
	
	
	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public boolean exists(Proveedor obj) {
		return repository.exists(obj.getRfc());
	}

	@Override
	public int save(Proveedor obj){
		int domicilio = repositoryDomicilio.save(obj.getDomicilio());
		obj.setIdDomicilio(domicilio);
		return repository.save(obj);
	}

	@Override
	public Proveedor get(String id) {
		return repository.get(id);
	}

	@Override
	public Proveedor get(Proveedor obj) {
		return get(obj.getRfc());
	}

	@Override
	public Proveedor getByData(String obj) {
		return null;
	}

	@Override
	public boolean remove(Proveedor obj) {
		return remove(obj.getRfc());
	}

	@Override
	public boolean remove(String id) {
		return repository.remove(id);
	}

	@Override
	public boolean set(Proveedor obj) {
		repositoryDomicilio.set(obj.getDomicilio());
		return repository.set(obj);
	}

	@Override
	public List<Proveedor> getAll() {
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
