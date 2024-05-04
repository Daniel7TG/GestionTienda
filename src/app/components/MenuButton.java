package app.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import app.UI.vista.menus.PanelMenuProductos;

public class MenuButton extends JButton {

	public MenuButton(String text) {
		this(text, KeyEvent.VK_SPACE);
	}
	
	public MenuButton(String text, int c){
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
	
}
