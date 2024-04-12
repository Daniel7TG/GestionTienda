package app.UI.vista.general;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.Painter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import app.enums.Orientacion;
import app.modelos.Domicilio;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class PanelCapturaDireccion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField numeroField;
	private JTextField calleField;
	private JTextField coloniaField;
	private JTextField ciudadField;
	private JTextField estadoField;
	private JTextField codigoPostalField;
	private JLabel lbNumero;
	private JLabel lbCalle;
	private JLabel lbOrientacion;
	private JLabel lbColonia;
	private JComboBox<Orientacion> orientacionBox;
	private JLabel lbCiudad;
	private JLabel lbEstado;
	private JLabel lbCodigoPostal;
	private Font fontLabel;
	private Font fontFunc;
	private Font titleLabel;

	public PanelCapturaDireccion() {
		titleLabel = new Font("Montserrat", Font.BOLD, 16);
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(),
				"Formulario Domicilio", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, titleLabel));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 45, 0, 45, 0, 45, 0, 45, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbNumero = new JLabel("Numero");
		GridBagConstraints gbc_lbNumero = new GridBagConstraints();
		gbc_lbNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lbNumero.gridx = 0;
		gbc_lbNumero.gridy = 0;
		add(lbNumero, gbc_lbNumero);
		
		lbCalle = new JLabel("Calle");
		GridBagConstraints gbc_lbCalle = new GridBagConstraints();
		gbc_lbCalle.insets = new Insets(0, 0, 5, 0);
		gbc_lbCalle.gridx = 1;
		gbc_lbCalle.gridy = 0;
		add(lbCalle, gbc_lbCalle);
		
		numeroField = new JTextField();
		numeroField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(numeroField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_numeroField = new GridBagConstraints();
		gbc_numeroField.insets = new Insets(0, 0, 5, 5);
		gbc_numeroField.fill = GridBagConstraints.BOTH;
		gbc_numeroField.gridx = 0;
		gbc_numeroField.gridy = 1;
		add(numeroField, gbc_numeroField);
		numeroField.setColumns(10);
		
		calleField = new JTextField();
		GridBagConstraints gbc_calleField = new GridBagConstraints();
		gbc_calleField.insets = new Insets(0, 0, 5, 0);
		gbc_calleField.fill = GridBagConstraints.BOTH;
		gbc_calleField.gridx = 1;
		gbc_calleField.gridy = 1;
		add(calleField, gbc_calleField);
		calleField.setColumns(10);
		
		lbOrientacion = new JLabel("Orientacion");
		GridBagConstraints gbc_lbOrientacion = new GridBagConstraints();
		gbc_lbOrientacion.insets = new Insets(0, 0, 5, 5);
		gbc_lbOrientacion.gridx = 0;
		gbc_lbOrientacion.gridy = 2;
		add(lbOrientacion, gbc_lbOrientacion);
		
		lbColonia = new JLabel("Colonia");
		GridBagConstraints gbc_lbColonia = new GridBagConstraints();
		gbc_lbColonia.insets = new Insets(0, 0, 5, 0);
		gbc_lbColonia.gridx = 1;
		gbc_lbColonia.gridy = 2;
		add(lbColonia, gbc_lbColonia);
		
		orientacionBox = new JComboBox<Orientacion>();
		orientacionBox.addItem(Orientacion.NORTE);
		orientacionBox.addItem(Orientacion.ORIENTE);
		orientacionBox.addItem(Orientacion.PONIENTE);
		orientacionBox.addItem(Orientacion.SUR);
		GridBagConstraints gbc_orientacionBox = new GridBagConstraints();
		gbc_orientacionBox.insets = new Insets(0, 0, 5, 5);
		gbc_orientacionBox.fill = GridBagConstraints.BOTH;
		gbc_orientacionBox.gridx = 0;
		gbc_orientacionBox.gridy = 3;
		add(orientacionBox, gbc_orientacionBox);
		
		coloniaField = new JTextField();
		GridBagConstraints gbc_coloniaField = new GridBagConstraints();
		gbc_coloniaField.insets = new Insets(0, 0, 5, 0);
		gbc_coloniaField.fill = GridBagConstraints.BOTH;
		gbc_coloniaField.gridx = 1;
		gbc_coloniaField.gridy = 3;
		add(coloniaField, gbc_coloniaField);
		coloniaField.setColumns(10);
		
		lbCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lbCiudad = new GridBagConstraints();
		gbc_lbCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lbCiudad.gridx = 0;
		gbc_lbCiudad.gridy = 4;
		add(lbCiudad, gbc_lbCiudad);
		
		lbEstado = new JLabel("Estado");
		GridBagConstraints gbc_lbEstado = new GridBagConstraints();
		gbc_lbEstado.insets = new Insets(0, 0, 5, 0);
		gbc_lbEstado.gridx = 1;
		gbc_lbEstado.gridy = 4;
		add(lbEstado, gbc_lbEstado);
		
		ciudadField = new JTextField();
		GridBagConstraints gbc_ciudadField = new GridBagConstraints();
		gbc_ciudadField.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadField.fill = GridBagConstraints.BOTH;
		gbc_ciudadField.gridx = 0;
		gbc_ciudadField.gridy = 5;
		add(ciudadField, gbc_ciudadField);
		ciudadField.setColumns(10);
		
		estadoField = new JTextField();
		GridBagConstraints gbc_estadoField = new GridBagConstraints();
		gbc_estadoField.insets = new Insets(0, 0, 5, 0);
		gbc_estadoField.fill = GridBagConstraints.BOTH;
		gbc_estadoField.gridx = 1;
		gbc_estadoField.gridy = 5;
		add(estadoField, gbc_estadoField);
		estadoField.setColumns(10);
		
		lbCodigoPostal = new JLabel("Codigo Postal");
		GridBagConstraints gbc_lbCodigoPostal = new GridBagConstraints();
		gbc_lbCodigoPostal.insets = new Insets(0, 0, 5, 5);
		gbc_lbCodigoPostal.gridx = 0;
		gbc_lbCodigoPostal.gridy = 6;
		add(lbCodigoPostal, gbc_lbCodigoPostal);
		
		codigoPostalField = new JTextField();
		codigoPostalField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(numeroField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_codigoPostalField = new GridBagConstraints();
		gbc_codigoPostalField.insets = new Insets(0, 0, 0, 5);
		gbc_codigoPostalField.fill = GridBagConstraints.BOTH;
		gbc_codigoPostalField.gridx = 0;
		gbc_codigoPostalField.gridy = 7;
		add(codigoPostalField, gbc_codigoPostalField);
		codigoPostalField.setColumns(10);

		style( new Component[] {		
		 numeroField,
		 calleField,
		 coloniaField,
		 ciudadField,
		 estadoField,
		 codigoPostalField,
		 lbNumero,
		 lbCalle,
		 lbOrientacion,
		 lbColonia,
		 orientacionBox,
		 lbCiudad,
		 lbEstado,
		 lbCodigoPostal
		});
		
	}
	
	
	public void style(Component[] components) {				
		for(Component c : components) {
			if(c instanceof JLabel) {
				c.setFont(fontLabel);
			}
			else c.setFont(fontFunc);	
		}	
	}
	
	
	public Domicilio getDireccion() {
		return new Domicilio(
				Integer.valueOf(numeroField.getText()), 
				calleField.getText(),
				(Orientacion)orientacionBox.getSelectedItem(),
				coloniaField.getText(),
				ciudadField.getText(),
				estadoField.getText(),
				codigoPostalField.getText()
				);
	}

}
