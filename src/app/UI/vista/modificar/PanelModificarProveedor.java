package app.UI.vista.modificar;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import app.UI.vista.general.PanelProveedores;
import app.enums.Permission;
import app.interfaces.Service;
import app.modelos.Domicilio;
import app.modelos.Proveedor;
import app.modelos.Usuario;

public class PanelModificarProveedor extends PanelProveedores {

    public PanelModificarProveedor(Service<Proveedor> proveedores) {
        super(proveedores, true);
        this.proveedores = proveedores;
        editable(); // Esto permitirá la edición de los campos en PanelModificarProveedor
    }

    
    public void modificarProveedor() {
        if(rfcField.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "El campo de RFC no puede estar vacío");
        } else if(proveedores.get(rfcField.getText()) == null) {
            JOptionPane.showMessageDialog(null, "El proveedor no existe");
        } else if(fieldNombre.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "El campo de nombre no puede estar vacío");
        } else if(fieldApellido.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "El campo de apellido no puede estar vacío");
        } else if(telefonoField.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "El campo de teléfono no puede estar vacío");
        } else if(razonField.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "El campo de razón social no puede estar vacío");
        } else if(!panelDireccion.isValidDirection()) {
            JOptionPane.showMessageDialog(null, "La dirección no es válida");
        } else {
            String rfc = rfcField.getText();
            String nombre = fieldNombre.getText();
            String apellido = fieldApellido.getText();
            String razonSocial = razonField.getText();
            String telefono = telefonoField.getText();
            Domicilio domicilio = panelDireccion.getDireccion();
            Proveedor proveedor = proveedores.get(rfc);

            // Actualizar los datos del proveedor
            proveedor.setNombre(nombre);
            proveedor.setApellido(apellido);
            proveedor.setRazonSocial(razonSocial);
            proveedor.setTelefono(telefono);
            proveedor.setDomicilio(domicilio);

            // Guardar los cambios
            if(proveedores.set(proveedor)) {
                JOptionPane.showMessageDialog(null, "Proveedor modificado correctamente");
                vaciarComponentes();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el proveedor");
            }
        }
    }

}
