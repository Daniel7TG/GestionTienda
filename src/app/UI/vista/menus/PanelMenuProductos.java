package app.UI.vista.menus;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
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

public class PanelMenuProductos extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton registrarButton;
	private JButton consultarButton;
	private JButton eliminarButton;
	private JButton modificarButton;
	private JButton listarButton;


	/**
	 * Create the panel.
	 */
	public PanelMenuProductos(Usuario usuario) {
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));

		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_PRODUCTOS);
		if(!usuario.hasAccessTo(Permission.ADD_PRODUCTOS)) registrarButton.setEnabled(false);

		consultarButton = new MenuButton("Consultar", KeyEvent.VK_C, Permission.READ_PRODUCTOS);
		if(!usuario.hasAccessTo(Permission.READ_PRODUCTOS)) consultarButton.setEnabled(false);

		eliminarButton = new MenuButton("Eliminar", KeyEvent.VK_E, Permission.DELETE_PRODUCTOS);
		if(!usuario.hasAccessTo(Permission.DELETE_PRODUCTOS)) eliminarButton.setEnabled(false);

		modificarButton = new MenuButton("Modificar", KeyEvent.VK_M, Permission.MODIFY_PRODUCTOS);
		if(!usuario.hasAccessTo(Permission.MODIFY_PRODUCTOS)) modificarButton.setEnabled(false);

		listarButton = new MenuButton("Listar",KeyEvent.VK_L, Permission.READ_PRODUCTOS);
		if(!usuario.hasAccessTo(Permission.READ_PRODUCTOS)) listarButton.setEnabled(false);


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
