package app.UI.vista.general;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class PanelOpciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton guardarButton;
	private JButton confirmarButton;
	private JButton cancelarButton;
	private JButton jumpable;
	public static final int BOTH = 2, CANCEL = 1, CONFIRM = 0;
	public static final int SAVE = 3;
	public static final int ALL = 4;
	
	/**
	 * Create the panel.
	 */
	public PanelOpciones(Component lastElement, int optNum) {
		this(lastElement, optNum, "Guardar", "Cancelar", "Confirmar");				
	}
	public PanelOpciones(Component lastElement, int optNum, String firstText) {
		this(lastElement, optNum, firstText, "Cancelar", "Confirmar");		
	}
	public PanelOpciones(Component lastElement, int optNum, String firstText, String secondText) {
		this(lastElement, optNum, firstText, secondText, "Confirmar");
	}
	public PanelOpciones(Component lastElement, int optNum, String firstText, String secondText, String thirdText) {	
		setBackground(Color.decode("#d0e2ed"));
		
		guardarButton = new JButton(firstText);
		guardarButton.setFocusPainted(false);
		guardarButton.setRolloverSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		guardarButton.setSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		guardarButton.setBorderPainted(false);
		guardarButton.setContentAreaFilled(false);
		guardarButton.setForeground(Color.WHITE);
		guardarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		guardarButton.setPressedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		guardarButton.setDisabledIcon(new ImageIcon(PanelOpciones.class.getResource("/img/disabledConfirm.png")));
		guardarButton.setRolloverIcon(new ImageIcon(PanelOpciones.class.getResource("/img/hoverConfirm.png")));
		guardarButton.setIcon(new ImageIcon(PanelOpciones.class.getResource("/img/defaultConfirm.png")));
		guardarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		guardarButton.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				guardarButton.setSelected(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				guardarButton.setSelected(false);							
			}						
		});

		
		confirmarButton = new JButton(thirdText);
		confirmarButton.setFocusPainted(false);
		confirmarButton.setRolloverSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		confirmarButton.setSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		confirmarButton.setBorderPainted(false);
		confirmarButton.setContentAreaFilled(false);
		confirmarButton.setForeground(Color.WHITE);
		confirmarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		confirmarButton.setPressedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		confirmarButton.setDisabledIcon(new ImageIcon(PanelOpciones.class.getResource("/img/disabledConfirm.png")));
		confirmarButton.setRolloverIcon(new ImageIcon(PanelOpciones.class.getResource("/img/hoverConfirm.png")));
		confirmarButton.setIcon(new ImageIcon(PanelOpciones.class.getResource("/img/defaultConfirm.png")));
		confirmarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		confirmarButton.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				confirmarButton.setSelected(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				confirmarButton.setSelected(false);							
			}						
		});
		
		
		cancelarButton = new JButton(secondText);
		cancelarButton.setFocusPainted(false);
		cancelarButton.setSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedCancel.png")));
		cancelarButton.setContentAreaFilled(false);
		cancelarButton.setBorderPainted(false);
		cancelarButton.setForeground(Color.WHITE);
		cancelarButton.setPressedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedCancel.png")));
		cancelarButton.setDisabledIcon(new ImageIcon(PanelOpciones.class.getResource("/img/disabledCancel.png")));
		cancelarButton.setIcon(new ImageIcon(PanelOpciones.class.getResource("/img/defaultCancel.png")));
		cancelarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		cancelarButton.setRolloverIcon(new ImageIcon(PanelOpciones.class.getResource("/img/hoverCancel.png")));
		cancelarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelarButton.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				cancelarButton.setSelected(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				cancelarButton.setSelected(false);							
			}						
		});
		

		this.jumpable = guardarButton;
		if(optNum == CANCEL) {
			add(cancelarButton);
			this.jumpable = cancelarButton;
		}
		else if(optNum == SAVE) {			
			add(guardarButton);
		}
		else if(optNum == CONFIRM) {			
			add(confirmarButton);
		}
		else if(optNum == BOTH) {
			add(guardarButton);
			add(cancelarButton); 
		}
		else {
			add(guardarButton);
			add(cancelarButton); 
			add(confirmarButton);
		}
		
		if(lastElement != null)
			lastElement.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyChar() == '\n'){
						e.consume();
						jumpable.requestFocus();
					}
				}			
			});
		
	

	}

	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getGuardarButton() {
		return guardarButton;
	}
	public JButton getConfirmButton() {
		return confirmarButton;
	}

	public JButton getCancelarButton() {
		return cancelarButton;
	}
	

}
	