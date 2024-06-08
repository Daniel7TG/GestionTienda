package app.UI.vista.listado;

import app.interfaces.Service;
import app.modelos.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PanelListadoClientes extends JPanel {

	private Service<Cliente> clienteService;
	private JTable table;
	private JScrollPane tableScroll;
	private DefaultTableModel tableModel;

	private static Map<String, Integer> columns = new LinkedHashMap<>(){{
		put("Telefono", 1);
		put("Nombre", 1);
		put("Apellido", 1);
		put("Tarjeta", 1);
	}};

	public PanelListadoClientes(Service<Cliente> clienteService) {
		this.clienteService = clienteService;

		setLayout(new BorderLayout());

		tableModel = new DefaultTableModel(getColumnNames(), 0);

		table = new JTable(tableModel);

		cargarClientes();

		tableScroll = new JScrollPane(table);

		add(tableScroll, BorderLayout.CENTER);
	}

	private Object[] getColumnNames() {
		return columns.keySet().toArray();
	}

	public void cargarClientes() {
		tableModel.setRowCount(0);

		for (Cliente cliente : clienteService.getAll()) {
			Object[] rowData = new Object[]{
					cliente.getTelefono(),
					cliente.getNombre(),
					cliente.getApellido(),
					cliente.getTarjeta()
			};
			tableModel.addRow(rowData);
		}
	}

	public void updateTable() {
		cargarClientes();
	}
}
