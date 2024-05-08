package app.UI.vista.consulta;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.UI.vista.general.PanelUsuarios;
import app.interfaces.Service;
import app.modelos.Usuario;

public class PanelConsultaUsuarios extends PanelUsuarios {

	public PanelConsultaUsuarios(Service<Usuario> usuarios) {
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
	
	
}
