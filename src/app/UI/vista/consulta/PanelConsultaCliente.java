package app.UI.vista.consulta;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

public class PanelConsultaCliente extends PanelClientes {
    public PanelConsultaCliente(Service<Cliente> clientes, boolean autocomplete) {
        super(clientes, autocomplete);
    }
}
