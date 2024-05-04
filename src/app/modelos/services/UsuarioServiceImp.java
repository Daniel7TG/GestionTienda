package app.modelos.services;

import static app.dao.database.UtilityDB.getDataBaseParameters;

import java.util.List;

import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.Usuario;
import app.modelos.repositories.DomicilioRepository;
import app.modelos.repositories.UsuariosRepository;
import app.records.DBRecord;

public class UsuarioServiceImp implements Service<Usuario> {
	
	private Database database;
	private UsuariosRepository repository;
	private DomicilioRepository repositoryDomicilio;
	
	
	public UsuarioServiceImp(){
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
			repository = new UsuariosRepository(database.getConnection());
			repositoryDomicilio = new DomicilioRepository(database.getConnection());
		}
		else 
			System.exit(0);		
	}
	
	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public boolean exists(Usuario obj) {
		return repository.exists(obj.getUserName());
	}

	@Override
	public int save(Usuario obj) {
		int domicilio = repositoryDomicilio.save(obj.getDomicilio());
		obj.setIdDomicilio(domicilio);
		return repository.save(obj);
	}

	@Override
	public Usuario get(String id) {
		return repository.get(id);
	}

	@Override
	public Usuario get(Usuario obj) {
		return repository.get(obj.getUserName());
	}

	@Override
	@Deprecated
	public Usuario getByData(String obj) {
		return null;
	}

	@Override
	public boolean remove(Usuario obj) {
		return repository.remove(obj.getUserName());
	}

	@Override
	public boolean remove(String id) {
		return repository.remove(id);
	}

	@Override
	public boolean set(Usuario obj) {
		repositoryDomicilio.set(obj.getDomicilio());
		return repository.set(obj);
	}

	@Override
	public List<Usuario> getAll() {
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
