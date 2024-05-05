package app.modelos.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.modelos.Compra;
import app.modelos.Venta;
import app.util.MoveResult;
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
			pStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStatement.setDouble(1, venta.getTotal());
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
		String sql = "SELECT venta.*, det.* FROM venta "
				+ "JOIN detalles_venta AS det ON venta.folio = det.folio "
				+ "WHERE venta.folio = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			Venta venta = new Venta();
			if(resultSet.next())
				venta = MoveResult.toVenta(resultSet);
			while(resultSet.next())
				venta.getDetalles().add(MoveResult.toDetallesVenta(resultSet));
			return venta;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean remove(String id) {
		String sql = "DELETE FROM venta WHERE folio = ?";
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
		String sql = "UPDATE venta SET total = ?, fecha = ?, username = ? WHERE folio = ? ";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setDouble(1, venta.getTotal());
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
		String sql = "SELECT venta.*, det.* FROM venta JOIN detalles_venta AS det ON venta.folio = det.folio";
		List<Venta> ventas = new ArrayList<Venta>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int folio = resultSet.getInt("compra.folio");
				int indexOnList = ventas.indexOf(new Venta(folio));
				if(indexOnList != -1) {
					ventas.get(indexOnList).getDetalles().add( MoveResult.toDetallesVenta(resultSet));
				}
				else {
					ventas.add(MoveResult.toVenta(resultSet));					
				}
			}
		} catch (SQLException e) {
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

}










