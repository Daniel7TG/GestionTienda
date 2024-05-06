package app.UI.vista.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;

public class PanelMenuEmpleados extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton registrarButton;
	private JButton consultarButton;
	private JButton eliminarButton;
	private JButton modificarButton;
	private JButton listarButton;
	
	public PanelMenuEmpleados(Usuario usuario){
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));
		
		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_USUARIOS);
		if(!usuario.hasAccessTo(Permission.ADD_USUARIOS)) registrarButton.setEnabled(false);
		
		consultarButton = new MenuButton("Consultar", KeyEvent.VK_L, Permission.READ_USUARIOS);
		if(!usuario.hasAccessTo(Permission.READ_USUARIOS)) consultarButton.setEnabled(false);
		
		eliminarButton = new MenuButton("Eliminar", KeyEvent.VK_L, Permission.DELETE_USUARIOS);
		if(!usuario.hasAccessTo(Permission.DELETE_USUARIOS)) eliminarButton.setEnabled(false);
		
		modificarButton = new MenuButton("Modificar", KeyEvent.VK_L, Permission.MODIFY_USUARIOS);
		if(!usuario.hasAccessTo(Permission.MODIFY_USUARIOS)) modificarButton.setEnabled(false);
		
		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_USUARIOS);
		if(!usuario.hasAccessTo(Permission.READ_USUARIOS)) listarButton.setEnabled(false);
		
		
		add(registrarButton);
		add(consultarButton);
		add(eliminarButton);
		add(modificarButton);
		add(listarButton);
	}
	
	
	public void enableButtons(boolean status) {
		registrarButton.setEnabled(status);
		consultarButton.setEnabled(status);
		eliminarButton.setEnabled(status);
		modificarButton.setEnabled(status);
		listarButton.setEnabled(status);
	}

	public JButton getRegistrarButton() {
		return registrarButton;
	}

	public JButton getConsultarButton() {
		return consultarButton;
	}

	public JButton getEliminarButton() {
		return eliminarButton;
	}
	
	public JButton getModificarButton() {
		return modificarButton;
	}

	public JButton getListarButton() {
		return listarButton;
	}
	
}
