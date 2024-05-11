package app.UI.vista.modificar;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelProveedores;
import app.enums.Permission;
import app.interfaces.Service;
import app.modelos.Domicilio;
import app.modelos.Proveedor;
import app.modelos.Usuario;

public class PanelModificarProveedor extends PanelProveedores {

	public PanelModificarProveedor(Service<Proveedor> proveedores) {
		super(proveedores, true);
	}

	
	private void modificarProveedor() {
		if(rfcField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de username no puede estar vacío");			
		} else if(usuarios.get(usernameField.getText()) == null) {
			JOptionPane.showMessageDialog(null, "El usuario no existe");
		} else if(passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "El campo de contraseña no puede estar vacío");
		} else if(confirmPasswordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "El campo de confirmar contraseña no puede estar vacío");
		} else if(Arrays.compare(confirmPasswordField.getPassword(), passwordField.getPassword()) != 0) {
			JOptionPane.showMessageDialog(null, "Las contraseñas son diferentes");
		} else if(fieldNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de nombre no puede estar vacío");
		} else if(fieldApellido.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de apellido no puede estar vacío");
		} else if(telefonoField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de teléfono no puede estar vacío");				
		} else if(permisos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede dejar todos los permisos sin marcar");				
		} else if(!panelDireccion.isValidDirection()) {} 
		else {
			String nombre = fieldNombre.getText();
			String apellido = fieldApellido.getText();
			String username = usernameField.getText();
			String telefono = telefonoField.getText();
			Domicilio domicilio = panelDireccion.getDireccion();
			domicilio.setId(usuarios.get(username).getIdDomicilio());
			String password = String.valueOf(passwordField.getPassword());
			
			Usuario user = new Usuario();
			user.setNombre(nombre);
			user.setApellido(apellido);
			user.setDomicilio(domicilio);
			user.setPassword(password);
			user.setTelefono(telefono);
			user.setUserName(username);
			user.setPermisos(permisos);
			usuarios.set(user);
			JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
			vaciarComponentes();
		}
	}


}
