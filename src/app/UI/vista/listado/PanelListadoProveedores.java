package app.UI.vista.listado;

import app.interfaces.Listable;
import app.interfaces.Service;
import app.modelos.Proveedor;

import java.util.LinkedHashMap;
import java.util.Map;

public class PanelListadoProveedores extends Listado {
    private static final LinkedHashMap<String, Integer> columns = new LinkedHashMap<>() {{
        put("RFC", 1);
        put("Nombre", 3);
        put("Razón Social", 2);
        put("Dirección", 3);
        put("Teléfono", 2);
    }};

    public PanelListadoProveedores(Service<Proveedor> service) {
        super(columns,
                service.getAll().stream().sorted(Proveedor::compareByName).toList()
        );

    }
}
