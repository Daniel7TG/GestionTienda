package app.UI.vista.general;

import app.UI.vista.listado.Listado;
import app.components.TextFieldSuggestion;
import app.interfaces.Service;
import app.modelos.Proveedor;
import app.modelos.Usuario;
import app.util.TableModel;
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

public class PanelProveedores extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected Service<Proveedor> proveedores;
    protected JTextField rfcField;
    protected JTextField razonField;
    protected JTextField telefonoField;
    protected JLabel lbRfc;
    protected JLabel lbRazonSocial;
    protected JLabel lbTelefono;
    protected Font fontLabel;
    protected Font fontFunc;
    protected PanelCapturaDireccion panelDireccion;
    protected JLabel lblNombre;
    protected JLabel lblApellido;
    protected JTextField fieldNombre;
    protected JTextField fieldApellido;
    protected JScrollPane tablePanel;
    protected JTable productsTable;

    protected TableModel model;
    protected static final LinkedHashMap<String, Integer> columns = new LinkedHashMap<>() {{
        put("RFC", 1);
        put("Nombre", 3);
        put("Razón Social", 2);
        put("Dirección", 3);
        put("Teléfono", 2);
    }};


    public PanelProveedores(Service<Proveedor> proveedores, boolean autoComplete){
        fontLabel = new Font("Montserrat", Font.PLAIN, 16);
        fontFunc = new Font("Montserrat", Font.PLAIN, 13);
        this.proveedores = proveedores;
        Util.FocusField focusField = new Util.FocusField();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{35, 65, 35, 65, 35, 65, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 6.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        lbRfc = new JLabel("RFC");
        GridBagConstraints gbc_lbRfc = new GridBagConstraints();
        gbc_lbRfc.fill = GridBagConstraints.VERTICAL;
        gbc_lbRfc.gridwidth = 2;
        gbc_lbRfc.insets = new Insets(0, 0, 5, 5);
        gbc_lbRfc.gridx = 0;
        gbc_lbRfc.gridy = 0;
        add(lbRfc, gbc_lbRfc);

        if(autoComplete){
        rfcField = new TextFieldSuggestion(Util.getRfcFilter(proveedores));
        rfcField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
				}
				@Override
				public void insertUpdate(DocumentEvent e) {
					Proveedor prov = proveedores.get(rfcField.getText());
					if(prov != null) {
						autoCompleteFields(prov);
					}
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					Proveedor prov = proveedores.get(rfcField.getText());
					if(prov != null) {
						autoCompleteFields(prov);
					}
				}
			});
        }
		else {
			rfcField = new JTextField();
		}
        rfcField.setHorizontalAlignment(SwingConstants.CENTER);

        
        panelDireccion = new PanelCapturaDireccion();
        panelDireccion.getLastItem().setEnabled(false);
        panelDireccion.setUnneditable();
        GridBagConstraints gbc_direccion = new GridBagConstraints();
        gbc_direccion.gridheight = 7;
        gbc_direccion.fill = GridBagConstraints.BOTH;
        gbc_direccion.insets = new Insets(10, 0, 10, 0);
        gbc_direccion.gridx = 2;
        gbc_direccion.gridy = 0;
        gbc_direccion.gridwidth = 2;
        add(panelDireccion, gbc_direccion);
        GridBagConstraints gbc_rfcField = new GridBagConstraints();
        gbc_rfcField.gridwidth = 2;
        gbc_rfcField.insets = new Insets(10, 10, 10, 10);
        gbc_rfcField.fill = GridBagConstraints.BOTH;
        gbc_rfcField.gridx = 0;
        gbc_rfcField.gridy = 1;
        add(rfcField, gbc_rfcField);
        rfcField.setColumns(10);

        lblApellido = new JLabel("Apellido");
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.fill = GridBagConstraints.VERTICAL;
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 1;
        gbc_lblApellido.gridy = 2;
        add(lblApellido, gbc_lblApellido);

        fieldNombre = new JTextField();
        fieldNombre.setEditable(false);
        GridBagConstraints gbc_fieldNombre = new GridBagConstraints();
        gbc_fieldNombre.insets = new Insets(10, 10, 10, 10);
        gbc_fieldNombre.fill = GridBagConstraints.BOTH;
        gbc_fieldNombre.gridx = 0;
        gbc_fieldNombre.gridy = 3;
        add(fieldNombre, gbc_fieldNombre);
        fieldNombre.setColumns(10);

        fieldApellido = new JTextField();
        fieldApellido.setEditable(false);
        GridBagConstraints gbc_fieldApellido = new GridBagConstraints();
        gbc_fieldApellido.insets = new Insets(10, 10, 10, 10);
        gbc_fieldApellido.fill = GridBagConstraints.BOTH;
        gbc_fieldApellido.gridx = 1;
        gbc_fieldApellido.gridy = 3;
        add(fieldApellido, gbc_fieldApellido);
        fieldApellido.setColumns(10);

        lbRazonSocial = new JLabel("Razon Social");
        GridBagConstraints gbc_lbRazonSocial = new GridBagConstraints();
        gbc_lbRazonSocial.fill = GridBagConstraints.VERTICAL;
        gbc_lbRazonSocial.insets = new Insets(0, 0, 5, 5);
        gbc_lbRazonSocial.gridx = 0;
        gbc_lbRazonSocial.gridy = 4;
        add(lbRazonSocial, gbc_lbRazonSocial);

        razonField = new JTextField();
        razonField.setEditable(false);
        razonField.addActionListener(focusField);

        lbTelefono = new JLabel("Teléfono");
        GridBagConstraints gbc_lbTelefono = new GridBagConstraints();
        gbc_lbTelefono.fill = GridBagConstraints.VERTICAL;
        gbc_lbTelefono.insets = new Insets(0, 0, 5, 5);
        gbc_lbTelefono.gridx = 1;
        gbc_lbTelefono.gridy = 4;
        add(lbTelefono, gbc_lbTelefono);
        GridBagConstraints gbc_razonField = new GridBagConstraints();
        gbc_razonField.insets = new Insets(10, 10, 10, 10);
        gbc_razonField.fill = GridBagConstraints.BOTH;
        gbc_razonField.gridx = 0;
        gbc_razonField.gridy = 5;
        add(razonField, gbc_razonField);
        razonField.setColumns(10);

        telefonoField = new JTextField();
        telefonoField.setEditable(false);
        telefonoField.addActionListener(focusField);
        telefonoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!(telefonoField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
                    e.consume();
                }
            }
        });
        GridBagConstraints gbc_telefonoField = new GridBagConstraints();
        gbc_telefonoField.insets = new Insets(10, 10, 10, 10);
        gbc_telefonoField.fill = GridBagConstraints.BOTH;
        gbc_telefonoField.gridx = 1;
        gbc_telefonoField.gridy = 5;
        add(telefonoField, gbc_telefonoField);
        telefonoField.setColumns(10);

        lblNombre = new JLabel("Nombre");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 2;
        add(lblNombre, gbc_lblNombre);

