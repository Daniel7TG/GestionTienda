package app.UI.vista.menus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;

public class PanelMenuCompra extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton registrarButton;
	private JButton listarButton;

	public PanelMenuCompra(Usuario usuario) {

		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));

		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.WRITE_COMPRA);
		if(!usuario.hasAccessTo(Permission.WRITE_COMPRA)) registrarButton.setEnabled(false);
		
		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_COMPRA);
		if(!usuario.hasAccessTo(Permission.READ_COMPRA)) listarButton.setEnabled(false);
				
		add(registrarButton);
		add(listarButton);
	}
	
	public void enableButtons(boolean status) {
		registrarButton.setEnabled(status);
		listarButton.setEnabled(status);
	}

	public JButton getRegistrarButton() {
		return registrarButton;
	}
	
	public JButton getListarButton() {
		return listarButton;
	}

}
