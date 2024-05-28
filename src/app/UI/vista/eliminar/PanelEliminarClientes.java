package app.UI.vista.eliminar;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

public class PanelEliminarClientes extends PanelClientes {
    public PanelEliminarClientes(Service<Cliente> clientes, boolean autocomplete) {
        super(clientes, autocomplete);
    }
}
