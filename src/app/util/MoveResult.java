package app.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.enums.Orientacion;
import app.modelos.Domicilio;
import app.modelos.Producto;
import app.modelos.Proveedor;


public abstract class MoveResult {
	
	public static Producto toProduct(ResultSet result) {
		Producto product = new Producto();
		try {
			product.setCodigoBarras(result.getString("codigo_barras"));
			product.setNombre(result.getString("nombre"));
			product.setMarca(result.getString("marca"));
			product.setTipo(result.getString("tipo"));
			product.setContenido(result.getString("contenido"));
			product.setUnidadDeMedida(result.getString("medida"));
			product.setStockMaximo(result.getInt("stock_maximo"));
			product.setStockMinimo(result.getInt("stock_minimo"));
			product.setPresentacion(result.getString("presentacion"));
			product.setDescripcion(result.getString("descripcion"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}	

	
	public static Proveedor toSupplier(ResultSet result) {
		Proveedor prov = new Proveedor();
		try {
			prov.setIdDomicilio(result.getInt("domicilio"));
			prov.setNombre(result.getString("nombre"));
			prov.setApellido(result.getString("apellido"));
			prov.setRazonSocial(result.getString("razon_social"));
			prov.setRfc(result.getString("rfc"));
			prov.setTelefono(result.getString("telefono"));
			prov.setDomicilio(toAddress(result));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prov;
	}	

	
	public static Domicilio toAddress(ResultSet result) {
		Domicilio address = new Domicilio();
		try {
			address.setCalle(result.getString("calle"));
			address.setCiudad(result.getString("ciudad"));
			address.setCodigoPostal(result.getString("codigo_postal"));
			address.setColonia(result.getString("colonia"));
			address.setEstado(result.getString("estado"));
			address.setNumero(result.getInt("numero"));
			address.setOrientacion(Orientacion.valueOf(result.getString("orientacion")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}	
	
	
	
	


}
