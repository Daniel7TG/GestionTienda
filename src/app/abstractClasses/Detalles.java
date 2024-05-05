package app.abstractClasses;

public abstract class Detalles {
	
	private String codigo;
	private int cantidad;
	private double precio;
	private double total;
	private int folio;
	
	/**
	 * @param codigo
	 * @param total
	 * @param precio
	 * @param cantidad
	 */
	public Detalles(String codigo, double precio, int cantidad) {
		this.codigo = codigo;
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = precio*cantidad;
	}

	public Detalles(String codigo, double precio, int cantidad, int folio) {
		this.codigo = codigo;
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = precio*cantidad;
		this.folio = folio;
	}
	
	public Detalles(){	
	}
	
	public Detalles(String codigo) {
		this.codigo = codigo;
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
	public int getFolio() {
		return folio;
	}	
	public void setFolio(int folio) {
		this.folio = folio;
	}
	
	@Override
	public boolean equals(Object obj) {
		return codigo.equals(((Detalles)obj).codigo);			
	}
	@Override
	public String toString() {
		return "Detalles [codigo=" + codigo + ", cantidad=" + cantidad + ", precio=" + precio + ", total=" + total
				+ "]";
	}

	
	
	
}
