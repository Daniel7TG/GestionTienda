package app.UI.vista.general;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import app.components.GroupRadioButtons;
import app.components.TextFieldSuggestion;
import app.enums.Permission;
import app.interfaces.Service;
import app.modelos.Domicilio;
import app.modelos.Proveedor;
import app.modelos.Usuario;
import app.util.TableModel;
import app.util.Util;
import app.util.Util.FocusField;

import static app.enums.Permission.*;

public abstract class PanelUsuarios extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected Service<Usuario> usuarios;
	protected JTextField usernameField;
	protected JPasswordField passwordField;
	protected JPasswordField confirmPasswordField;
	protected JTextField telefonoField;
	protected JLabel lbUsername;
	protected JLabel lbPassword;
	protected JLabel lbConfirmPassword;
	protected JLabel lbTelefono;
	protected Font fontLabel;
	protected Font fontFunc;
	protected PanelCapturaDireccion panelDireccion;
	protected JLabel lblNombre;
	protected JLabel lblApellido;
	protected JTextField fieldNombre;
	protected JTextField fieldApellido;
	protected GroupRadioButtons productosPermission;
	protected GroupRadioButtons proveedoresPermission;
	protected GroupRadioButtons usuariosPermission;
	protected JRadioButton writeCompraBtn;
	protected JRadioButton readCompraBtn;
	protected JRadioButton writeVentaBtn;
	protected JRadioButton readVentaBtn;
	protected GridBagConstraints gbc_rfcField;
	
	protected PanelUsuarios(Service<Usuario> usuarios, boolean autoComplete) {
		this.usuarios = usuarios;
		
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);

		FocusField focusField = new FocusField();
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{35, 65, 35, 65, 35, 65, 30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbUsername = new JLabel("Nombre de Usuario");
		GridBagConstraints gbc_lbRfc = new GridBagConstraints();
		gbc_lbRfc.fill = GridBagConstraints.VERTICAL;
		gbc_lbRfc.insets = new Insets(0, 0, 5, 5);
		gbc_lbRfc.gridx = 0;
		gbc_lbRfc.gridy = 0;
		add(lbUsername, gbc_lbRfc);
		
		if(autoComplete) usernameField = new TextFieldSuggestion(Util.getUsernameFilter(usuarios));
		else usernameField = new JTextField();
		gbc_rfcField = new GridBagConstraints();
		gbc_rfcField.insets = new Insets(10, 10, 10, 10);
		gbc_rfcField.fill = GridBagConstraints.BOTH;
		gbc_rfcField.gridx = 0;
		gbc_rfcField.gridy = 1;
		add(usernameField, gbc_rfcField);
		
		panelDireccion = new PanelCapturaDireccion();
		GridBagConstraints gbc_direccion = new GridBagConstraints();
		gbc_direccion.gridheight = 7;
		gbc_direccion.fill = GridBagConstraints.BOTH;
		gbc_direccion.insets = new Insets(10, 0, 10, 0);
		gbc_direccion.gridx = 2;
		gbc_direccion.gridy = 0;
		gbc_direccion.gridwidth = 2;
		add(panelDireccion, gbc_direccion);
		
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
		
		lbPassword = new JLabel("Contraseña");
		GridBagConstraints gbc_lbRazonSocial = new GridBagConstraints();
		gbc_lbRazonSocial.fill = GridBagConstraints.VERTICAL;
		gbc_lbRazonSocial.insets = new Insets(0, 0, 5, 5);
		gbc_lbRazonSocial.gridx = 0;
		gbc_lbRazonSocial.gridy = 4;
		add(lbPassword, gbc_lbRazonSocial);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(focusField);
		GridBagConstraints gbc_razonField = new GridBagConstraints();
		gbc_razonField.insets = new Insets(10, 10, 10, 10);
		gbc_razonField.fill = GridBagConstraints.BOTH;
		gbc_razonField.gridx = 0;
		gbc_razonField.gridy = 5;
		add(passwordField, gbc_razonField);
		passwordField.setColumns(10);

		lbConfirmPassword = new JLabel("Confirmar Contraseña");
		GridBagConstraints lbConfirmPasswordC = new GridBagConstraints();
		lbConfirmPasswordC.fill = GridBagConstraints.VERTICAL;
		lbConfirmPasswordC.insets = new Insets(0, 0, 5, 5);
		lbConfirmPasswordC.gridx = 1;
		lbConfirmPasswordC.gridy = 4;
		add(lbConfirmPassword, lbConfirmPasswordC);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.addActionListener(focusField);
		GridBagConstraints confirmPasswordFieldC = new GridBagConstraints();
		confirmPasswordFieldC.insets = new Insets(10, 10, 10, 10);
		confirmPasswordFieldC.fill = GridBagConstraints.BOTH;
		confirmPasswordFieldC.gridx = 1;
		confirmPasswordFieldC.gridy = 5;
		add(confirmPasswordField, confirmPasswordFieldC);
		
		lbTelefono = new JLabel("Teléfono");
		GridBagConstraints gbc_lbTelefono = new GridBagConstraints();
		gbc_lbTelefono.fill = GridBagConstraints.VERTICAL;
		gbc_lbTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lbTelefono.gridx = 1;
		gbc_lbTelefono.gridy = 0;
		add(lbTelefono, gbc_lbTelefono);
		
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
		gbc_telefonoField.gridy = 1;
		add(telefonoField, gbc_telefonoField);
		telefonoField.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		add(lblNombre, gbc_lblNombre);
		
		
		productosPermission = new GroupRadioButtons(
				new String[] {"CRUD", "Read", "Add", "Delete", "Modify"}, 
				new Permission[]{CRUD_PRODUCTOS, READ_PRODUCTOS, ADD_PRODUCTOS, DELETE_PRODUCTOS, MODIFY_PRODUCTOS}, 
				"Productos");
		GridBagConstraints productosPermissionC = new GridBagConstraints();
		productosPermissionC.fill = GridBagConstraints.BOTH;
		productosPermissionC.insets = new Insets(5, 5, 5, 5);
		productosPermissionC.gridwidth = 5;
		productosPermissionC.gridx = 0;
		productosPermissionC.gridy = 7;
		add(productosPermission, productosPermissionC);

		proveedoresPermission = new GroupRadioButtons(
				new String[] {"CRUD", "Read", "Add", "Delete", "Modify"}, 
				new Permission[]{CRUD_PROVEEDORES, READ_PROVEEDORES, ADD_PROVEEDORES, DELETE_PROVEEDORES, MODIFY_PROVEEDORES}, 
				"Proveedores");
		GridBagConstraints proveedoresPermissionC = new GridBagConstraints();
		proveedoresPermissionC.fill = GridBagConstraints.BOTH;
		proveedoresPermissionC.insets = new Insets(5, 5, 5, 5);
		proveedoresPermissionC.gridwidth = 5;
		proveedoresPermissionC.gridx = 0;
		proveedoresPermissionC.gridy = 8;
		add(proveedoresPermission, proveedoresPermissionC);

		usuariosPermission = new GroupRadioButtons(
				new String[] {"CRUD", "Read", "Add", "Delete", "Modify"}, 
				new Permission[]{CRUD_USUARIOS, READ_USUARIOS, ADD_USUARIOS, DELETE_USUARIOS, MODIFY_USUARIOS}, 
				"Usuarios");
		GridBagConstraints usuariosPermissionC = new GridBagConstraints();
		usuariosPermissionC.fill = GridBagConstraints.BOTH;
		usuariosPermissionC.insets = new Insets(5, 5, 5, 5);
		usuariosPermissionC.gridwidth = 5;
		usuariosPermissionC.gridx = 0;
		usuariosPermissionC.gridy = 9;
		add(usuariosPermission, usuariosPermissionC);
		
		GridLayout buttonLayout = new GridLayout(1, 2, 5, 0);
		Border panelVentaBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
				"Venta", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Montserrat", Font.BOLD, 16));
		Border panelCompraBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
				"Compra", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Montserrat", Font.BOLD, 16));
		JPanel ventaButtonPanel = new JPanel();
		ventaButtonPanel.setBorder(panelVentaBorder);
		ventaButtonPanel.setLayout(buttonLayout);
		
		JPanel compraButtonPanel = new JPanel();
		compraButtonPanel.setBorder(panelCompraBorder);
		compraButtonPanel.setLayout(buttonLayout);
		
		readVentaBtn = new JRadioButton("Read Venta");
		writeVentaBtn = new JRadioButton("Write Venta");
		readVentaBtn.setHorizontalAlignment(JRadioButton.CENTER);
		writeVentaBtn.setHorizontalAlignment(JRadioButton.CENTER);
		compraButtonPanel.add(writeVentaBtn);
		compraButtonPanel.add(readVentaBtn);

		GridBagConstraints writeVentaBtnC = new GridBagConstraints();
		writeVentaBtnC.fill = GridBagConstraints.BOTH;
		writeVentaBtnC.insets = new Insets(5, 5, 5, 5);
		writeVentaBtnC.gridx = 0;
		writeVentaBtnC.gridy = 10;
		writeVentaBtnC.gridwidth = 2;
		add(ventaButtonPanel, writeVentaBtnC);

		writeCompraBtn = new JRadioButton("Write Compra");
		readCompraBtn = new JRadioButton("Read Compra");
		readCompraBtn.setHorizontalAlignment(JRadioButton.CENTER);
		writeCompraBtn.setHorizontalAlignment(JRadioButton.CENTER);
		ventaButtonPanel.add(writeCompraBtn);
		ventaButtonPanel.add(readCompraBtn);
		
		GridBagConstraints writeCompraBtnC = new GridBagConstraints();
		writeCompraBtnC.fill = GridBagConstraints.BOTH;
		writeCompraBtnC.insets = new Insets(5, 5, 5, 5);
		writeCompraBtnC.gridx = 2;
		writeCompraBtnC.gridy = 10;
		writeCompraBtnC.gridwidth = 2;
		add(compraButtonPanel, writeCompraBtnC);
				
		
		style(new Component[] {
		  usernameField,
		  passwordField,
		  telefonoField,
		  lbUsername,
		  lbPassword,
		  lbTelefono,
		  lblNombre,
		  lblApellido,
		  fieldNombre,
		  fieldApellido,
		  lbConfirmPassword,
		  confirmPasswordField
		});
		
		
		SwingUtilities.invokeLater(()->{			
			usernameField.requestFocusInWindow();
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
	
	
	public Component getLastItem() {
		return panelDireccion.getLastItem();
	}
	
	public void vaciarComponentes() {
		passwordField.setText("");
		confirmPasswordField.setText("");
		usernameField.setText("");
		telefonoField.setText("");
		fieldNombre.setText("");
		fieldApellido.setText("");
		panelDireccion.vaciarComponentes();
	}
	
	public void setUnneditable() {
		passwordField.setEditable(false);
		confirmPasswordField.setEditable(false);
		telefonoField.setEditable(false);
		fieldNombre.setEditable(false);
		fieldApellido.setEditable(false);
		panelDireccion.setUnneditable();;		
	}
	public void autocompleteFields(Usuario user) {
		fieldNombre.setText(user.getNombre());
		fieldApellido.setText(user.getApellido());
		telefonoField.setText(user.getTelefono());
		panelDireccion.autoCompleteFields(user.getDomicilio());
		passwordField.setText("");		
		confirmPasswordField.setText("");		
		
		writeCompraBtn.setSelected(user.getPermisos().contains(Permission.WRITE_COMPRA));
		writeVentaBtn.setSelected(user.getPermisos().contains(Permission.WRITE_VENTA));
		readCompraBtn.setSelected(user.getPermisos().contains(Permission.READ_COMPRA));
		readVentaBtn.setSelected(user.getPermisos().contains(Permission.READ_VENTA));
		
		productosPermission.setPressed(user.getPermisos());
		proveedoresPermission.setPressed(user.getPermisos());
		usuariosPermission.setPressed(user.getPermisos());
	}
}
