package app.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import app.UI.vista.menus.PanelMenuProductos;
import app.enums.Permission;

public class MenuButton extends JButton {

	Permission permissionNeeded;
	
	public MenuButton(String text, Permission permissionNeeded) {
		this(text, KeyEvent.VK_SPACE, permissionNeeded);
	}
	
	public MenuButton(String text, int c, Permission permissionNeeded){
		this.permissionNeeded = permissionNeeded;
		setText(text);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		setMnemonic(c);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		setForeground(Color.WHITE);
		setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		setHorizontalTextPosition(SwingConstants.CENTER);
	}

	public Permission getPermissionNeeded() {
		return permissionNeeded;
	}
	
}
