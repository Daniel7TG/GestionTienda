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
import app.modelos.Producto;
import app.util.MoveResult;
import app.util.Util;

public class ProductosRepository implements CRUDRepository<Producto> {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pStatement;
	private ResultSet resultSet;
	
	
	public ProductosRepository(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public boolean exists(String id) {
		String sql = "SELECT * FROM producto WHERE codigo_barras = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeQuery().next();
		} catch (SQLException e) {}
		return false;
	}

	
	@Override
	public int save(Producto producto) {
		String sql = "INSERT INTO producto(codigo_barras, nombre, marca, tipo, contenido, medida, stock_maximo, stock_minimo, presentacion, descripcion, cantidad) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		int result = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, producto.getCodigoBarras());
			pStatement.setString(2, producto.getNombre());
			pStatement.setString(3, producto.getMarca());
			pStatement.setString(4, producto.getTipo());
			pStatement.setString(5, producto.getContenido());
			pStatement.setString(6, producto.getUnidadDeMedida());
			pStatement.setInt(7, producto.getStockMaximo());
			pStatement.setInt(8, producto.getStockMinimo());
			pStatement.setString(9, producto.getPresentacion());
			pStatement.setString(10, producto.getDescripcion());
			pStatement.setInt(11, producto.getStockActual());
			result = pStatement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@Override
	public Producto get(String id) {
		String sql = "SELECT * FROM producto WHERE codigo_barras = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return MoveResult.toProduct(resultSet);
		} catch (SQLException e) {}
		return null;
	}
	
	
	public Producto getByData(String data) {
		String sql = "SELECT * FROM producto WHERE nombre = ? AND marca = ? AND contenido = ? AND medida = ?";
		String[] args = data.split("_");
		if(args.length == 4)
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, args[0]);
			pStatement.setString(2, args[1]);
			pStatement.setDouble(3, Double.valueOf(args[2]));
			pStatement.setString(4, args[3]);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return MoveResult.toProduct(resultSet);
		} catch (SQLException e) {}
		
		return null;	
	}



	@Override
	public boolean remove(String id) {
		String sql = "DELETE FROM producto WHERE codigo_barras = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;		
	}
	

	@Override
	public boolean set(Producto p) {
		String sql = "UPDATE producto SET nombre = ?, marca = ?, tipo = ?, "
				+ "contenido = ?, medida = ?, stock_maximo = ?, stock_minimo = ?, "
				+ "presentacion = ?, descripcion = ?, cantidad = ? "
				+ "WHERE codigo_barras = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, p.getNombre());
			pStatement.setString(2, p.getMarca());
			pStatement.setString(3, p.getTipo());
			pStatement.setString(4, p.getContenido());
			pStatement.setString(5, p.getUnidadDeMedida());
			pStatement.setInt(6, p.getStockMaximo());
			pStatement.setInt(7, p.getStockMinimo());
			pStatement.setString(8, p.getPresentacion());
			pStatement.setString(9, p.getDescripcion());
			pStatement.setInt(10, p.getStockActual());
			pStatement.setString(11, p.getCodigoBarras());
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}

	
	@Override
	public List<Producto> getAll() {
		String sql = "SELECT * FROM producto";
		List<Producto> productos = new ArrayList<Producto>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				productos.add(MoveResult.toProduct(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

	
	@Override
	public Object[][] getMatrix() {
		return Util.anyToString(getAll());
	}

	
	@Override
	public int getSize() {
		String sql = "SELECT COUNT(codigo_barras) AS size FROM producto";
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

	


}
