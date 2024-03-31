package app.modelos;

public class DetallesCompra {
	String codigo;
	double total;
	double precio;
	int cantidad;
	
	
	/**
	 * @param codigo
	 * @param total
	 * @param precio
	 * @param cantidad
	 */
	public DetallesCompra(String codigo, double total, double precio, int cantidad) {
		this.codigo = codigo;
		this.total = total;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
		
}
