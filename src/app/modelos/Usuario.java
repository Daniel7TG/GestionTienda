package app.modelos;

import java.util.ArrayList;
import java.util.List;

import app.abstractClasses.Persona;
import app.enums.Permission;

public class Usuario extends Persona {

	private List<Permission> permisos;
	private String userName;
	private String password;

	/**
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param idDomicilio
	 * @param domicilio
	 * @param userID
	 * @param permisos
	 * @param userName
	 * @param password
	 */
	public Usuario(String nombre, String apellido, String telefono, int idDomicilio, Domicilio domicilio, int userID,
			List<Permission> permisos, String userName, String password) {
		super(nombre, apellido, telefono, idDomicilio, domicilio);
		this.permisos = permisos;
		this.userName = userName;
		this.password = password;
	}

	public Usuario(String user) {
		this.userName = user;
	}
	
	
	public Usuario() {
		permisos = new ArrayList<Permission>();
	}

	public boolean hasAccessTo(Permission p) {
		if(permisos.contains(Permission.ADMIN)) return true;
		return permisos.stream().anyMatch(user_p -> 
			p == user_p | 
			(p.getCategory() == user_p.getCategory() & user_p.isUniversal())
		);
	}
	
	
	public List<Permission> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<Permission> permisos) {
		this.permisos = permisos;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Usuario [permisos=" + permisos + ", userName=" + userName + ", password="
				+ password + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", idDomicilio=" + idDomicilio + ", domicilio=" + domicilio + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Usuario u)
			return userName.equals(u.userName);
		return false;
	}
	
	
}
