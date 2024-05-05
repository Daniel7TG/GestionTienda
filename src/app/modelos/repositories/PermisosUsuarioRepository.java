package app.modelos.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import app.interfaces.CRUDRepository;

public class PermisosUsuarioRepository {

	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public PermisosUsuarioRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void save(String username, int permissionId) {
		String sql = "INSERT INTO usuario_permisos(username, permiso_id) "
				+ "VALUES(?, ?)"; 
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setInt(2, permissionId);
			pStatement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void deleteAll(String username) {
		String sql = "DELETE FROM usuario_permisos "
				+ "WHERE username = ?"; 
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.executeUpdate();
			System.out.println("deleted\n" + pStatement.toString());
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
}
