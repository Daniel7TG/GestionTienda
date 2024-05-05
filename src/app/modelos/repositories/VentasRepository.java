package app.modelos.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.modelos.Venta;
import app.util.Util;

public class VentasRepository {

	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public VentasRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean exists(String id) {
		String sql = "SELECT * FROM venta WHERE folio = ? ";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			return resultSet.next();

		}catch (SQLException e) {}
		return false;	
	}

	public int save(Venta venta) {
		String sql = "INSERT INTO venta(total, fecha, username) VALUES(?, ?, ?)";
		try {
			pStatement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, (int) venta.getTotal());
			pStatement.setDate(2, Date.valueOf(venta.getFecha()));
			pStatement.setString(3, venta.getUserName());
			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if(resultSet.next()) return resultSet.getInt(1);

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Venta get(String id) {
		String sql = "SELECT * FROM venta WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next()) return toVenta(resultSet);

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean remove(String id) {
		String sql = "SELECT * FROM venta WHERE folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.valueOf(id));
			return pStatement.executeUpdate() != 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean set(Venta venta) {
		String sql = "UPDATE venta SET total = ?, username = ? WHERE folio = ? ";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, (int) venta.getTotal());
			pStatement.setDate(2, Date.valueOf(venta.getFecha()));
			pStatement.setString(3, venta.getUserName());
			pStatement.setInt(4, venta.getFolio());
			return pStatement.executeUpdate() != 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public List<Venta> getAll(){
		String sql = "SELECT * FROM venta";
		List<Venta> ventas = new ArrayList<>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				ventas.add(toVenta(resultSet));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return ventas;
	}


	public Object[][] getMatrix(){
		return Util.anyToString(getAll());
	}


	public int getSize() {
		String sql = "SELECT COUNT(folio) AS size FROM venta";
		try {
			resultSet = statement.executeQuery(sql);
			return resultSet.getInt("size");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}


	private Venta toVenta(ResultSet rs) {
		Venta venta = new Venta();	
		try {
			venta.setFolio(rs.getInt("folio"));
			venta.setTotal(rs.getInt("total"));
			venta.setFecha(rs.getDate("fecha").toLocalDate());
			venta.setUserName(rs.getString("username"));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return venta;
	}

}










