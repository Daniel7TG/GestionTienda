package app.modelos.repositories;

import java.util.List;

import app.enums.Orientacion;
import app.interfaces.CRUDRepository;
<<<<<<< Updated upstream
=======
import app.modelos.Domicilio;
import app.modelos.Producto;
>>>>>>> Stashed changes
import app.modelos.Proveedor;

public class ProveedoresRepository implements CRUDRepository<Proveedor> {

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
<<<<<<< Updated upstream
	public int save(Proveedor obj) {
		// TODO Auto-generated method stub
		return 0;
=======
	public int save(Proveedor proveedor) {
		String sql = "INSERT INTO proveedor(rfc, nombre, apellido, razon_social, telefono, domicilio) "
				+ "VALUES(?, ?, ?, ?, ?, ?)"; 
		int result = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, proveedor.getRfc());
			pStatement.setString(2, proveedor.getNombre());
			pStatement.setString(3, proveedor.getApellido());
			pStatement.setString(4, proveedor.getRazonSocial());
			pStatement.setString(5, proveedor.getTelefono());
			pStatement.setInt(6, proveedor.getIdDomicilio());
			result = pStatement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
>>>>>>> Stashed changes
	}

	@Override
	public Proveedor get(String id) {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
=======
		String sql = "SELECT * FROM proveedor JOIN domicilio ON proveedores.domicilio = domicilio.id WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				return moveToSupplier(resultSet);
		} catch (SQLException e) {}
>>>>>>> Stashed changes
		return null;
	}

	@Override
	public boolean remove(String id) {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
		return false;
=======
		String sql = "DELETE FROM proveedor WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, id);
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {}
		return false;	
>>>>>>> Stashed changes
	}

	@Override
	public boolean set(Proveedor obj) {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
		return false;
=======
		String sql = "UPDATE proveedor SET nombre = ?, apellido = ?, "
				+ "razon_social = ?, telefono = ?, domicilio = ? "
				+ "JOIN domicilio ON proveedores.domicilio = domicilio.id "
				+ "WHERE rfc = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, obj.getNombre());
			pStatement.setString(2, obj.getApellido());
			pStatement.setString(3, obj.getRazonSocial());
			pStatement.setString(4, obj.getTelefono());
			pStatement.setInt(5, obj.getIdDomicilio());
			pStatement.setString(6, obj.getRfc());
		
			return pStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
>>>>>>> Stashed changes
	}

	@Override
	public List<Proveedor> getAll() {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
		return null;
=======
		String sql = "SELECT * FROM proveedor";
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		try {
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				proveedores.add(moveToSupplier(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedores;
>>>>>>> Stashed changes
	}

	@Override
	public Object[][] getMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
=======
		String sql = "SELECT COUNT(rfc) AS size FROM proveedor";
		try {
			resultSet = statement.executeQuery(sql);
			return resultSet.getInt("size");
		} catch (SQLException e) {
			e.printStackTrace();
		}
>>>>>>> Stashed changes
		return 0;
	}

	@Override
	public boolean isEmpty() {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
		return false;
	}
=======
		return getSize() == 0 ? true:false;
	}
	
>>>>>>> Stashed changes

	
	
}
