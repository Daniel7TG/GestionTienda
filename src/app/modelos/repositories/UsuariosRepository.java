package app.modelos.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.enums.Permission;
import app.interfaces.CRUDRepository;
import app.modelos.Proveedor;
import app.modelos.Usuario;
import app.util.MoveResult;
import app.util.Util;

public class UsuariosRepository implements CRUDRepository<Usuario> {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public UsuariosRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	@Override
	public boolean exists(String id) {
		String sql = "SELECT * FROM usuario WHERE username = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeQuery().next();
		} catch (SQLException e) {}
		return false;
	}

	
	@Override
	public int save(Usuario usuario) {

		String sql = "INSERT INTO usuario(username, password, nombre, apellido, telefono, domicilio) "
				+ "VALUES(?, ?, ?, ?, ?, ?)"; 
		int result = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, usuario.getUserName());
			pStatement.setString(2, usuario.getPassword());
			pStatement.setString(3, usuario.getNombre());
			pStatement.setString(4, usuario.getApellido());
			pStatement.setString(5, usuario.getTelefono());
			pStatement.setInt(6, usuario.getIdDomicilio());

			result = pStatement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@Override
	public Usuario get(String id) {
		String sql = "SELECT u.*, d.numero, d.orientacion, d.colonia, d.estado, d.codigo_postal, d.calle, d.ciudad, p.permiso_id, permisos.descripcion "
				+ "FROM usuario AS u "
				+ "JOIN domicilio AS d ON u.domicilio = d.id "
				+ "JOIN usuario_permisos AS p ON u.username = p.username "
				+ "JOIN permisos ON p.permiso_id = permisos.id "
				+ "WHERE u.username = ?";

		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			Usuario user = new Usuario();
			if(resultSet.next())
				user = MoveResult.toUser(resultSet);
			while(resultSet.next())
				user.getPermisos().add(Permission.valueOf(resultSet.getString("descripcion")));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean remove(String id) {
		String sql = "DELETE FROM usuario WHERE username = ?";

		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;	
	}

	@Override
	public boolean set(Usuario obj) {
		// DEJAMOS EL MISMO id_domicilio, actualizamos el mismo domicilio en el controlador 
		String sql = "UPDATE usuario SET nombre = ?, apellido = ?, "
				+ "username = ?, telefono = ?, password = ? "
				+ "WHERE username = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			
			pStatement.setString(1, obj.getNombre());
			pStatement.setString(2, obj.getApellido());
			pStatement.setString(3, obj.getUserName());
			pStatement.setString(4, obj.getTelefono());
			pStatement.setString(5, obj.getPassword());
			pStatement.setString(6, obj.getUserName());
	
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}

	@Override
	public List<Usuario> getAll() {
		String sql = "SELECT u.*, d.numero, d.orientacion, d.colonia, d.estado, d.codigo_postal, d.calle, d.ciudad, p.permiso_id, permisos.descripcion "
				+ "FROM usuario AS u "
				+ "JOIN domicilio AS d ON u.domicilio = d.id "
				+ "JOIN usuario_permisos AS p ON u.username = p.username "
				+ "JOIN permisos ON p.permiso_id = permisos.id ";

		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String user = resultSet.getString("u.username");
				int indexOnList = usuarios.indexOf(new Usuario(user));
				if(indexOnList != -1) {
					usuarios.get(indexOnList).getPermisos()
					.add(Permission.valueOf(resultSet.getString("permisos.descripcion")));
				}
				else {
					usuarios.add(MoveResult.toUser(resultSet));					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	@Override
	public int getSize() {
		String sql = "SELECT COUNT(username) AS size FROM usuario";

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
	public int saveAll(List<Usuario> obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
