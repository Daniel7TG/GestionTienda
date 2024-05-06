package app.modelos.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.interfaces.CRUDRepository;
import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.util.MoveResult;

public class DetallesVentaRepository implements CRUDRepository<DetallesVenta> {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;
	
	
	public DetallesVentaRepository(Connection connection) {
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
	public int save(DetallesVenta obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveAll(List<DetallesVenta> detList) {
		String sql = "INSERT INTO detalles_venta(cantidad, precio, total, folio, codigo) "
				+ "VALUES(?, ?, ?, ?, ?)"; 
		int cantidad = 0;
		for(DetallesVenta det : detList)
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, det.getCantidad());
			pStatement.setDouble(2, det.getPrecio());
			pStatement.setDouble(3, det.getTotal());
			pStatement.setInt(4, det.getFolio());
			pStatement.setString(5, det.getCodigo());
			pStatement.executeUpdate();
			cantidad++;
		} catch (SQLException e) {
			e.printStackTrace();
			return cantidad;
		} 
		return cantidad;	
	}

	@Override
	public DetallesVenta get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean set(DetallesVenta obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DetallesVenta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[][] getMatrix() {
		// TODO Auto-generated method stub
		return null;
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
