package app.UI.vista.listado;

import javax.swing.JPanel;

import app.interfaces.Listable;
import app.interfaces.Service;
import app.modelos.Usuario;

import java.util.LinkedHashMap;
import java.util.Map;

public class PanelListadoUsuarios extends Listado {

	final private static LinkedHashMap<String, Integer> columns = new LinkedHashMap<>(){{
			put("Username", 2);
			put("Nombre", 3);
			put("Teléfono", 3);
			put("Domicilio", 3);
			put("Permisos", 2);
	}};

	public PanelListadoUsuarios(Service<Usuario> service) {
		super(columns, service, 4);
	}

}
