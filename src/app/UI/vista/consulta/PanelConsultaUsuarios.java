package app.UI.vista.consulta;

import app.UI.vista.general.PanelUsuarios;
import app.interfaces.Service;
import app.modelos.Usuario;

public class PanelConsultaUsuarios extends PanelUsuarios {

	public PanelConsultaUsuarios(Service<Usuario> usuarios) {
		super(usuarios, true);
		setUnneditable();
	}
	
}
