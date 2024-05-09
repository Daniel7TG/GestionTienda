package app.UI.vista.menus;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;


public class PanelMenuVenta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton registrarButton;
	private JButton listarButton;

	/**
	 * Create the panel.
	 */
	public PanelMenuVenta(Usuario usuario) {
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));


		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.WRITE_VENTA); // "Crear venta"
		if(!usuario.hasAccessTo(Permission.WRITE_VENTA)) registrarButton.setEnabled(false);

		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_VENTA);
		if(!usuario.hasAccessTo(Permission.READ_VENTA)) listarButton.setEnabled(false);
	
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
