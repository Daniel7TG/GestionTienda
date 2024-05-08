package app.UI.vista.eliminar;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import app.UI.vista.general.PanelCapturaDireccion;
import app.components.TextFieldSuggestion;
import app.interfaces.Service;
import app.modelos.Proveedor;
import app.util.TableModel;
import app.util.Util;
import app.util.Util.FocusField;

public class PanelEliminarProveedor extends JPanel {

    private static final long serialVersionUID = 1L;
    private Service<Proveedor> proveedores;
    private TextFieldSuggestion rfcField;
    private JLabel lbRfc;
    private Font fontLabel;
    private JTable productsTable;
    private TableModel model;
    private Object[][] data = new Object[0][0];
    private String[] columnNames = {"RFC",
            "Nombre",
            "Apellido",
            "Razón Social",
            "Teléfono",
            "Dirección"
    };

    public PanelEliminarProveedor(Service<Proveedor> proveedores) {
        fontLabel = new Font("Montserrat", Font.PLAIN, 16);
        this.proveedores = proveedores;
        FocusField focusField = new FocusField();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{35, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        lbRfc = new JLabel("RFC");
        lbRfc.setFont(fontLabel);
        GridBagConstraints gbc_lbRfc = new GridBagConstraints();
        gbc_lbRfc.anchor = GridBagConstraints.EAST;
        gbc_lbRfc.insets = new Insets(0, 0, 5, 5);
        gbc_lbRfc.gridx = 0;
        gbc_lbRfc.gridy = 1;
        add(lbRfc, gbc_lbRfc);

        rfcField = new TextFieldSuggestion(Util.getRfcFilter(proveedores));
        rfcField.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_rfcField = new GridBagConstraints();
        gbc_rfcField.fill = GridBagConstraints.HORIZONTAL;
        gbc_rfcField.insets = new Insets(0, 0, 5, 0);
        gbc_rfcField.gridx = 1;
        gbc_rfcField.gridy = 1;
        add(rfcField, gbc_rfcField);
        rfcField.setColumns(10);

        JScrollPane tablePanel = new JScrollPane();
        productsTable = new JTable();
        data = proveedores.getMatrix();
        model = new TableModel(productsTable, proveedores.getAll(), columnNames);
        model.configurarTabla(1, 1, 1, 1, 1, 3);
        productsTable.setModel(model);
        tablePanel.setViewportView(productsTable);

        GridBagConstraints gbc_tablePanel = new GridBagConstraints();
        gbc_tablePanel.gridwidth = 2;
        gbc_tablePanel.insets = new Insets(0, 0, 0, 5);
        gbc_tablePanel.fill = GridBagConstraints.BOTH;
        gbc_tablePanel.gridx = 0;
        gbc_tablePanel.gridy = 2;
        add(tablePanel, gbc_tablePanel);

        style(new Component[]{rfcField, lbRfc});

        SwingUtilities.invokeLater(() -> {
            rfcField.requestFocusInWindow();
        });
    }


    public void style(Component[] components) {
        for (Component c : components) {
            if (c instanceof JLabel) {
                c.setFont(fontLabel);
            }
        }
    }

    public void eliminarProveedor() {
        String rfc = rfcField.getText();
        if (!rfc.isEmpty()) {
            if (proveedores.exists(rfc)) {
                proveedores.remove(rfc);
                model.update(proveedores.getMatrix());
                rfcField.setText("");
                JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente", "Proveedor eliminado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El proveedor con el RFC especificado no existe", "Proveedor no encontrado", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un RFC válido", "Campo RFC vacío", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Component getLastItem() {
        return rfcField;
    }
}
