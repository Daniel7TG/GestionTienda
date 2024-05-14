package app.UI.vista.eliminar;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelUsuarios;
import app.interfaces.Service;
import app.modelos.Usuario;

public class PanelEliminarUsuario extends PanelUsuarios {

	public PanelEliminarUsuario(Service<Usuario> usuarios) {
		super(usuarios, true);
		setUnneditable();
	}
	
	
	public void eliminarUsuario() {
		if(!usuarios.exists(usernameField.getText())) {
			JOptionPane.showMessageDialog(null, "No existe este usuario");
			return;
		}
		if(usernameField.getText().equals("root")) {
			JOptionPane.showMessageDialog(null, "No se puede eliminar al administrador");
			return;
		}
		usuarios.remove(usernameField.getText());
		JOptionPane.showMessageDialog(null, "Usuario Eliminado");
		vaciarComponentes();
	}
	
	
}
