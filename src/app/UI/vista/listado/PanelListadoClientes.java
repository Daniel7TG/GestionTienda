package app.UI.vista.listado;

import app.interfaces.Service;
import app.modelos.Cliente;
import app.modelos.Compra;

import javax.swing.*;
import javax.swing.event.CaretListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class PanelListadoClientes extends Listado {

    private Service<Cliente> clientes;
    private JScrollPane tableScroll;

    private static Map<String, Integer> columns = new LinkedHashMap<>(){{
        put("Telefono", 1);
        put("Nombre", 1);
        put("Apellido", 1);
        put("Tarjeta", 1);
    }};

    public PanelListadoClientes(Service<Cliente> clientes) {
        super(columns, clientes.getAll());
    }

}
