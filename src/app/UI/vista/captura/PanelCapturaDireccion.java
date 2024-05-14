package app.UI.vista.captura;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import app.enums.Orientacion;
import app.modelos.Domicilio;
import app.util.Util;
import app.util.Util.FocusBox;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import static app.util.Util.FocusField;

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
		FocusField focusField = new FocusField();
		FocusBox focusBox = new FocusBox();
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
				"Formulario Domicilio", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, titleLabel));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 45, 0, 45, 0, 45, 0, 45, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lbNumero = new JLabel("Numero");
		GridBagConstraints gbc_lbNumero = new GridBagConstraints();
		gbc_lbNumero.insets = new Insets(10, 0, 0, 0);
		gbc_lbNumero.gridx = 0;
		gbc_lbNumero.gridy = 0;
		add(lbNumero, gbc_lbNumero);

		lbCalle = new JLabel("Calle");
		GridBagConstraints gbc_lbCalle = new GridBagConstraints();
		gbc_lbCalle.insets = new Insets(10, 0, 5, 0);
		gbc_lbCalle.gridx = 1;
		gbc_lbCalle.gridy = 0;
		add(lbCalle, gbc_lbCalle);

		numeroField = new JTextField();
		numeroField.addActionListener(focusField);
		numeroField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(numeroField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				}
			}
		});
		numeroField.addKeyListener(Util.lenghtLimit(3));
		GridBagConstraints gbc_numeroField = new GridBagConstraints();
		gbc_numeroField.insets = new Insets(10, 10, 10, 10);
		gbc_numeroField.fill = GridBagConstraints.BOTH;
		gbc_numeroField.gridx = 0;
		gbc_numeroField.gridy = 1;
		add(numeroField, gbc_numeroField);
		numeroField.setColumns(10);

		calleField = new JTextField();
		calleField.addActionListener(focusField);
		GridBagConstraints gbc_calleField = new GridBagConstraints();
		gbc_calleField.insets = new Insets(10, 10, 10, 10);
		gbc_calleField.fill = GridBagConstraints.BOTH;
		gbc_calleField.gridx = 1;
		gbc_calleField.gridy = 1;
		add(calleField, gbc_calleField);
		calleField.setColumns(10);

		lbOrientacion = new JLabel("Orientacion");
		GridBagConstraints gbc_lbOrientacion = new GridBagConstraints();
		gbc_lbOrientacion.insets = new Insets(10, 0, 0, 0);
		gbc_lbOrientacion.gridx = 0;
		gbc_lbOrientacion.gridy = 2;
		add(lbOrientacion, gbc_lbOrientacion);

		lbColonia = new JLabel("Colonia");
		GridBagConstraints gbc_lbColonia = new GridBagConstraints();
		gbc_lbColonia.insets = new Insets(10, 0, 0, 0);
		gbc_lbColonia.gridx = 1;
		gbc_lbColonia.gridy = 2;
		add(lbColonia, gbc_lbColonia);

		orientacionBox = new JComboBox<Orientacion>();
		orientacionBox.addItemListener(focusBox);
		orientacionBox.addItem(Orientacion.NORTE);
		orientacionBox.addItem(Orientacion.ORIENTE);
		orientacionBox.addItem(Orientacion.PONIENTE);
		orientacionBox.addItem(Orientacion.SUR);
		GridBagConstraints gbc_orientacionBox = new GridBagConstraints();
		gbc_orientacionBox.insets = new Insets(10, 10, 10, 10);
		gbc_orientacionBox.fill = GridBagConstraints.BOTH;
		gbc_orientacionBox.gridx = 0;
		gbc_orientacionBox.gridy = 3;
		add(orientacionBox, gbc_orientacionBox);

		coloniaField = new JTextField();
		coloniaField.addActionListener(focusField);
		GridBagConstraints gbc_coloniaField = new GridBagConstraints();
		gbc_coloniaField.insets = new Insets(10, 1, 10, 10);
		gbc_coloniaField.fill = GridBagConstraints.BOTH;
		gbc_coloniaField.gridx = 1;
		gbc_coloniaField.gridy = 3;
		add(coloniaField, gbc_coloniaField);
		coloniaField.setColumns(10);

		lbCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lbCiudad = new GridBagConstraints();
		gbc_lbCiudad.insets = new Insets(10, 0, 0, 5);
		gbc_lbCiudad.gridx = 0;
		gbc_lbCiudad.gridy = 4;
		add(lbCiudad, gbc_lbCiudad);

		lbEstado = new JLabel("Estado");
		GridBagConstraints gbc_lbEstado = new GridBagConstraints();
		gbc_lbEstado.insets = new Insets(10, 0, 0, 0);
		gbc_lbEstado.gridx = 1;
		gbc_lbEstado.gridy = 4;
		add(lbEstado, gbc_lbEstado);

		ciudadField = new JTextField();
		ciudadField.addActionListener(focusField);
		GridBagConstraints gbc_ciudadField = new GridBagConstraints();
		gbc_ciudadField.insets = new Insets(10, 10, 10, 10);
		gbc_ciudadField.fill = GridBagConstraints.BOTH;
		gbc_ciudadField.gridx = 0;
		gbc_ciudadField.gridy = 5;
		add(ciudadField, gbc_ciudadField);
		ciudadField.setColumns(10);

		estadoField = new JTextField();
		estadoField.addActionListener(focusField);
		GridBagConstraints gbc_estadoField = new GridBagConstraints();
		gbc_estadoField.insets = new Insets(10, 10, 10, 10);
		gbc_estadoField.fill = GridBagConstraints.BOTH;
		gbc_estadoField.gridx = 1;
		gbc_estadoField.gridy = 5;
		add(estadoField, gbc_estadoField);
		estadoField.setColumns(10);

		lbCodigoPostal = new JLabel("Codigo Postal");
		GridBagConstraints gbc_lbCodigoPostal = new GridBagConstraints();
		gbc_lbCodigoPostal.insets = new Insets(10, 0, 5, 5);
		gbc_lbCodigoPostal.gridx = 0;
		gbc_lbCodigoPostal.gridy = 6;
		add(lbCodigoPostal, gbc_lbCodigoPostal);


		codigoPostalField = new JTextField();
		codigoPostalField.addActionListener(focusField);
		codigoPostalField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(numeroField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				}
			}
		});
		codigoPostalField.addKeyListener(Util.lenghtLimit(5));
		GridBagConstraints gbc_codigoPostalField = new GridBagConstraints();
		gbc_codigoPostalField.insets = new Insets(10, 10, 10, 10);
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

	public boolean isValidDirection() {
		if(numeroField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La direccion debe tener un numero");
		}else if(calleField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La direccion debe tener una calle");
		}else if(coloniaField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La direccion debe tener una colonia");
		}else if(ciudadField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La direccion debe tener una ciudad");
		}else if(estadoField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La direccion debe tener un estado");
		}else if(codigoPostalField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "La direccion debe tener codigo postal");
		} else {
			return true;
		}
		return false;
	}

	public Domicilio getDireccion() {
		return new Domicilio(
				Integer.parseInt(numeroField.getText()),
				calleField.getText(),
				(Orientacion)orientacionBox.getSelectedItem(),
				coloniaField.getText(),
				ciudadField.getText(),
				estadoField.getText(),
				codigoPostalField.getText()
				);
	}

	public void setPreItem(JTextField c) {
		c.addActionListener(e->{
			SwingUtilities.invokeLater(()->{
				numeroField.requestFocus();				
			});
		});
	}

	public Component getLastItem() {
		return codigoPostalField;
	}


	public void vaciarComponentes() {
		numeroField.setText(""); 
		calleField.setText("");
		orientacionBox.setSelectedItem(0);
		coloniaField.setText("");
		ciudadField.setText("");
		estadoField.setText("");
		codigoPostalField.setText("");
	}
	
	
	public void setUnneditable() {
		numeroField.setEditable(false); 
		calleField.setEditable(false);
		orientacionBox.setEnabled(false);
		coloniaField.setEditable(false);
		ciudadField.setEditable(false);
		estadoField.setEditable(false);
		codigoPostalField.setEditable(false);		
	}
	
	


	public void autoCompleteFields(Domicilio domicilio) {
		if (domicilio != null) {
			numeroField.setText(String.valueOf(domicilio.getNumero()));
			calleField.setText(domicilio.getCalle());
			orientacionBox.setSelectedItem(domicilio.getOrientacion());
			coloniaField.setText(domicilio.getColonia());
			ciudadField.setText(domicilio.getCiudad());
			estadoField.setText(domicilio.getEstado());
			codigoPostalField.setText(domicilio.getCodigoPostal());
		}

	}

	public void setEditable(boolean editable) {
	    numeroField.setEditable(editable); 
	    calleField.setEditable(editable);
	    orientacionBox.setEnabled(editable);
	    coloniaField.setEditable(editable);
	    ciudadField.setEditable(editable);
	    estadoField.setEditable(editable);
	    codigoPostalField.setEditable(editable);
	}

}
