package app.UI.vista.eliminar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		
		usuarios.remove(usernameField.getText());
		JOptionPane.showMessageDialog(null, "Usuario Eliminado");
		vaciarComponentes();
	}
	
	
}