//        tablePanel = new JScrollPane();
//        productsTable = new JTable();
//        data = proveedores.getMatrix();
//        model = new TableModel(productsTable, proveedores.getAll(), columnNames);
//        model.configurarTabla(1, 1, 1, 1, 1, 3);
//        productsTable.setModel(model);
//        tablePanel.setViewportView(productsTable);
        Listado listado = new Listado(columns, proveedores);
        listado.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Proveedor selectedProveedor = proveedores.getAll().get(selectedRow);
                        autoCompleteFields(selectedProveedor);
                    }
                }
            }
        });

        GridBagConstraints gbc_tablePanel = new GridBagConstraints();
        gbc_tablePanel.gridwidth = 4;
        gbc_tablePanel.insets = new Insets(0, 0, 0, 5);
        gbc_tablePanel.fill = GridBagConstraints.BOTH;
        gbc_tablePanel.gridx = 0;
        gbc_tablePanel.gridy = 7;
        add(listado, gbc_tablePanel);

        style(new Component[] {
                rfcField,
                razonField,
                telefonoField,
                lbRfc,
                lbRazonSocial,
                lbTelefono,
                lblNombre,
                lblApellido,
                fieldNombre,
                fieldApellido
        });


        SwingUtilities.invokeLater(()->{
            rfcField.requestFocusInWindow();
            panelDireccion.setPreItem(telefonoField);
        });
    }


    public void style(Component[] components) {
        for(Component c : components) {
            if(c instanceof JLabel) {
                c.setFont(fontLabel);
            }
            else c.setFont(fontFunc);
        }
    }


    public Component getLastItem() {
        return panelDireccion.getLastItem();
    }


    public void vaciarComponentes() {
        razonField.setText("");
        rfcField.setText("");
        telefonoField.setText("");
        fieldNombre.setText("");
        fieldApellido.setText("");
        panelDireccion.vaciarComponentes();
    }

    
    protected void setDisabled() {
    	fieldNombre.setEditable(false);
    	fieldApellido.setEditable(false);
    	razonField.setEditable(false);
    	telefonoField.setEditable(false);
    }
    

    protected void autoCompleteFields(Proveedor proveedor) {
        if(proveedor != null) {
            rfcField.setText(proveedor.getRfc());
            fieldNombre.setText(proveedor.getNombre());
            fieldApellido.setText(proveedor.getApellido());
            razonField.setText(proveedor.getRazonSocial());
            telefonoField.setText(proveedor.getTelefono());
            panelDireccion.autoCompleteFields(proveedor.getDomicilio());
        }
    }


}
