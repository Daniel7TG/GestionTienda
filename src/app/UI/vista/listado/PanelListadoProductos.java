package app.UI.vista.listado;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.interfaces.Service;
import app.modelos.Producto;
import app.modelos.containers.Catalogo;
import app.util.TableModel;

public class PanelListadoProductos extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private TableModel model;
	private String[] columnNames = {"Codigo",
			"Nombre",
			"Marca",
			"Tipo",
			"Contenido",
			"Medida",
			"Presentacion",
			"Maximo",
			"Minimo",
			"Descripcion",
			"Precio",
			"Cantidad",
			};
	private JScrollPane tableScroll;
	
	public PanelListadoProductos(Service<Producto> catalogo) {
		setLayout(new GridLayout(1, 1, 0, 0));

		Object[][] data = catalogo.getMatrix();
		table = new JTable();
		model = new TableModel(table, data, columnNames);
		table.setModel(model);
		model.configurarTabla(3, 3, 2, 2, 1, 2, 2, 1, 1, 3, 1, 1);
		table.setRowHeight(30);
		tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(tableScroll);	
	}	
	
}



////