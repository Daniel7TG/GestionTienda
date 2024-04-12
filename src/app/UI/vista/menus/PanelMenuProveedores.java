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

public class PanelMenuProveedores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton registrarButton;

	public PanelMenuProveedores() {
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));

		registrarButton = new JButton("Registrar");
		registrarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registrarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		registrarButton.setMnemonic(KeyEvent.VK_R);
		registrarButton.setFocusPainted(false);
		registrarButton.setContentAreaFilled(false);
		registrarButton.setBorderPainted(false);
		registrarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		registrarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		registrarButton.setForeground(Color.WHITE);
		registrarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		registrarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(registrarButton);
	}
	public void enableButtons(boolean status) {
		registrarButton.setEnabled(status);
	}
	public JButton getRegistrarButton() {
		return registrarButton;
	}

}
