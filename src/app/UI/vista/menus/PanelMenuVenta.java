package app.UI.vista.menus;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;


public class PanelMenuVenta extends PanelMenu {

	private static final long serialVersionUID = 1L;
	private MenuButton registrarButton;
	private MenuButton listarButton;

	public PanelMenuVenta(Usuario usuario) {
		super("Venta", usuario);
		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.WRITE_VENTA); // "Crear venta"
		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_VENTA);
		addButtons(registrarButton, listarButton);
	}

	public MenuButton getRegistrarButton() {
		return registrarButton;
	}
	
	public MenuButton getListarButton() {
		return listarButton;
	}
}
