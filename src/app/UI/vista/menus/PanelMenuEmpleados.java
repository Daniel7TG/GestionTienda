package app.UI.vista.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;

public class PanelMenuEmpleados extends PanelMenu {

	private static final long serialVersionUID = 1L;
	
	private MenuButton registrarButton;
	private MenuButton consultarButton;
	private MenuButton eliminarButton;
	private MenuButton modificarButton;
	private MenuButton listarButton;
	
	public PanelMenuEmpleados(Usuario usuario){
		super("Usuarios", usuario);
		
		registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_USUARIOS);
		consultarButton = new MenuButton("Consultar", KeyEvent.VK_L, Permission.READ_USUARIOS);
		eliminarButton = new MenuButton("Eliminar", KeyEvent.VK_L, Permission.DELETE_USUARIOS);
		modificarButton = new MenuButton("Modificar", KeyEvent.VK_L, Permission.MODIFY_USUARIOS);
		listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_USUARIOS);
			
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
