package app.UI.vista.menus;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PanelMenuClientes extends PanelMenu {

    private MenuButton registrarButton;
    private MenuButton consultarButton;
    private MenuButton eliminarButton;
    private MenuButton modificarButton;
    private MenuButton listarButton;

    public PanelMenuClientes(Usuario usuario) {
        super("Cliente", usuario);

        registrarButton = new MenuButton("Registrar", KeyEvent.VK_R, Permission.ADD_PROVEEDORES);
        consultarButton = new MenuButton("Consultar", KeyEvent.VK_C, Permission.READ_PROVEEDORES);
        eliminarButton = new MenuButton("Eliminar", KeyEvent.VK_E, Permission.DELETE_PROVEEDORES);
        modificarButton = new MenuButton("Modificar", KeyEvent.VK_M, Permission.MODIFY_PROVEEDORES);
        listarButton = new MenuButton("Listar", KeyEvent.VK_L, Permission.READ_PROVEEDORES);

        super.addButtons(registrarButton, consultarButton, eliminarButton, modificarButton, listarButton);
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
