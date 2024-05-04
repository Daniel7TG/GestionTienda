package app.modelos.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.interfaces.CRUDRepository;
import app.modelos.Proveedor;
import app.util.MoveResult;
import app.util.Util;

public class ProveedoresRepository implements CRUDRepository<Proveedor> {

	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public ProveedoresRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public boolean exists(String id) {
		return false;
	}

	@Override
	public int save(Proveedor proveedor) {
		String sql = "INSERT INTO proveedor(rfc, nombre, apellido, razon_social, telefono, domicilio) "
				+ "VALUES(?, ?, ?, ?, ?, ?)"; 
		int result = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, proveedor.getRfc());
			pStatement.setString(2, proveedor.getNombre());
			pStatement.setString(3, proveedor.getApellido());
			pStatement.setString(4, proveedor.getRazonSocial());
			pStatement.setString(5, proveedor.getTelefono());
			pStatement.setInt(6, proveedor.getIdDomicilio());
			result = pStatement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Proveedor get(String id) {
		String sql = "SELECT * FROM proveedor JOIN domicilio ON proveedores.domicilio = domicilio.id WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return MoveResult.toSupplier(resultSet);
		} catch (SQLException e) {}
		return null;
	}

	@Override
	public boolean remove(String id) {
		String sql = "DELETE FROM proveedor WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;	
	}

	@Override
	public boolean set(Proveedor obj) {
		String sql = "UPDATE proveedor SET nombre = ?, apellido = ?, "
				+ "razon_social = ?, telefono = ?, domicilio = ? "
				+ "JOIN domicilio ON proveedores.domicilio = domicilio.id "
				+ "WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, obj.getNombre());
			pStatement.setString(2, obj.getApellido());
			pStatement.setString(3, obj.getRazonSocial());
			pStatement.setString(4, obj.getTelefono());
			pStatement.setInt(5, obj.getIdDomicilio());
			pStatement.setString(6, obj.getRfc());
		
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}

	@Override
	public List<Proveedor> getAll() {
		String sql = "SELECT * FROM proveedor";
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				proveedores.add(MoveResult.toSupplier(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedores;
	}

	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	@Override
	public int getSize() {
		String sql = "SELECT COUNT(rfc) AS size FROM proveedor";
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
		return getSize() == 0 ? true:false;
	}
	
}
