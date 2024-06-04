package app.UI.vista.listado;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.LinkedHashMap;

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
import app.util.TableModel;
import app.util.Util;

public class PanelListadoVentas extends Listado {


	public static final LinkedHashMap<String, Integer> columns = new LinkedHashMap<String, Integer>(){
		{
			put("Folio", 2);
			put("Total", 2);
			put("Fecha", 2);
			put("Detalles", 6);
			put("Usuario", 2);
			put("Cliente", 2);
		}
	};

	public PanelListadoVentas(Service<Venta> clientes) {
		super(columns, clientes);
	}
	
	

}
