package app.UI.vista.captura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelUsuarios;
import app.enums.Permission;
import app.interfaces.Service;
import app.modelos.Domicilio;
import app.modelos.Usuario;

public class PanelCapturaUsuario extends PanelUsuarios {

	public PanelCapturaUsuario(Service<Usuario> usuarios) {
		super(usuarios, false);

	}
	
	public void guardarUsuario() {		
		List<Permission> permisos = getPermisos();
		if(usernameField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de username no puede estar vacío");			
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
		} else if(usuarios.exists(usernameField.getText())) {
			JOptionPane.showMessageDialog(null, "Nombre de usuario en uso");
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
			String password = String.valueOf(passwordField.getPassword());
			
			Usuario user = new Usuario();
			user.setNombre(nombre);
			user.setApellido(apellido);
			user.setDomicilio(domicilio);
			user.setPassword(password);
			user.setTelefono(telefono);
			user.setUserName(username);
			user.setPermisos(permisos);
			usuarios.save(user);
			JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
			vaciarComponentes();
		}
	}
	
	
	public List<Permission> getPermisos(){
		List<Permission> todosLosPermisos = new ArrayList<Permission>();
		
		if(writeCompraBtn.isSelected()) todosLosPermisos.add(Permission.WRITE_COMPRA); 
		if(writeVentaBtn.isSelected()) todosLosPermisos.add(Permission.WRITE_VENTA); 
		if(readCompraBtn.isSelected()) todosLosPermisos.add(Permission.READ_COMPRA); 
		if(readVentaBtn.isSelected()) todosLosPermisos.add(Permission.READ_VENTA); 
		
		todosLosPermisos.addAll(productosPermission.getPressed());
		todosLosPermisos.addAll(proveedoresPermission.getPressed());
		todosLosPermisos.addAll(usuariosPermission.getPressed());
		return todosLosPermisos;
	}
	
	

}
