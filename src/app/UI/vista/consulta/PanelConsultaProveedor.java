	package app.UI.vista.consulta;
	
	import app.UI.vista.general.PanelProveedores;
    import app.interfaces.Service;
	import app.modelos.Proveedor;

    public class PanelConsultaProveedor extends PanelProveedores {
	
		public PanelConsultaProveedor(Service<Proveedor> proveedores) {
			super(proveedores, true);
			setDisabled();
		}
	
	}
