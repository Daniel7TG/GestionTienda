package app.UI.vista.general;

import app.UI.vista.captura.PanelCapturaProductos;
import app.UI.vista.listado.Listado;
import app.components.TextFieldSuggestion;
import app.interfaces.Service;
import app.modelos.Producto;
import app.util.TableModel;
import app.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static app.dao.DaoUtility.*;
import static app.enums.ColorStyles.CONTENT;
import static app.util.Util.capitalize;
import static app.util.Util.range;
import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.visualizar;

public class PanelProductos extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    protected JLabel imageLabel;
    protected JTextField codigoBarrasField;
    protected JTextField nombreField;
    protected JTextField marcaField;
    protected JTextField contenidoField;
    protected JTextField descripcionField;
    protected JTextField precioField;
    protected JButton imageButton;
    protected JComboBox<Integer> maximoBox;
    protected JComboBox<Integer> minimoBox;
    protected JComboBox<String> tipoBox;
    protected JComboBox<String> medidaBox;
    protected JComboBox<String> presentacionBox;
    protected FocusField focusField;
    protected FocusBox focusBox;
    protected ValidateUnderscore validateUnderscore;
    protected Font fontLabel;
    protected Font fontFunc;
    protected Insets separation;

    protected Service<Producto> catalogo;
    protected Listado listado;
    protected static final LinkedHashMap<String, Integer> columns = new LinkedHashMap<String, Integer>(){{
        put("Código", 3);
        put("Nombre", 3);
        put("Marca", 2);
        put("Tipo", 2);
        put("Contenido", 1);
        put("Medida", 2);
        put("Presentación", 2);
        put("Máximo", 1);
        put("Mínimo", 1);
        put("Descripción", 3);
        put("Precio", 1);
        put("Cantidad", 1);
    }};
    private final boolean BY_CODE = true, BY_NAME = false;




    KeyListener codeKeyListener = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(!Character.isDigit(e.getKeyChar())) {
                e.consume();
            }
        }
    };
    DocumentListener codeTextListener = new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {}
        public void removeUpdate(DocumentEvent e) {
            Producto p = catalogo.get(codigoBarrasField.getText());
            if(p != null) {
                autoCompleteFields(p, BY_CODE);
            }
        }
        public void insertUpdate(DocumentEvent e) {
            Producto p = catalogo.get(codigoBarrasField.getText());
            if(p != null) {
                autoCompleteFields(p, BY_CODE);
            }
        }
    };
    DocumentListener nameTextListener = new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {}
        public void removeUpdate(DocumentEvent e) {
            Producto producto = catalogo.getByData(nombreField.getText());
            if(producto != null) {
                autoCompleteFields(producto, BY_NAME);
            }
        }
        public void insertUpdate(DocumentEvent e) {
            Producto producto = catalogo.getByData(nombreField.getText());
            if(producto != null) {
                autoCompleteFields(producto, BY_NAME);
            }
        }
    };


    protected PanelProductos(Service<Producto> catalogo, boolean autoComplete) {
        this.catalogo = catalogo;
        focusField = new FocusField();
        focusBox = new FocusBox();
        validateUnderscore = new ValidateUnderscore();
        separation = new Insets(15, 5, 5, 0);
        fontLabel = new Font("Montserrat", Font.PLAIN, 16);
        fontFunc = new Font("Montserrat", Font.PLAIN, 13);

        setBorder(new EmptyBorder(20, 20, 0, 20));
        setBackground(CONTENT.color);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 300};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 3.0, 15.0};
        setLayout(gridBagLayout);

        JLabel codigoDeBarras = new JLabel("Codigo de Barras");
        GridBagConstraints gbc_codigoDeBarras = new GridBagConstraints();
        gbc_codigoDeBarras.insets = new Insets(0, 0, 5, 5);
        gbc_codigoDeBarras.fill = GridBagConstraints.VERTICAL;
        gbc_codigoDeBarras.anchor = GridBagConstraints.EAST;
        gbc_codigoDeBarras.gridx = 0;
        gbc_codigoDeBarras.gridy = 1;


        JLabel nombre = new JLabel("Nombre");
        GridBagConstraints gbc_nombre = new GridBagConstraints();
        gbc_nombre.insets = new Insets(0, 0, 5, 5);
        gbc_nombre.fill = GridBagConstraints.VERTICAL;
        gbc_nombre.anchor = GridBagConstraints.EAST;
        gbc_nombre.gridx = 2;
        gbc_nombre.gridy = 1;

        imageLabel = new JLabel("");
        imageLabel.setBounds(0, 0, 200, 200);
        GridBagConstraints gbc_imageLabel = new GridBagConstraints();
        gbc_imageLabel.insets = new Insets(0, 0, 5, 0);
        gbc_imageLabel.fill = GridBagConstraints.VERTICAL;
        gbc_imageLabel.gridheight = 5;
        gbc_imageLabel.gridx = 4;
        gbc_imageLabel.gridy = 1;
        add(imageLabel, gbc_imageLabel);

        imageButton = new JButton("Cambiar Imagen");
        imageButton.setFocusable(false);
        GridBagConstraints gbc_imageButton = new GridBagConstraints();
        gbc_imageButton.insets = new Insets(0, 0, 5, 0);
        gbc_imageButton.fill = GridBagConstraints.VERTICAL;
        gbc_imageButton.gridx = 4;
        gbc_imageButton.gridy = 0;
        add(imageButton, gbc_imageButton);
        imageButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(Util.RUTAIMG);
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                imageLabel.setIcon(Util.getImage(filePath, 200, 200));
            }
        });

        JLabel marca = new JLabel("Marca");
        GridBagConstraints gbc_marca = new GridBagConstraints();
        gbc_marca.insets = new Insets(0, 0, 5, 5);
        gbc_marca.fill = GridBagConstraints.VERTICAL;
        gbc_marca.anchor = GridBagConstraints.EAST;
        gbc_marca.gridx = 0;
        gbc_marca.gridy = 2;

        marcaField = new JTextField();
        GridBagConstraints gbc_marcaField = new GridBagConstraints();
        gbc_marcaField.insets = new Insets(0, 0, 5, 5);
        gbc_marcaField.fill = GridBagConstraints.BOTH;
        gbc_marcaField.gridx = 1;
        gbc_marcaField.gridy = 2;
        marcaField.setColumns(10);
        marcaField.addKeyListener(validateUnderscore);
        marcaField.addActionListener(focusField);

        JLabel tipo = new JLabel("Tipo");
        GridBagConstraints gbc_tipo = new GridBagConstraints();
        gbc_tipo.insets = new Insets(0, 0, 5, 5);
        gbc_tipo.fill = GridBagConstraints.VERTICAL;
        gbc_tipo.anchor = GridBagConstraints.EAST;
        gbc_tipo.gridx = 2;
        gbc_tipo.gridy = 2;

        tipoBox = new JComboBox<String>();
        tipoBox.addItemListener(focusBox);

        GridBagConstraints gbc_tipoBox = new GridBagConstraints();
        gbc_tipoBox.insets = new Insets(0, 0, 5, 5);
        gbc_tipoBox.fill = GridBagConstraints.BOTH;
        gbc_tipoBox.gridx = 3;
        gbc_tipoBox.gridy = 2;

        JLabel contenido = new JLabel("Contenido");
        GridBagConstraints gbc_contenido = new GridBagConstraints();
        gbc_contenido.insets = new Insets(0, 0, 5, 5);
        gbc_contenido.fill = GridBagConstraints.VERTICAL;
        gbc_contenido.anchor = GridBagConstraints.EAST;
        gbc_contenido.gridx = 0;
        gbc_contenido.gridy = 3;

        contenidoField = new JTextField();
        contenidoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(contenidoField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$")) {
                    e.consume();
                }
            }
        });
        GridBagConstraints gbc_contenidoField = new GridBagConstraints();
        gbc_contenidoField.insets = new Insets(0, 0, 5, 5);
        gbc_contenidoField.fill = GridBagConstraints.BOTH;
        gbc_contenidoField.gridx = 1;
        gbc_contenidoField.gridy = 3;
        contenidoField.setColumns(10);
        contenidoField.addKeyListener(validateUnderscore);
        contenidoField.addActionListener(focusField);

        JLabel unidadDeMedida = new JLabel("Unidad de Medida");
        GridBagConstraints gbc_unidadDeMedida = new GridBagConstraints();
        gbc_unidadDeMedida.insets = new Insets(0, 0, 5, 5);
        gbc_unidadDeMedida.fill = GridBagConstraints.VERTICAL;
        gbc_unidadDeMedida.anchor = GridBagConstraints.EAST;
        gbc_unidadDeMedida.gridx = 2;
        gbc_unidadDeMedida.gridy = 3;

        medidaBox = new JComboBox<String>();
        GridBagConstraints gbc_medidaBox = new GridBagConstraints();
        gbc_medidaBox.insets = new Insets(0, 0, 5, 5);
        gbc_medidaBox.fill = GridBagConstraints.BOTH;
        gbc_medidaBox.gridx = 3;
        gbc_medidaBox.gridy = 3;
        medidaBox.addItemListener(focusBox);

        JLabel presentacion = new JLabel("Presentacion");
        GridBagConstraints gbc_presentacion = new GridBagConstraints();
        gbc_presentacion.insets = new Insets(0, 0, 5, 5);
        gbc_presentacion.fill = GridBagConstraints.VERTICAL;
        gbc_presentacion.anchor = GridBagConstraints.EAST;
        gbc_presentacion.gridx = 0;
        gbc_presentacion.gridy = 4;
        presentacionBox = new JComboBox<String>();
        GridBagConstraints gbc_presentacionBox = new GridBagConstraints();
        gbc_presentacionBox.insets = new Insets(0, 0, 5, 5);
        gbc_presentacionBox.fill = GridBagConstraints.BOTH;
        gbc_presentacionBox.gridx = 1;
        gbc_presentacionBox.gridy = 4;
        presentacionBox.addItemListener(focusBox);

        JLabel precio = new JLabel("Precio de Venta");
        GridBagConstraints gbc_precio = new GridBagConstraints();
        gbc_precio.insets = new Insets(0, 0, 5, 5);
        gbc_precio.fill = GridBagConstraints.VERTICAL;
        gbc_precio.anchor = GridBagConstraints.EAST;
        gbc_precio.gridx = 2;
        gbc_precio.gridy = 4;
        add(precio, gbc_precio);
        precioField = new JTextField();
        precioField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(precioField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$")) {
                    e.consume();
                }
            }
        });

        GridBagConstraints gbc_precioField = new GridBagConstraints();
        gbc_precioField.insets = new Insets(0, 0, 5, 5);
        gbc_precioField.fill = GridBagConstraints.BOTH;
        gbc_precioField.gridx = 3;
        gbc_precioField.gridy = 4;

        JLabel stockMaximo = new JLabel("Stock Maximo");
        GridBagConstraints gbc_stockMaximo = new GridBagConstraints();
        gbc_stockMaximo.insets = new Insets(0, 0, 5, 5);
        gbc_stockMaximo.fill = GridBagConstraints.VERTICAL;
        gbc_stockMaximo.anchor = GridBagConstraints.EAST;
        gbc_stockMaximo.gridx = 0;
        gbc_stockMaximo.gridy = 5;

        maximoBox = new JComboBox<Integer>();
        GridBagConstraints gbc_maximoBox = new GridBagConstraints();
        gbc_maximoBox.insets = new Insets(0, 0, 5, 5);
        gbc_maximoBox.fill = GridBagConstraints.BOTH;
        gbc_maximoBox.gridx = 1;
        gbc_maximoBox.gridy = 5;
        maximoBox.addItemListener(focusBox);
        Stream.of(range(100)).forEach(i -> maximoBox.addItem(i));

        JLabel stockMinimo = new JLabel("Stock Minimo");
        GridBagConstraints gbc_stockMinimo = new GridBagConstraints();
        gbc_stockMinimo.insets = new Insets(0, 0, 5, 5);
        gbc_stockMinimo.fill = GridBagConstraints.VERTICAL;
        gbc_stockMinimo.anchor = GridBagConstraints.EAST;
        gbc_stockMinimo.gridx = 2;
        gbc_stockMinimo.gridy = 5;

        minimoBox = new JComboBox<Integer>();
        GridBagConstraints gbc_minimoBox = new GridBagConstraints();
        gbc_minimoBox.insets = new Insets(0, 0, 5, 5);
        gbc_minimoBox.fill = GridBagConstraints.BOTH;
        gbc_minimoBox.gridx = 3;
        gbc_minimoBox.gridy = 5;
        Stream.of(range(100)).forEach(i -> minimoBox.addItem(i));
        minimoBox.addItemListener(focusBox);

        JLabel descripcion = new JLabel("Descripción");
        GridBagConstraints gbc_descripcion = new GridBagConstraints();
        gbc_descripcion.fill = GridBagConstraints.BOTH;
        gbc_descripcion.gridx = 1;
        gbc_descripcion.gridy = 6;

        descripcionField = new JTextField();
        GridBagConstraints gbc_descripcionField = new GridBagConstraints();
        gbc_descripcionField.insets = new Insets(0, 0, 5, 5);
        gbc_descripcionField.gridwidth = 3;
        gbc_descripcionField.fill = GridBagConstraints.BOTH;
        gbc_descripcionField.gridx = 1;
        gbc_descripcionField.gridy = 7;
        descripcionField.setColumns(10);


        listado = new Listado(columns, catalogo);
        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.gridwidth = 5;
        gbc_table.insets = new Insets(0, 0, 0, 5);
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.gridx = 0;
        gbc_table.gridy = 8;
        add(listado, gbc_table);

        getMedidas().forEach(i -> medidaBox.addItem(i));
        getTipos().forEach(i -> tipoBox.addItem(i));
        getPresentaciones().forEach(i -> presentacionBox.addItem(i));

        if(autoComplete){
            isAutoComplete();
        }
        else{
            codigoBarrasField = new JTextField();
            codigoBarrasField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char[] texto = codigoBarrasField.getText().toCharArray();
                    if (texto.length > 12) {
                        if (catalogo.exists(codigoBarrasField.getText())) {
                            visualizar("Ya existe el producto");
                            codigoBarrasField.setText("");
                        } else {
                            nombreField.requestFocus();
                        }
                        e.consume();
                    }
                }
            });
            nombreField = new JTextField();
            nombreField.addActionListener(e -> {
                String gotNombre = nombreField.getText();
                if (gotNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insertar Nombre");
                } else {
                    nombreField.setText(capitalize(nombreField.getText()));
                    nombreField.transferFocus();
                }
            });
        }

        codigoBarrasField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        GridBagConstraints gbc_codigoBarrasField = new GridBagConstraints();
        gbc_codigoBarrasField.insets = new Insets(0, 0, 5, 5);
        gbc_codigoBarrasField.fill = GridBagConstraints.BOTH;
        gbc_codigoBarrasField.gridx = 1;
        gbc_codigoBarrasField.gridy = 1;
        GridBagConstraints gbc_nombreField = new GridBagConstraints();
        gbc_nombreField.insets = new Insets(0, 0, 5, 5);
        gbc_nombreField.fill = GridBagConstraints.BOTH;
        gbc_nombreField.gridx = 3;
        gbc_nombreField.gridy = 1;
        nombreField.addKeyListener(validateUnderscore);


        style(new Component[] { codigoBarrasField, nombreField, marcaField, contenidoField, descripcionField, maximoBox,
                        minimoBox, tipoBox, medidaBox, presentacionBox, descripcionField, precioField, codigoDeBarras, nombre,
                        marca, tipo, contenido, unidadDeMedida, presentacion, stockMaximo, stockMinimo, descripcion, precio },
                new GridBagConstraints[] { gbc_codigoBarrasField, gbc_nombreField, gbc_marcaField, gbc_contenidoField,
                        gbc_descripcionField, gbc_maximoBox, gbc_minimoBox, gbc_tipoBox, gbc_medidaBox,
                        gbc_presentacionBox, gbc_descripcionField, gbc_precioField, gbc_codigoDeBarras, gbc_nombre,
                        gbc_marca, gbc_tipo, gbc_contenido, gbc_unidadDeMedida, gbc_presentacion, gbc_stockMaximo,
                        gbc_stockMinimo, gbc_descripcion, gbc_precio });

    }

    public void style(Component[] components, GridBagConstraints[] constraints) {
        for (GridBagConstraints constraint : constraints) {
            constraint.insets = separation;
        }

        for (Component c : components) {
            if (c instanceof JLabel) {
                c.setFont(fontLabel);
            } else
                c.setFont(fontFunc);
        }
        for (int i = 0; i < components.length; i++)
            add(components[i], constraints[i]);
    }


    public void actualizarTabla() {
        listado.update();
    }


    public void reiniciarCaptura() {
        vaciarComponents();
        codigoBarrasField.requestFocus();
    }


    public void disableFields(){
        nombreField.setEnabled(false);
        marcaField.setEnabled(false);
        tipoBox.setEnabled(false);
        contenidoField.setEnabled(false);
        medidaBox.setEnabled(false);
        presentacionBox.setEnabled(false);
        maximoBox.setEnabled(false);
        minimoBox.setEnabled(false);
        descripcionField.setEnabled(false);
        precioField.setEnabled(false);
        imageButton.setEnabled(false);
    }


    public void vaciarComponents() {
        codigoBarrasField.setText("");
        nombreField.setText("");
        marcaField.setText("");
        tipoBox.setSelectedIndex(0);
        contenidoField.setText("");
        medidaBox.setSelectedIndex(0);
        presentacionBox.setSelectedIndex(0);
        maximoBox.setSelectedIndex(0);
        minimoBox.setSelectedIndex(0);
        descripcionField.setText("");
        precioField.setText("0.0");
        imageLabel.setIcon(null);
    }


    private void autoCompleteFields(Producto producto, boolean code) {
        if(code) {
            nombreField.setText(producto.getNombre());
        } else {
            codigoBarrasField.setText(producto.getCodigoBarras());
            SwingUtilities.invokeLater(()->{
                nombreField.setText(producto.getNombre());
            });
        }
        marcaField.setText(producto.getMarca());
        contenidoField.setText(producto.getContenido());
        descripcionField.setText(producto.getDescripcion());
        maximoBox.setSelectedItem(producto.getStockMaximo());
        minimoBox.setSelectedItem(producto.getStockMinimo());
        tipoBox.setSelectedItem(producto.getTipo());
        medidaBox.setSelectedItem(producto.getUnidadDeMedida());
        presentacionBox.setSelectedItem(producto.getPresentacion());
        precioField.setText(String.valueOf(producto.getPrecioVenta()));
        repaint();
    }

    public void setActionName() {
        codigoBarrasField.removeKeyListener(codeKeyListener);
        codigoBarrasField.getDocument().removeDocumentListener(codeTextListener);
        codigoBarrasField.setEnabled(false);
        nombreField.getDocument().addDocumentListener(nameTextListener);
        nombreField.setEnabled(true);
        vaciarComponents();
    }


    public void setActionCode() {
        codigoBarrasField.addKeyListener(codeKeyListener);
        codigoBarrasField.getDocument().addDocumentListener(codeTextListener);
        codigoBarrasField.setEnabled(true);
        nombreField.getDocument().removeDocumentListener(nameTextListener);
        nombreField.setEnabled(false);
        vaciarComponents();
    }


    private void isAutoComplete(){
        codigoBarrasField = new TextFieldSuggestion(Util.getCodeFilter(catalogo));
        nombreField = new TextFieldSuggestion(Util.getNameFilter(catalogo));
        listado.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listado.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(listado.table.getSelectedRow() != -1) {
                    Producto p = catalogo.get((String)listado.table.getValueAt(listado.table.getSelectedRow(), 0) );
                    autoCompleteFields(p, BY_NAME);
                }
            }
        });
        JRadioButton byCodeBtn = new JRadioButton("Buscar Por Codigo");
        byCodeBtn.setSelected(true);
        GridBagConstraints gbc_byCodeBtn = new GridBagConstraints();
        gbc_byCodeBtn.anchor = GridBagConstraints.EAST;
        gbc_byCodeBtn.insets = new Insets(0, 0, 5, 5);
        gbc_byCodeBtn.gridx = 0;
        gbc_byCodeBtn.gridy = 0;
        add(byCodeBtn, gbc_byCodeBtn);
        byCodeBtn.addActionListener(e ->{
            setActionCode();
        });

        JRadioButton byNameBtn = new JRadioButton("Buscar Por Nombre");
        GridBagConstraints gbc_byNameBtn = new GridBagConstraints();
        gbc_byNameBtn.anchor = GridBagConstraints.EAST;
        gbc_byNameBtn.insets = new Insets(0, 0, 5, 5);
        gbc_byNameBtn.gridx = 2;
        gbc_byNameBtn.gridy = 0;
        add(byNameBtn, gbc_byNameBtn);
        byNameBtn.addActionListener(e ->{
            setActionName();
        });

        setActionCode();

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(byCodeBtn);
        searchGroup.add(byNameBtn);
    }


    public static class ValidateUnderscore implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '_')
                e.consume();
        }
        @Override
        public void keyPressed(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}
    }
    public static class FocusField implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((JTextField) e.getSource()).transferFocus();
        }
    }

    public static class FocusBox implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            ((JComboBox<?>) e.getSource()).transferFocus();
        }
    }


}
