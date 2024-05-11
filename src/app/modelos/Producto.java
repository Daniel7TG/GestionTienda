package app.modelos;


import app.interfaces.Listable;

/**
 * @author odtgo
 */
public class Producto implements Listable {
	private String codigoBarras;
	private String nombre;	
	private String marca;
	private String tipo;
	private String contenido;
	private String unidadDeMedida;
	private String presentacion;
	private int stockMaximo;
	private int stockMinimo;
	private String descripcion;
	private double precioVenta;
	private int stockActual;
		
	/**
	 * @param codigoBarras
	 * @param nombre
	 * @param marca
	 * @param tipo
	 * @param contenido
	 * @param unidadDeMedida
	 * @param presentacion
	 * @param stockMaximo
	 * @param stockMinimo
	 * @param descripcion
	 */
	public Producto(String codigoBarras, String nombre, String marca, String tipo, String contenido,
			String unidadDeMedida, String presentacion, int stockMaximo, int stockMinimo, String descripcion) {
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.marca = marca;
		this.tipo = tipo;
		this.contenido = contenido;
		this.unidadDeMedida = unidadDeMedida;
		this.presentacion = presentacion;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.descripcion = descripcion;
	}

	public Producto(String codigoBarras, String nombre, String marca, String tipo, String contenido,
			String unidadDeMedida, String presentacion, int stockMaximo, int stockMinimo, String descripcion, double precioVenta) {
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.marca = marca;
		this.tipo = tipo;
		this.contenido = contenido;
		this.unidadDeMedida = unidadDeMedida;
		this.presentacion = presentacion;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.stockActual = 0;
	}
	public Producto(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Producto() {
	}
	
	public String getMainData() {
		return String.format("%s_%s_%s_%s", nombre, marca, contenido, unidadDeMedida);
	}
	public void setMainData(String data) {
		String[] dataList = data.split("_");
		this.codigoBarras = "null";
		if(dataList.length < 4) return;
		this.nombre = dataList[0];
		this.marca = dataList[1];
		this.contenido = dataList[2];
		this.unidadDeMedida = dataList[3];
	}
	
//	@Override
//	protected void finalize() throws Throwable {
//		System.out.println("finalize");
//	}
	
	public void addStock(int value) {
		stockActual += value;
	}
	public void subtractStock(int value) {
		stockActual -= value;
	}
	/**
	 * @param value to add to actual Stock
	 * @return true if can be added considering stock maximo
	 */
	public boolean valStockMax(int value) {
		return stockActual + value <= stockMaximo;
	}
	/**
	 * @param value to add to actual Stock
	 * @return true if can be subtracted considering stock minimo
	 */
	public boolean valStockMin(int value) {
		return stockActual - value >= stockMinimo;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public int getStockMaximo() {
		return stockMaximo;
	}
	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}
	public int getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getStockActual() {
		return stockActual;
	}
	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	@Override
	public String toString() {
		return "Producto [codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", marca=" + marca + ", tipo=" + tipo
				+ ", contenido=" + contenido + ", unidadDeMedida=" + unidadDeMedida + ", presentacion=" + presentacion
				+ ", stockMaximo=" + stockMaximo + ", stockMinimo=" + stockMinimo + ", descripcion=" + descripcion
				+ "]";
	}
	public boolean equals(Object o) {
		return this.codigoBarras.equals(((Producto)o).codigoBarras) | this.getMainData().equals(((Producto)o).getMainData());
	}


	@Override
	public Object[] toRow() {
		return new Object[]{codigoBarras, nombre, marca, tipo, contenido, unidadDeMedida, presentacion, stockMaximo, stockMinimo, descripcion, precioVenta, stockActual};
	}
}
