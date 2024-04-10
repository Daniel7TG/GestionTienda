package app.UI.vista;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.modelos.Clientes;
import app.modelos.Proveedores;
import app.util.TableModel;

public class PanelListadoCompras extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private TableModel model;
	private String[] columnNames = {"Folio",
			"Total",
			"Fecha",
			"Detalles"};
	private Proveedores proveedores;
	private JScrollPane tableScroll;
	
	
	public PanelListadoCompras(Proveedores proveedores) {
		setLayout(new GridLayout(1, 1, 0, 0));

		this.proveedores = proveedores;		
		Object[][] data = proveedores.getData();
		table = new JTable();
		model = new TableModel(table, data, columnNames);
		table.setModel(model);
		model.renderListColumn(3);
		model.configurarTabla(2, 2, 2, 6);
		tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(tableScroll);
	}

}
