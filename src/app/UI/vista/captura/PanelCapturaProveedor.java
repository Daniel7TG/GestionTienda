package app.UI.vista.captura;

import javax.swing.JPanel;

import app.UI.vista.general.PanelCapturaDireccion;
import app.abstractClasses.Detalles;
import app.modelos.Proveedor;
import app.modelos.containers.Proveedores;
import app.util.Util.FocusField;
import app.util.TableModel;
import app.util.Util;
import app.util.Util.FocusBox;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelCapturaProveedor extends JPanel {

	private static final long serialVersionUID = 1L;
	Proveedores proveedores;
	private JTextField rfcField;
	private JTextField razonField;
	private JTextField telefonoField;
	private JLabel lbRfc;
	private JLabel lbRazonSocial;
	private JLabel lbTelefono;
	private Font fontLabel;
	private Font fontFunc;
	private PanelCapturaDireccion panelDireccion;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField fieldNombre;
	private JTextField fieldApellido;
	private JScrollPane tablePanel;
	private JTable productsTable;
	private TableModel model;
	private Object[][] data = new Object[0][0];
	private String[] columnNames = {"RFC",
			"Nombre",
			"Apellido", 
			"Razón Social",
			"Teléfono",
			"Dirección"
			};
	
	public PanelCapturaProveedor(Proveedores proveedores) {
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);
		this.proveedores = proveedores;
		FocusField focusField = new FocusField();
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{35, 65, 35, 65, 35, 65, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 6.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbRfc = new JLabel("RFC");
		GridBagConstraints gbc_lbRfc = new GridBagConstraints();
		gbc_lbRfc.fill = GridBagConstraints.VERTICAL;
		gbc_lbRfc.gridwidth = 2;
		gbc_lbRfc.insets = new Insets(0, 0, 5, 5);
		gbc_lbRfc.gridx = 0;
		gbc_lbRfc.gridy = 0;
		add(lbRfc, gbc_lbRfc);
		
		rfcField = new JTextField();
		rfcField.setHorizontalAlignment(SwingConstants.CENTER);
		rfcField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char[] texto = rfcField.getText().toCharArray();
				if(texto.length > 12) {
					if(proveedores.exists(rfcField.getText())) {
						JOptionPane.showMessageDialog(null, "Ya existe este proveedor");
						rfcField.setText("");
					} else {
						rfcField.transferFocus();
					}
					e.consume();
				}				
			}
		});
		panelDireccion = new PanelCapturaDireccion();
		GridBagConstraints gbc_direccion = new GridBagConstraints();
		gbc_direccion.gridheight = 7;
		gbc_direccion.fill = GridBagConstraints.BOTH;
		gbc_direccion.insets = new Insets(10, 0, 10, 0);
		gbc_direccion.gridx = 2;
		gbc_direccion.gridy = 0;
		gbc_direccion.gridwidth = 2;
		add(panelDireccion, gbc_direccion);
		GridBagConstraints gbc_rfcField = new GridBagConstraints();
		gbc_rfcField.gridwidth = 2;
		gbc_rfcField.insets = new Insets(10, 10, 10, 10);
		gbc_rfcField.fill = GridBagConstraints.BOTH;
		gbc_rfcField.gridx = 0;
		gbc_rfcField.gridy = 1;
		add(rfcField, gbc_rfcField);
		rfcField.setColumns(10);
		
		lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.fill = GridBagConstraints.VERTICAL;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 2;
		add(lblApellido, gbc_lblApellido);
		
		fieldNombre = new JTextField();
		GridBagConstraints gbc_fieldNombre = new GridBagConstraints();
		gbc_fieldNombre.insets = new Insets(10, 10, 10, 10);
		gbc_fieldNombre.fill = GridBagConstraints.BOTH;
		gbc_fieldNombre.gridx = 0;
		gbc_fieldNombre.gridy = 3;
		add(fieldNombre, gbc_fieldNombre);
		fieldNombre.setColumns(10);
		
		fieldApellido = new JTextField();
		GridBagConstraints gbc_fieldApellido = new GridBagConstraints();
		gbc_fieldApellido.insets = new Insets(10, 10, 10, 10);
		gbc_fieldApellido.fill = GridBagConstraints.BOTH;
		gbc_fieldApellido.gridx = 1;
		gbc_fieldApellido.gridy = 3;
		add(fieldApellido, gbc_fieldApellido);
		fieldApellido.setColumns(10);
		
		lbRazonSocial = new JLabel("Razon Social");
		GridBagConstraints gbc_lbRazonSocial = new GridBagConstraints();
		gbc_lbRazonSocial.fill = GridBagConstraints.VERTICAL;
		gbc_lbRazonSocial.insets = new Insets(0, 0, 5, 5);
		gbc_lbRazonSocial.gridx = 0;
		gbc_lbRazonSocial.gridy = 4;
		add(lbRazonSocial, gbc_lbRazonSocial);
		
		razonField = new JTextField();
		razonField.addActionListener(focusField);
		
		lbTelefono = new JLabel("Teléfono");
		GridBagConstraints gbc_lbTelefono = new GridBagConstraints();
		gbc_lbTelefono.fill = GridBagConstraints.VERTICAL;
		gbc_lbTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lbTelefono.gridx = 1;
		gbc_lbTelefono.gridy = 4;
		add(lbTelefono, gbc_lbTelefono);
		GridBagConstraints gbc_razonField = new GridBagConstraints();
		gbc_razonField.insets = new Insets(10, 10, 10, 10);
		gbc_razonField.fill = GridBagConstraints.BOTH;
		gbc_razonField.gridx = 0;
		gbc_razonField.gridy = 5;
		add(razonField, gbc_razonField);
		razonField.setColumns(10);
		
		telefonoField = new JTextField();
		telefonoField.addActionListener(focusField);
		telefonoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(telefonoField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_telefonoField = new GridBagConstraints();
		gbc_telefonoField.insets = new Insets(10, 10, 10, 10);
		gbc_telefonoField.fill = GridBagConstraints.BOTH;
		gbc_telefonoField.gridx = 1;
		gbc_telefonoField.gridy = 5;
		add(telefonoField, gbc_telefonoField);
		telefonoField.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		add(lblNombre, gbc_lblNombre);
		
		tablePanel = new JScrollPane();
		productsTable = new JTable();
		data = proveedores.getData();
		model = new TableModel(productsTable, data, columnNames);
		model.configurarTabla(1, 1, 1, 1, 1, 3);
		productsTable.setModel(model);
		tablePanel.setViewportView(productsTable);
		
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.gridwidth = 4;
		gbc_tablePanel.insets = new Insets(0, 0, 0, 5);
		gbc_tablePanel.fill = GridBagConstraints.BOTH;
		gbc_tablePanel.gridx = 0;
		gbc_tablePanel.gridy = 7;
		add(tablePanel, gbc_tablePanel);
		
		style(new Component[] {
		  rfcField,
		  razonField,
		  telefonoField,
		  lbRfc,
		  lbRazonSocial,
		  lbTelefono,
		  lblNombre,
		  lblApellido,
		  fieldNombre,
		  fieldApellido
		});
		
		
		SwingUtilities.invokeLater(()->{			
			rfcField.requestFocusInWindow();
			panelDireccion.setPreItem(telefonoField);
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
	
	public void guardarProveedor() {
		if(rfcField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de rfc no puede estar vacío");			
		} else if(rfcField.getText().length() != 13) {
			JOptionPane.showMessageDialog(null, "El campo de rfc debe tener 13 dígitos");			
		} else if(razonField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de razón no puede estar vacío");
		} else if(fieldNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de nombre no puede estar vacío");
		} else if(fieldApellido.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de apellido no puede estar vacío");
		} else if(rfcField.getText().isBlank() & rfcField.getText().length() == 13) {
			JOptionPane.showMessageDialog(null, "El campo de rfc no puede estar vacio");		
		} else if(proveedores.exists(rfcField.getText())) {
			JOptionPane.showMessageDialog(null, "Ya existe este proveedor");
		} else if(telefonoField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de teléfono no puede estar vacío");				
		} else if(!panelDireccion.isValidDirection()) {} 
		else {			
			Proveedor proveedor = new Proveedor(razonField.getText(), fieldNombre.getText(), fieldApellido.getText(), rfcField.getText(), telefonoField.getText(), panelDireccion.getDireccion());
			proveedores.add(proveedor);
			JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente");
			vaciarComponentes();
			updateTable();
		}
	}
	
	private void updateTable() {
		model.update(proveedores.getData());
	}
	
	
	public Component getLastItem() {
		return panelDireccion.getLastItem();
	}
	
	
	public void vaciarComponentes() {
		razonField.setText("");
		rfcField.setText("");
		telefonoField.setText("");
		fieldNombre.setText("");
		fieldApellido.setText("");
		panelDireccion.vaciarComponentes();
	}

}
