package app.UI.vista.modificar;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

public class PanelModificarCliente extends PanelClientes {

	public PanelModificarCliente(Service<Cliente> clientes) {
		super(clientes, true);

	}

	public void modificarCliente() {
        String telefono = txtTelefono.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();

        if (telefono.isBlank()) {
            JOptionPane.showMessageDialog(this, "El teléfono no puede estar vacío");
            return;
        } else if (telefono.length() != 10) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener 10 dígitos");
            return;
        } else if (nombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
            return;
        } else if (apellido.isBlank()) {
            JOptionPane.showMessageDialog(this, "El apellido no puede estar vacío");
            return;
        }

        Cliente cliente = new Cliente(nombre, apellido, telefono);
        boolean resultado = clienteService.set(cliente);
        if(resultado) {
        	JOptionPane.showMessageDialog(this, "Cliente modificado");
        	vaciarComponentes();
        	updateTable();
        }else{
        	JOptionPane.showMessageDialog(this, "Error");
        }
    }
}
