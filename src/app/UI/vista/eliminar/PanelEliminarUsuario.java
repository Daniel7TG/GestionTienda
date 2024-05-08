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
		usernameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Usuario user = usuarios.get(usernameField.getText());
				if(user != null) {
					autocompleteFields(user);
				}				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				Usuario user = usuarios.get(usernameField.getText());
				if(user != null) {
					autocompleteFields(user);
				}				
			}
		}); 
		add(usernameField, gbc_rfcField);
		setUnneditable();
	}
	
	
	public void eliminarUsuario() {
		if(usuarios.exists(usernameField.getText()) == false) {
			JOptionPane.showMessageDialog(null, "No existe este usuario");
			return;
		}
		
		usuarios.remove(usernameField.getText());
		JOptionPane.showMessageDialog(null, "UsuarioEliminado");
		vaciarComponentes();
	}
	
	
}
