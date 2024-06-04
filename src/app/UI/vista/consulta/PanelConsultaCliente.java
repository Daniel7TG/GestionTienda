package app.UI.vista.consulta;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelClientes;
import app.UI.vista.listado.Listado;
import app.UI.vista.listado.PanelListadoVentas;
import app.interfaces.Service;
import app.modelos.Cliente;
import app.modelos.Venta;

import java.awt.*;
import java.util.List;


public class PanelConsultaCliente extends PanelClientes {

	Listado ventas;
	Service<Venta> ventasService;

	public PanelConsultaCliente(Service<Cliente> clientes, Service<Venta> ventasService) {
		super(clientes, true);
		this.ventasService = ventasService;

        txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		
		txtNombre.setFocusable(false);
		txtApellido.setFocusable(false);

		ventas = new Listado(PanelListadoVentas.columns, List.of());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 4;
		constraints.gridwidth = 1;
		add(ventas, constraints);
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


	@Override
	protected void autoCompleteFields(Cliente c, boolean fromField) {
		super.autoCompleteFields(c, fromField);
		List<Venta> ventasCliente = ventasService.getAll().stream().filter(v -> v.getCliente().equals(c.getTelefono())).toList();
		ventas.update(ventasCliente);
	}
}
