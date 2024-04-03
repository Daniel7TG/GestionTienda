package app.UI.vista;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.interfaces.Funcionable;
import app.modelos.Clientes;
import app.util.TableModel;
import app.util.Util;

public class PanelListadoVentas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private TableModel model;
	private String[] columnNames = {"Total", 
			"Fecha",
			"Detalles"};
	private Clientes clientes;
	private JScrollPane tableScroll;
	
	@SuppressWarnings("serial")
	public PanelListadoVentas(Clientes clientes) {
		setLayout(new GridLayout(1, 1, 0, 0));

		this.clientes = clientes;		
		Object[][] data = clientes.getData();
		table = new JTable();
		model = new TableModel(table, data, columnNames);
		table.setModel(model);
		model.renderListColumn(2);
		model.configurarTabla(2, 2, 6);
		tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(tableScroll);
	}
	
	

}
