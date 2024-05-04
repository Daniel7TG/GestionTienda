package app.modelos.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;

import app.interfaces.CRUDRepository;
import app.modelos.Domicilio;
import app.util.MoveResult;
import app.util.Util;

public class DomicilioRepository implements CRUDRepository<Domicilio> {
	
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public DomicilioRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * @param Domicilio  
	 * @return ID of the row created in the database if inserted, otherwise -1
	 */
	public int save(Domicilio domicilio) {
		String sql = "INSERT INTO domicilio(numero, orientacion, colonia, estado, codigo_postal, calle) "
				+ "VALUES(?, ?, ?, ?, ?, ?)"; 
		int result = -1;
		try {
			pStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, domicilio.getNumero());
			pStatement.setString(2, domicilio.getOrientacion().toString());
			pStatement.setString(3, domicilio.getColonia());
			pStatement.setString(4, domicilio.getEstado());
			pStatement.setString(5, domicilio.getCodigoPostal());
			pStatement.setString(6, domicilio.getCalle());
			pStatement.executeUpdate();
			ResultSet keys = pStatement.getGeneratedKeys();
			
			return keys.next() ? keys.getInt(1) : -1;
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public Domicilio get(String id) {
		String sql = "SELECT * FROM proveedor JOIN domicilio ON proveedores.domicilio = domicilio.id WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return MoveResult.toAddress(resultSet);
		} catch (SQLException e) {}
		return null;	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean set(Domicilio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Domicilio> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
}
