package app.UI.vista.eliminar;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.UI.vista.captura.PanelCapturaDireccion;
import app.UI.vista.general.PanelProveedores;
import app.components.TextFieldSuggestion;
import app.interfaces.Service;
import app.modelos.Proveedor;
import app.util.TableModel;
import app.util.Util;
import app.util.Util.FocusField;

public class PanelEliminarProveedor extends PanelProveedores {

	public PanelEliminarProveedor(Service<Proveedor> proveedores) {
		super(proveedores, true);
		setDisabled();
	}


	public void eliminarProveedor() {
		String rfc = rfcField.getText();
		if (proveedores.exists(rfc)) {
			int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este proveedor?",
					"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				proveedores.remove(rfc);
				updateTable();
				vaciarComponentes();
				JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente.");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "El proveedor no existe.");
		}
	}
}
