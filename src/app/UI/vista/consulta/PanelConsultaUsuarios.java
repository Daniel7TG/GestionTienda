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
		setUnneditable();
	}
	
}
