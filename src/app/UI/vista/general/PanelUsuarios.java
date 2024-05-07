package app.UI.vista.general;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import app.components.GroupRadioButtons;
import app.interfaces.Service;
import app.modelos.Proveedor;
import app.modelos.Usuario;
import app.util.TableModel;
import app.util.Util.FocusField;

public abstract class PanelUsuarios extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Service<Usuario> usuarios;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField telefonoField;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JLabel lbConfirmPassword;
	private JLabel lbTelefono;
	private Font fontLabel;
	private Font fontFunc;
	private PanelCapturaDireccion panelDireccion;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField fieldNombre;
	private JTextField fieldApellido;
	
	protected PanelUsuarios(Service<Usuario> usuarios) {
		this.usuarios = usuarios;
		
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);

		FocusField focusField = new FocusField();
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{35, 65, 35, 65, 35, 65, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 6.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbUsername = new JLabel("Nombre de Usuario");
		GridBagConstraints gbc_lbRfc = new GridBagConstraints();
		gbc_lbRfc.fill = GridBagConstraints.VERTICAL;
		gbc_lbRfc.insets = new Insets(0, 0, 5, 5);
		gbc_lbRfc.gridx = 0;
		gbc_lbRfc.gridy = 0;
		add(lbUsername, gbc_lbRfc);
		
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_rfcField = new GridBagConstraints();
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
		
		
		GroupRadioButtons productosPermission = new GroupRadioButtons(new String[] {"CRUD", "Read", "Add", "Delete", "Modify"}, 
				"Productos");
		GridBagConstraints productosPermissionC = new GridBagConstraints();
		productosPermissionC.fill = GridBagConstraints.BOTH;
		productosPermissionC.insets = new Insets(5, 5, 5, 5);
		productosPermissionC.gridwidth = 5;
		productosPermissionC.gridx = 0;
		productosPermissionC.gridy = 6;
		add(productosPermission, productosPermissionC);
		
		
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
	
	public void guardarProveedor() {
		if(usernameField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de username no puede estar vacío");			
		} else if(passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "El campo de razón no puede estar vacío");
		} else if(fieldNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de nombre no puede estar vacío");
		} else if(fieldApellido.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de apellido no puede estar vacío");
		} else if(usuarios.exists(usernameField.getText())) {
			JOptionPane.showMessageDialog(null, "Nombre de usuario en uso");
		} else if(telefonoField.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de teléfono no puede estar vacío");				
		} else if(!panelDireccion.isValidDirection()) {} 
		else {			
			Usuario user = new Usuario();
			usuarios.save(user);
			JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
			vaciarComponentes();
		}
	}
	
	public Component getLastItem() {
		return panelDireccion.getLastItem();
	}
	
	public void vaciarComponentes() {
		passwordField.setText("");
		usernameField.setText("");
		telefonoField.setText("");
		fieldNombre.setText("");
		fieldApellido.setText("");
		panelDireccion.vaciarComponentes();
	}
	
}
