package app.UI.vista.listado;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.abstractClasses.Transaccion;
import app.interfaces.Funcionable;
import app.interfaces.Service;
import app.modelos.Venta;
import app.modelos.containers.HistorialVenta;
import app.util.TableModel;
import app.util.Util;

public class PanelListadoVentas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private TableModel model;
	private String[] columnNames = {"Folio",
			"Total", 
			"Fecha",
			"Detalles",
			"Usuario"};
	private Service<Venta> clientes;
	private JScrollPane tableScroll;
	
	@SuppressWarnings("serial")
	public PanelListadoVentas(Service<Venta> clientes) {
		setLayout(new GridLayout(1, 1, 0, 0));

		this.clientes = clientes;		
		Object[][] data = clientes.getMatrix();
		table = new JTable();
		model = new TableModel(table, clientes.getAll(), columnNames);
		table.setModel(model);
		model.renderListColumn(3);
		model.configurarTabla(2, 2, 2, 6, 2);
		tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(tableScroll);
	}
	
	

}
