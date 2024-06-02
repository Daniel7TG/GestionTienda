package app.UI.vista.eliminar;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

public class PanelEliminarClientes extends PanelClientes {
	public PanelEliminarClientes(Service<Cliente> clientes) {
		super(clientes, true);
		
		
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
//		pa q no se seleccionennnnnn
		txtNombre.setFocusable(false);
		txtApellido.setFocusable(false);

	}
	
	public void eliminarClientes() {
		String telefono = txtTelefono.getText();
		
		if(telefono.isBlank()) {
			JOptionPane.showMessageDialog(this,"Añada un numero de telefono para continuar");
			return;
		}else if(telefono.length() != 10) {
			JOptionPane.showMessageDialog(this, "El telefono debe de tener 10 digitos");
			return;
		}else if(!clienteService.exists(telefono)) {
			JOptionPane.showMessageDialog(this, "Cliente no encontrado");
			return;
		}
		
		  int confirm = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este cliente?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            clienteService.remove(telefono);
	            JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente");
	            vaciarComponentes();
	            updateTable();
	        }

		
	}
}
