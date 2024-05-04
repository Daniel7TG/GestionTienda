package app.modelos;

import java.util.List;

import app.abstractClasses.Persona;
import app.enums.Permission;

public class Usuario extends Persona {

	private int userID;
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
		this.userID = userID;
		this.permisos = permisos;
		this.userName = userName;
		this.password = password;
	}

	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
}
