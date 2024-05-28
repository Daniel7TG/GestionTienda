package app.modelos.repositories;

import app.interfaces.CRUDRepository;
import app.modelos.Cliente;
import app.modelos.Producto;
import app.util.MoveResult;
import app.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesRepository implements CRUDRepository<Cliente> {

    private Connection connection;
    private Statement statement;
    private PreparedStatement pStatement;
    private ResultSet resultSet;


    public ClientesRepository(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean exists(String id) {
        String sql = "SELECT * FROM cliente WHERE telefono = ?";
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, id);
            return pStatement.executeQuery().next();
        } catch (SQLException e) {}
        return false;
    }

    @Override
    public int save(Cliente cliente) {
        String sql = "INSERT INTO cliente(tarjeta, nombre, apellido, telefono) "
                + "VALUES(?, ?, ?, ?)";
        int result = 0;
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cliente.getTarjeta());
            pStatement.setString(2, cliente.getNombre());
            pStatement.setString(3, cliente.getApellido());
            pStatement.setString(4, cliente.getTelefono());
            result = pStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int saveAll(List<Cliente> obj) {
        return 0;
    }

    @Override
    public Cliente get(String id) {
        String sql = "SELECT * FROM cliente WHERE telefono = ?";
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, id);
            resultSet = pStatement.executeQuery();
            if(resultSet.next())
                return MoveResult.toClient(resultSet);
        } catch (SQLException e) {}
        return null;
    }

    @Override
    public boolean remove(String id) {
        String sql = "DELETE FROM cliente WHERE telefono = ?";
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, id);
            System.out.println(pStatement.toString());
            return pStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean set(Cliente obj) {
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, tarjeta = ? "
                + "WHERE telefono = ?";
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, obj.getNombre());
            pStatement.setString(2, obj.getApellido());
            pStatement.setString(4, obj.getTelefono());
            pStatement.setString(3, obj.getTarjeta());
            return pStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cliente> getAll() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                clientes.add(MoveResult.toClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Object[][] getMatrix() {
        return Util.anyToString(getAll());
    }

    @Override
    public int getSize() {
        String sql = "SELECT COUNT(tarjeta) AS size FROM cliente";
        try {
            resultSet = statement.executeQuery(sql);
            return resultSet.getInt("size");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
