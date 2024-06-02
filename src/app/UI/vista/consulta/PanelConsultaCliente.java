package app.UI.vista.consulta;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

public class PanelConsultaCliente extends PanelClientes {
    public PanelConsultaCliente(Service<Cliente> clientes) {
        super(clientes, true);
        
        txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		
		txtNombre.setFocusable(false);
		txtApellido.setFocusable(false);
		
    }

	public void consultarClientes() {
		String telefono = txtTelefono.getText();
		
		if(telefono.isBlank()) {
			JOptionPane.showMessageDialog(this,"Digite un número de telefono para continuar");
			return;
		}else if(telefono.length() != 10) {
			JOptionPane.showMessageDialog(this, "El número de teléfono es de 10 dígitos");
			return;
		}
		
		Cliente cliente = clienteService.get(telefono);
		if(cliente == null) {
			JOptionPane.showMessageDialog(this, "El cliente no existe");
			return;
		}
		
		txtNombre.setText(cliente.getNombre());
		txtApellido.setText(cliente.getApellido());
	
		
		
	}
}
