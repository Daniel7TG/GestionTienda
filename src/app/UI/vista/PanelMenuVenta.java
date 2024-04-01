package app.UI.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMenuVenta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton registrarButton;
	private JButton listarButton;

	/**
	 * Create the panel.
	 */
	public PanelMenuVenta() {
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
		
		listarButton = new JButton("Listar");
		listarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		listarButton.setMnemonic(KeyEvent.VK_L);
		listarButton.setFocusPainted(false);
		listarButton.setContentAreaFilled(false);
		listarButton.setBorderPainted(false);
		listarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		listarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		listarButton.setForeground(Color.WHITE);
		listarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		listarButton.setHorizontalTextPosition(SwingConstants.CENTER);
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
