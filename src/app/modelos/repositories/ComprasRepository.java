package app.modelos.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.interfaces.CRUDRepository;
import app.modelos.Compra;
import app.modelos.Producto;
import app.util.Util;

public class ComprasRepository implements CRUDRepository<Compra> {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;
	
	
	public ComprasRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public boolean exists(String id) {
		String sql = "SELECT * FROM compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {}
		return false;	
	}

	
	@Override
	public int save(Compra compra) {
		String sql = "INSERT INTO compra(fecha, rfc) "
				+ "VALUES(?, ?)"; 
		try {
			pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStatement.setDate(1, Date.valueOf(compra.getFecha()));
			pStatement.setString(2, compra.getRfc());
			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if(resultSet.next()) return resultSet.getInt(1);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	@Override
	public Compra get(String id) {
		String sql = "SELECT * FROM compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return toCompra(resultSet);
		} catch (SQLException e) {}
		return null;	
	}

	
	@Override
	public boolean remove(String id) {
		String sql = "DELETE FROM compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.valueOf(id) );
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;	
	}

	@Override
	public boolean set(Compra c) {
		String sql = "UPDATE compra SET rfc = ?, fecha = ? WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, c.getRfc());
			pStatement.setDate(2, Date.valueOf(c.getFecha()));
			pStatement.setInt(3, c.getFolio());
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}

	@Override
	public List<Compra> getAll() {
		String sql = "SELECT * FROM compra";
		List<Compra> compras = new ArrayList<Compra>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				compras.add(toCompra(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compras;
	}

	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	@Override
	public int getSize() {
		String sql = "SELECT COUNT(folio) AS size FROM producto";
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

	
	private Compra toCompra(ResultSet rs) {
		Compra compra = new Compra();
		try {
			compra.setFecha(rs.getDate("fecha").toLocalDate());
			compra.setFolio(rs.getInt("folio"));
			compra.setRfc(rs.getString("rfc"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return compra;
	}
}
