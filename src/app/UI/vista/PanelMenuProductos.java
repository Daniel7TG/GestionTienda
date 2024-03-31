package app.UI.vista;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
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
	private JButton ordenarButton;

	/**
	 * Create the panel.
	 */
	public PanelMenuProductos() {
		setMaximumSize(new Dimension(200, 32767));
		setBackground(Color.decode("#b0cfe0"));
		setLayout(new GridLayout(6, 1, 0, 20));
		
		registrarButton = new JButton("Registrar");
		registrarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registrarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		registrarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		registrarButton.setMnemonic(KeyEvent.VK_R);
		registrarButton.setFocusPainted(false);
		registrarButton.setContentAreaFilled(false);
		registrarButton.setBorderPainted(false);
		registrarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		registrarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		registrarButton.setForeground(Color.WHITE);

		registrarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(registrarButton);
		
		consultarButton = new JButton("Consultar");
		consultarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		consultarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		consultarButton.setMnemonic(KeyEvent.VK_C);
		consultarButton.setFocusPainted(false);
		consultarButton.setContentAreaFilled(false);
		consultarButton.setBorderPainted(false);
		consultarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		consultarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		consultarButton.setForeground(Color.WHITE);
		consultarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		consultarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(consultarButton);
		
		eliminarButton = new JButton("Eliminar");
		eliminarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		eliminarButton.setMnemonic(KeyEvent.VK_E);
		eliminarButton.setFocusPainted(false);
		eliminarButton.setContentAreaFilled(false);
		eliminarButton.setBorderPainted(false);
		eliminarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		eliminarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		eliminarButton.setForeground(Color.WHITE);
		eliminarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		eliminarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(eliminarButton);
		
		modificarButton = new JButton("Modificar");
		modificarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		modificarButton.setMnemonic(KeyEvent.VK_M);
		modificarButton.setFocusPainted(false);
		modificarButton.setContentAreaFilled(false);
		modificarButton.setBorderPainted(false);
		modificarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		modificarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		modificarButton.setForeground(Color.WHITE);
		modificarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		modificarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(modificarButton);
		
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
		
		ordenarButton = new JButton("Ordenar");
		ordenarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ordenarButton.setDisabledIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/disabled.png")));
		ordenarButton.setMnemonic(KeyEvent.VK_O);
		ordenarButton.setFocusPainted(false);
		ordenarButton.setContentAreaFilled(false);
		ordenarButton.setBorderPainted(false);
		ordenarButton.setPressedIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/selected.png")));
		ordenarButton.setRolloverIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/hover.png")));
		ordenarButton.setForeground(Color.WHITE);
		ordenarButton.setIcon(new ImageIcon(PanelMenuProductos.class.getResource("/img/default.png")));
		ordenarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(ordenarButton);
				
	}

	public ImageIcon scale() {
		return null;
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

	public JButton getOrdenarButton() {
		return ordenarButton;
	}
	
}
