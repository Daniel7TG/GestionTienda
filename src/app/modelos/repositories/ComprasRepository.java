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

import app.enums.Permission;
import app.interfaces.CRUDRepository;
import app.modelos.Compra;
import app.modelos.Producto;
import app.modelos.Usuario;
import app.util.MoveResult;
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
		String sql = "INSERT INTO compra(fecha, rfc, total) "
				+ "VALUES(?, ?, ?)"; 
		try {
			pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStatement.setDate(1, Date.valueOf(compra.getFecha()));
			pStatement.setString(2, compra.getRfc());
			pStatement.setDouble(3, compra.getTotal());
			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if(resultSet.next()) return resultSet.getInt(1);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	@Override
	public Compra get(String id) {
		String sql = "SELECT compra.*, det.* FROM compra "
				+ "JOIN detalles_compra AS det ON compra.folio = det.folio "
				+ "WHERE compra.folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			Compra compra = new Compra();
			if(resultSet.next())
				compra = MoveResult.toCompra(resultSet);
			while(resultSet.next())
				compra.getDetalles().add(MoveResult.toDetallesCompra(resultSet));
			return compra;
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
		String sql = "UPDATE compra SET rfc = ?, fecha = ?, total = ? WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, c.getRfc());
			pStatement.setDate(2, Date.valueOf(c.getFecha()));
			pStatement.setDouble(3, c.getTotal());
			pStatement.setInt(4, c.getFolio());
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}

	@Override
	public List<Compra> getAll() {
		String sql = "SELECT compra.*, det.* FROM compra JOIN detalles_compra AS det ON compra.folio = det.folio";
		List<Compra> compras = new ArrayList<Compra>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int folio = resultSet.getInt("compra.folio");
				int indexOnList = compras.indexOf(new Compra(folio));
				if(indexOnList != -1) {
					compras.get(indexOnList).getDetalles().add( MoveResult.toDetallesCompra(resultSet));
				}
				else {
					compras.add(MoveResult.toCompra(resultSet));					
				}
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
		String sql = "SELECT COUNT(folio) AS size FROM compra";
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
	public int saveAll(List<Compra> obj) {
		return 0;
	}
}
