package app.modelos;

public class DetallesVenta {
	String codigo;
	double precio;
	int cantidad;
	double total;
	
	/**
	 * @param codigo
	 * @param precio
	 * @param cantidad
	 * @param total
	 */
	public DetallesVenta(String codigo, double precio, int cantidad) {
		this.codigo = codigo;
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = precio*cantidad;
	}
	public DetallesVenta() {}
	
	
	@Override
	public String toString() {
		return "DetallesVenta [codigo=" + codigo + ", precio=" + precio + ", cantidad=" + cantidad + ", total=" + total
				+ "]";
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.total = precio*cantidad;
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.total = precio*cantidad;
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public boolean equals(Object obj) {
		return codigo.equals(((DetallesVenta)obj).codigo);
	}
	
	
}
