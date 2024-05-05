package app.modelos.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.interfaces.CRUDRepository;
import app.modelos.DetallesCompra;
import app.util.MoveResult;
import app.util.Util;

public class DetallesCompraRepository implements CRUDRepository<DetallesCompra> {

	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;
	
	
	public DetallesCompraRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public boolean exists(String id) {
		String sql = "SELECT * FROM detalles_compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {}
		return false;
	}

	@Override
	public int save(DetallesCompra det) {
		String sql = "INSERT INTO detalles_compra(fecha_caducidad, cantidad, precio, total, folio, codigo) "
				+ "VALUES(?, ?, ?, ?, ?, ?)"; 
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setDate(1, Date.valueOf(det.getFechaCaducidad()));
			pStatement.setInt(2, det.getCantidad());
			pStatement.setDouble(3, det.getPrecio());
			pStatement.setDouble(4, det.getTotal());
			pStatement.setInt(5, det.getFolio());
			pStatement.setString(6, det.getCodigo());
			return pStatement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public DetallesCompra get(String id) {
		String sql = "SELECT * FROM detalles_compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.valueOf(id));
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return MoveResult.toDetallesCompra(resultSet);
		} catch (SQLException e) {}
		return null;
	}

	public List<DetallesCompra> getByFolio(String folio) {
		String sql = "SELECT * FROM detalles_compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.valueOf(folio));
			resultSet = pStatement.executeQuery();
			List<DetallesCompra> detalles = new ArrayList<DetallesCompra>();
			while(resultSet.next())
				detalles.add(MoveResult.toDetallesCompra(resultSet));
		} catch (SQLException e) {}
		return null;		
	}
	
	
	@Override
	public boolean remove(String id) {
		String sql = "DELETE FROM detalles_compra WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.valueOf(id));
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;	
	}

	
	@Override
	public boolean set(DetallesCompra det) {
		String sql = "UPDATE detalles_compra "
				+ "SET fecha_caducidad = ?, cantidad = ?, precio = ?, total = ? "
				+ "WHERE folio = ? AND codigo = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setDate(1, Date.valueOf(det.getFechaCaducidad()));
			pStatement.setInt(2, det.getCantidad());
			pStatement.setDouble(3, det.getPrecio());
			pStatement.setDouble(4, det.getTotal());
			pStatement.setInt(5, det.getFolio());
			pStatement.setString(6, det.getCodigo());
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	}

	@Override
	public List<DetallesCompra> getAll() {
		String sql = "SELECT * FROM detalles_compra";
		List<DetallesCompra> detalles = new ArrayList<DetallesCompra>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				detalles.add(MoveResult.toDetallesCompra(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detalles;		
	}

	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	@Override
	public int getSize() {
		String sql = "SELECT COUNT(folio) AS size FROM detalles_compra";
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
	public int saveAll(List<DetallesCompra> detList) {
		String sql = "INSERT INTO detalles_compra(fecha_caducidad, cantidad, precio, total, folio, codigo) "
				+ "VALUES(?, ?, ?, ?, ?, ?)"; 
		int cantidad = 0;
		for(DetallesCompra det : detList)
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setDate(1, Date.valueOf(LocalDate.now().plusMonths(1L)));
			pStatement.setInt(2, det.getCantidad());
			pStatement.setDouble(3, det.getPrecio());
			pStatement.setDouble(4, det.getTotal());
			pStatement.setInt(5, det.getFolio());
			pStatement.setString(6, det.getCodigo());
			pStatement.executeUpdate();
			cantidad++;
		} catch (SQLException e) {
			e.printStackTrace();
			return cantidad;
		} 
		return cantidad;	
	}

}
