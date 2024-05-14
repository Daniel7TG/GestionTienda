package app.UI.vista.menus;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Cursor;

public class PanelMenuProductos extends PanelMenu {

	private static final long serialVersionUID = 1L;

	private MenuButton registrarButton;
	private MenuButton consultarButton;
	private MenuButton eliminarButton;
	private MenuButton modificarButton;
	private MenuButton listarButton;


	/**
	 * Create the panel.
	 */
	public PanelMenuProductos(Usuario usuario) {
		super("Productos", usuario);

		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_PRODUCTOS);
		consultarButton = new MenuButton("Consultar", KeyEvent.VK_C, Permission.READ_PRODUCTOS);
		eliminarButton = new MenuButton("Eliminar", KeyEvent.VK_E, Permission.DELETE_PRODUCTOS);
		modificarButton = new MenuButton("Modificar", KeyEvent.VK_M, Permission.MODIFY_PRODUCTOS);
		listarButton = new MenuButton("Listar",KeyEvent.VK_L, Permission.READ_PRODUCTOS);

		super.addButtons(registrarButton, consultarButton, eliminarButton, modificarButton, listarButton);
	}

	public MenuButton getRegistrarButton() {
		return registrarButton;
	}

	public MenuButton getConsultarButton() {
		return consultarButton;
	}

	public MenuButton getEliminarButton() {
		return eliminarButton;
	}

	public MenuButton getModificarButton() {
		return modificarButton;
	}

	public MenuButton getListarButton() {
		return listarButton;
	}

}
