package app.UI.vista.general;

import app.UI.vista.listado.Listado;
import app.components.TextFieldSuggestion;
import app.interfaces.Service;
import app.modelos.Cliente;
import app.modelos.Proveedor;
import app.util.Util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

public class PanelClientes extends JPanel {

    protected Service<Cliente> clienteService;

    protected JTextField txtTelefono;
    protected JTextField txtNombre;
    protected JTextField txtApellido;

    private Listado listado;
    private Font fontLabel;
    private Font fontFunc;

    protected static final LinkedHashMap<String, Integer> columns = new LinkedHashMap<>() {{
        put("Telefono", 1);
        put("Nombre", 1);
        put("Apellido", 1);
        put("Tarjeta", 1);
    }};

    public static final boolean FROM_FIELD = true, FROM_TABLE = false;

    public PanelClientes(Service<Cliente> clientes, boolean autocomplete) {
        this.clienteService = clientes;
        fontLabel = new Font("Montserrat", Font.PLAIN, 16);
        fontFunc = new Font("Montserrat", Font.PLAIN, 13);

        Insets insetsLabel = new Insets(15, 0, 5, 0);
        Insets insetsField = new Insets(0, 0, 0, 0);

        GridBagLayout layout = new GridBagLayout();
        layout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 4.0};
        layout.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 200};
        layout.columnWeights = new double[]{1.0, 1.0, 1.0};

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        setLayout(layout);

        JLabel lblTelefono = new JLabel("Telefono");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = insetsLabel;
        add(lblTelefono, constraints);

        if(autocomplete){
            txtTelefono = new TextFieldSuggestion(Util.getClientFilter(clientes));
            txtTelefono.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
                @Override
                public void insertUpdate(DocumentEvent e) {
                    Cliente c = clientes.get(txtTelefono.getText());
                    if(c != null) {
                        autoCompleteFields(c, FROM_FIELD);
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    Cliente c = clientes.get(txtTelefono.getText());
                    if(c != null) {
                        autoCompleteFields(c, FROM_FIELD);
                    }
                }
            });
        } else{
            txtTelefono = new JTextField();
        }
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!(txtTelefono.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
                    e.consume();
                }
            }
        });
        txtTelefono.addKeyListener(Util.lenghtLimit(10));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = insetsField;
        add(txtTelefono, constraints);

        JLabel lblNombre = new JLabel("Nombre");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = insetsLabel;
        add(lblNombre, constraints);

        txtNombre = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = insetsField;
        add(txtNombre, constraints);

        JLabel lblApellido = new JLabel("Apellido");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = insetsLabel;
        add(lblApellido, constraints);

        txtApellido = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.insets = insetsField;
        add(txtApellido, constraints);

        listado = new Listado(columns, clienteService);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(15, 0, 0, 0);
        add(listado, constraints);

        if(autocomplete)
            listado.table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = listado.table.getSelectedRow();
                    if (selectedRow != -1) {
                        Cliente cliente = clienteService.getAll().get(selectedRow);
                        autoCompleteFields(cliente, FROM_TABLE);
                    }
                }
            });

        style(new Component[]{lblTelefono, txtTelefono, lblNombre, txtNombre, lblApellido, txtApellido});
    }


    public void style(Component[] components) {
        for(Component c : components) {
            if(c instanceof JLabel) {
                c.setFont(fontLabel);
            }
            else c.setFont(fontFunc);
        }
    }

    public void updateTable() {
        listado.update();
    }
 private void autoCompleteFields(Cliente c, boolean fromField){
//    public void autoCompleteFields(Cliente c, boolean fromField){
        if(c == null) return;
        if (!fromField) {
            txtTelefono.setText(c.getTelefono());
        }
        txtNombre.setText(c.getNombre());
        txtApellido.setText(c.getApellido());
    }

    public void vaciarComponentes() {
        txtTelefono.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
    }


    protected void setDisabled() {
        txtTelefono.setEditable(false);
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
    }

    protected void editable() {
        txtTelefono.setEditable(true);
        txtNombre.setEditable(true);
        txtApellido.setEditable(true);
    }

}
