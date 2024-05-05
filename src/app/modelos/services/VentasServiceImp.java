package app.modelos.services;

import java.util.List;

import javax.xml.crypto.Data;
import static app.dao.database.UtilityDB.getDataBaseParameters;
import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.Venta;
import app.modelos.repositories.VentasRepository;
import app.records.DBRecord;

public class VentasServiceImp implements Service<Venta> {

	private Database database;
	private VentasRepository repository;

	public VentasServiceImp() {
		initDataBase();

	}

	private void initDataBase() {
		DBRecord record = getDataBaseParameters();
		String name = record.dataBase();
		String driver = record.driver();
		String password = record.password();
		String protocolo = record.protocolo();
		String usuario = record.usuario();

		database = Database.newInstance(name, usuario, password, protocolo, driver);
		if(database.doConnection())
			repository = new VentasRepository(database.getConnection());
		else
			System.exit(0);
	}


	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public boolean exists(Venta obj) {
		return repository.exists(String.valueOf(obj.getFolio()));
	}

	@Override
	public int save(Venta venta) {
		return repository.save(venta);
	}

	@Override
	public Venta get(String id) {
		return repository.get(id);
	}

	@Override
	public Venta get(Venta obj) {
		return repository.get(String.valueOf(obj.getFolio()));
	}

	@Override
	public Venta getByData(String obj) {
		// TODO Auto-generated method stub
		return null; 
	}

	@Override
	public boolean remove(Venta obj) {
		return repository.remove(String.valueOf(obj.getFolio()));
	}

	@Override
	public boolean remove(String id) {
		return repository.remove(String.valueOf(id));
	}

	@Override
	public boolean set(Venta obj) {
		return repository.set(obj);
	}

	@Override
	public List<Venta> getAll() {
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
