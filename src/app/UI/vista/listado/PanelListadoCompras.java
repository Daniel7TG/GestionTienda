package app.UI.vista.listado;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.abstractClasses.Transaccion;
import app.interfaces.Service;
import app.modelos.Compra;
import app.util.TableModel;

public class PanelListadoCompras extends Listado {

	private Service<Compra> proveedores;
	private JScrollPane tableScroll;

	private static Map<String, Integer> columns = new LinkedHashMap<>(){{
		put("Folio", 1);
		put("Total", 1);
		put("Fecha", 2);
		put("Detalles", 4);
		put("RFC", 3);
	}};
	
	public PanelListadoCompras(Service<Compra> proveedores) {
		super(columns, proveedores.getAll(), 3);
	}

}
