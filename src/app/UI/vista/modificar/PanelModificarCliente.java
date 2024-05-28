package app.UI.vista.modificar;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

public class PanelModificarCliente extends PanelClientes {

    public PanelModificarCliente(Service<Cliente> clientes) {
        super(clientes, true);
    }

}
