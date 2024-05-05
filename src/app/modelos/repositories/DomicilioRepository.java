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
		String sql = "SELECT * FROM domicilio WHERE id = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {}
		return false;	
	}

	@Override
	/**
	 * @param Domicilio  
	 * @return ID of the row created in the database if inserted, otherwise -1
	 */
	public int save(Domicilio domicilio) {
		String sql = "INSERT INTO domicilio(numero, orientacion, colonia, estado, codigo_postal, calle, ciudad) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)"; 
		int result = -1;
		try {
			pStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, domicilio.getNumero());
			pStatement.setString(2, domicilio.getOrientacion().toString());
			pStatement.setString(3, domicilio.getColonia());
			pStatement.setString(4, domicilio.getEstado());
			pStatement.setString(5, domicilio.getCodigoPostal());
			pStatement.setString(6, domicilio.getCalle());
			pStatement.setString(7, domicilio.getCiudad());
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
		String sql = "SELECT * FROM domicilio WHERE id = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return MoveResult.toAddress(resultSet);
		} catch (SQLException e) {}
		return null;	
	}

	@Override
	public boolean remove(String id) {
		String sql = "DELETE FROM domicilio WHERE id = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;		
	}

	@Override
	public boolean set(Domicilio obj) {
		String sql = "UPDATE domicilio SET numero = ?, calle = ?, "
				+ "ciudad = ?, codigo_postal = ?, colonia = ?, "
				+ "estado = ?, orientacion = ? "
				+ "WHERE id = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			
			pStatement.setInt(1, obj.getNumero());
			pStatement.setString(2, obj.getCalle());
			pStatement.setString(2, obj.getCiudad());
			pStatement.setString(3, obj.getCodigoPostal());
			pStatement.setString(4, obj.getColonia());
			pStatement.setString(5, obj.getEstado());
			pStatement.setString(6, obj.getOrientacion().toString());
	
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}

	@Override
	public List<Domicilio> getAll() {
		String sql = "SELECT * FROM domicilio";
		List<Domicilio> domicilios = new ArrayList<Domicilio>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) 
				domicilios.add(MoveResult.toAddress(resultSet));
		} catch (SQLException e) {}
	
		return null;	
	}

	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	@Override
	public int getSize() {
		String sql = "SELECT COUNT(id) AS size FROM domicilio";

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


	@Override
	public int saveAll(List<Domicilio> obj) {
		return 0;
	}


	
	
}
