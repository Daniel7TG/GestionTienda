package app.UI.vista.listado;

import java.awt.GridLayout;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.interfaces.Service;
import app.modelos.Producto;
import app.util.TableModel;

public class PanelListadoProductos extends Listado {

	private static final LinkedHashMap<String, Integer> columns = new LinkedHashMap<String, Integer>(){{
		put("Código", 3);
		put("Nombre", 3);
		put("Marca", 2);
		put("Tipo", 2);
		put("Contenido", 1);
		put("Medida", 2);
		put("Presentación", 2);
		put("Máximo", 1);
		put("Mínimo", 1);
		put("Descripción", 3);
		put("Precio", 1);
		put("Cantidad", 1);
	}};

	public PanelListadoProductos(Service<Producto> catalogo) {
		super(columns,
				catalogo.getAll().stream().sorted(Producto::compareByName).toList()
		);
	}
	
}
