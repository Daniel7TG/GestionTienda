package app.UI.vista.captura;

import app.UI.vista.general.PanelClientes;
import app.interfaces.Service;
import app.modelos.Cliente;

import javax.swing.*;

public class PanelCapturaClientes extends PanelClientes {


    public PanelCapturaClientes(Service<Cliente> clientes) {
        super(clientes, false);
    }

    public void guardarCliente(){
        String telefono = txtTelefono.getText();
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();

        if(telefono.isBlank()){
            JOptionPane.showMessageDialog(this, "El telefono no puede estar vacio");
            return;
        } else if(telefono.length() != 10){
            JOptionPane.showMessageDialog(this, "El telefono debe tener 10 digitos");
            return;
        } else if(apellido.isBlank()){
            JOptionPane.showMessageDialog(this, "El apellido no puede estar vacio");
            return;
        } else if(nombre.isBlank()){
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio");
            return;
        } else if(clienteService.exists(telefono)){
            JOptionPane.showMessageDialog(this, "El cliente ya existe");
            return;
        }

        Cliente cliente = new Cliente(nombre, apellido, telefono);
        JOptionPane.showMessageDialog(this, "Cliente guardado con tarjeta: " + cliente.getTarjeta());
        clienteService.save(cliente);
        vaciarComponentes();
        updateTable();
    }

}
