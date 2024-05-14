package app.UI.vista.captura;

import javax.swing.JPanel;

import app.UI.vista.general.PanelProveedores;
import app.interfaces.Service;
import app.modelos.Proveedor;
import app.util.Util.FocusField;
import app.util.TableModel;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelCapturaProveedor extends PanelProveedores {

	public PanelCapturaProveedor(Service<Proveedor> proveedores) {
		super(proveedores, false);
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
			proveedores.save(proveedor);
			JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente");
			vaciarComponentes();
			updateTable();
		}
	}

}
