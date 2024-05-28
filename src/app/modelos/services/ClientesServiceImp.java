package app.modelos.services;

import app.dao.database.Database;
import app.interfaces.Service;
import app.modelos.Cliente;
import app.modelos.repositories.ClientesRepository;
import app.modelos.repositories.ProductosRepository;
import app.records.DBRecord;

import java.util.List;

import static app.dao.database.UtilityDB.getDataBaseParameters;

public class ClientesServiceImp implements Service<Cliente> {

    private Database database;
    private ClientesRepository repository;


    public ClientesServiceImp(){
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
            repository = new ClientesRepository(database.getConnection());
        else
            System.exit(0);
    }

    @Override
    public boolean exists(String id) {
        return repository.exists(id);
    }

    @Override
    public boolean exists(Cliente obj) {
        return exists(obj.getTelefono());
    }

    @Override
    public int save(Cliente obj) {
        return repository.save(obj);
    }

    @Override
    public Cliente get(String id) {
        return repository.get(id);
    }

    @Override
    public Cliente get(Cliente obj) {
        return get(obj.getTelefono());
    }

    @Override
    public Cliente getByData(String obj) {
        return null;
    }

    @Override
    public boolean remove(Cliente obj) {
        return repository.remove(obj.getTelefono());
    }

    @Override
    public boolean remove(String id) {
        return repository.remove(id);
    }

    @Override
    public boolean set(Cliente obj) {
        return repository.set(obj);
    }

    @Override
    public List<Cliente> getAll() {
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
