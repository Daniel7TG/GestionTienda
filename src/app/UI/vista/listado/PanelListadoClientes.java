package app.UI.vista.listado;

import app.interfaces.Service;
import app.modelos.Cliente;
import app.modelos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PanelListadoClientes extends Listado {

	private static Map<String, Integer> columns = new LinkedHashMap<>(){{
		put("Telefono", 1);
		put("Nombre", 1);
		put("Apellido", 1);
		put("Tarjeta", 1);
	}};

	public PanelListadoClientes(Service<Cliente> clienteService) {
		super(columns,
				clienteService.getAll().stream().sorted(Cliente::compareByName).toList()
		);
	}
}
