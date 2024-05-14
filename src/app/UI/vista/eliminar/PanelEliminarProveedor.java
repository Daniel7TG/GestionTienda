package app.UI.vista.eliminar;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelProveedores;
import app.interfaces.Service;
import app.modelos.Proveedor;

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
