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

public class PanelMenuProveedores extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton registrarButton;
	private JButton consultarButton;
	private JButton eliminarButton;
	private JButton modificarButton;
	private JButton listarButton;

	public PanelMenuProveedores(Usuario usuario) {
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));

		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_PROVEEDORES);
		if(!usuario.hasAccessTo(Permission.ADD_PROVEEDORES)) registrarButton.setEnabled(false);

		consultarButton = new MenuButton("Consultar", KeyEvent.VK_C, Permission.READ_PROVEEDORES);
		if(!usuario.hasAccessTo(Permission.READ_PROVEEDORES)) consultarButton.setEnabled(false);

		eliminarButton = new MenuButton("Eliminar", KeyEvent.VK_E, Permission.DELETE_PROVEEDORES);
		if(!usuario.hasAccessTo(Permission.DELETE_PROVEEDORES)) eliminarButton.setEnabled(false);

		modificarButton = new MenuButton("Modificar", KeyEvent.VK_M, Permission.MODIFY_PROVEEDORES);
		if(!usuario.hasAccessTo(Permission.MODIFY_PROVEEDORES)) modificarButton.setEnabled(false);

		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_PROVEEDORES);
		if(!usuario.hasAccessTo(Permission.READ_PROVEEDORES)) listarButton.setEnabled(false);


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
