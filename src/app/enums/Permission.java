package app.enums;

public enum Permission {

	ADMIN(0, true),
	READ_VENTA(1, false), WRITE_VENTA(1, false), 
	READ_COMPRA(2, false), WRITE_COMPRA(2, false),
	READ_PRODUCTOS(3, false), CRUD_PRODUCTOS(3, true), ADD_PRODUCTOS(3, false), DELETE_PRODUCTOS(3, false), MODIFY_PRODUCTOS(3, false),
	READ_PROVEEDORES(4, false), CRUD_PROVEEDORES(4, true), ADD_PROVEEDORES(4, false), DELETE_PROVEEDORES(4, false), MODIFY_PROVEEDORES(4, false),
	READ_USUARIOS(5, false), CRUD_USUARIOS(5, true), ADD_USUARIOS(5, false), DELETE_USUARIOS(5, false), MODIFY_USUARIOS(5, false);
	
	private int category;
	private boolean universal;
	
	private Permission(int category, boolean universal) {
		this.category = category;
		this.universal = universal;
	}
	public int getCategory() {
		return category;
	}
	public boolean isUniversal() {
		return universal;
	}
}
