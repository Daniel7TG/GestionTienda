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
	private JButton listarButton;
	
	public PanelMenuEmpleados(Usuario usuario){
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));
		
		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_USUARIOS);
		if(!usuario.hasAccessTo(Permission.ADD_USUARIOS)) registrarButton.setEnabled(false);
		
		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_USUARIOS);
		if(!usuario.hasAccessTo(Permission.READ_USUARIOS)) listarButton.setEnabled(false);
		
		add(registrarButton);
		add(listarButton);
	}


	public JButton getRegistrarButton() {
		return registrarButton;
	}
	public void setRegistrarButton(JButton registrarButton) {
		this.registrarButton = registrarButton;
	}
	public JButton getListarButton() {
		return listarButton;
	}
	public void setListarButton(JButton listarButton) {
		this.listarButton = listarButton;
	}
	
}
