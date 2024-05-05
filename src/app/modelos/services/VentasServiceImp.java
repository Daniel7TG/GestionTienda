package app.modelos.services;

import java.util.List;

import javax.xml.crypto.Data;
import static app.dao.database.UtilityDB.getDataBaseParameters;
import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.modelos.Producto;
import app.modelos.Venta;
import app.modelos.repositories.ComprasRepository;
import app.modelos.repositories.DetallesCompraRepository;
import app.modelos.repositories.DetallesVentaRepository;
import app.modelos.repositories.ProductosRepository;
import app.modelos.repositories.VentasRepository;
import app.records.DBRecord;

public class VentasServiceImp implements Service<Venta> {

	private Database database;
	private VentasRepository repository;
	private ProductosRepository repositoryProductos;
	private DetallesVentaRepository repositoryDetalles;


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
		if(database.doConnection()) {
			repositoryProductos = new ProductosRepository(database.getConnection());
			repositoryDetalles = new DetallesVentaRepository(database.getConnection());
			repository = new VentasRepository(database.getConnection());
		}
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
		int folio = repository.save(venta);
		for(DetallesVenta det : venta.getDetalles()) {
			det.setFolio(folio);
			Producto p = repositoryProductos.get(det.getCodigo());
			p.subtractStock(det.getCantidad());
			repositoryProductos.set(p);
		}
		repositoryDetalles.saveAll(venta.getDetalles());
		return folio;
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
	@Deprecated
	public Venta getByData(String obj) {
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
