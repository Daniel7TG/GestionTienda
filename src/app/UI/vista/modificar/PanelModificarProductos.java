package app.UI.vista.modificar;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.UI.vista.captura.PanelCapturaProductos.FocusBox;
import app.UI.vista.captura.PanelCapturaProductos.FocusField;
import app.components.TextFieldSuggestion;
import app.interfaces.Funcionable;
import app.modelos.Producto;
import app.modelos.containers.Catalogo;
import app.util.TableModel;
import app.util.Util;

import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.BadLocationException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import static app.dao.DaoUtility.getMedidas;
import static app.dao.DaoUtility.getPresentaciones;
import static app.dao.DaoUtility.getTipos;
import static app.enums.ColorStyles.CONTENT;
import static app.util.Util.capitalize;
import static app.util.Util.range;
import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.visualizar;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class PanelModificarProductos extends JPanel {

	private static final long serialVersionUID = 1L;
	private Funcionable<Producto> catalogo;
	private TextFieldSuggestion codigoBarrasField;
	private TextFieldSuggestion nombreField;
	private JTextField marcaField;
	private JTextField contenidoField;
	private JTextField descripcionField;
	private JTextField precioField;
	private JComboBox<Integer> maximoBox;
	private JComboBox<Integer> minimoBox;
	private JComboBox<String> tipoBox;
	private JComboBox<String> medidaBox;
	private JComboBox<String> presentacionBox;
	private FocusField focusField;
	private FocusBox focusBox;
	private Font fontLabel;
	private Font fontFunc;
	private Insets separation;
	private JTable table;

	private TableModel model;
	private String[] columnNames = {"Codigo",
			"Nombre",
			"Marca",
			"Tipo",
			"Contenido",
			"Medida",
			"Presentacion",
			"Maximo",
			"Minimo",
			"Descripcion",
			"Precio",
			"Cantidad",
			};
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
			Producto p = new Producto();
			p.setMainData(nombreField.getText());
			Producto producto = catalogo.get(p);
			if(producto != null) {
				autoCompleteFields(producto, BY_NAME);
			}
		}
		public void insertUpdate(DocumentEvent e) {
			Producto p = new Producto();
			p.setMainData(nombreField.getText());
			Producto producto = catalogo.get(p);
			if(producto != null) {
				autoCompleteFields(producto, BY_NAME);
			}
		}
	};
	
	private JRadioButton byCodeBtn;
	private JRadioButton byNameBtn;

	
	public PanelModificarProductos(Funcionable<Producto> catalogo) {
		this.catalogo = catalogo;

		focusField = new FocusField();
		focusBox = new FocusBox();
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
		
		Object[][] data = catalogo.getData();
		table = new JTable();
		model = new TableModel(table, data, columnNames);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() != -1) {
					Producto p = catalogo.get(table.getSelectedRow());
					autoCompleteFields(p, BY_NAME);
				}
			}
		});
		table.setModel(model);
		model.configurarTabla(4, 4, 3, 3, 2, 3, 3, 2, 2, 4, 2, 2);
		table.setRowHeight(30);
		JScrollPane tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 5;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 8;
		add(tableScroll, gbc_table);
		
		
		byCodeBtn = new JRadioButton("Buscar Por Codigo");
		byCodeBtn.setSelected(true);
		GridBagConstraints gbc_byCodeBtn = new GridBagConstraints();
		gbc_byCodeBtn.anchor = GridBagConstraints.EAST;
		gbc_byCodeBtn.insets = new Insets(0, 0, 5, 5);
		gbc_byCodeBtn.gridx = 0;
		gbc_byCodeBtn.gridy = 0;
		add(byCodeBtn, gbc_byCodeBtn);
		byCodeBtn.addActionListener(e ->{
			setActionCode();
			removeActionName();
		});
		
		byNameBtn = new JRadioButton("Buscar Por Nombre");
		GridBagConstraints gbc_byNameBtn = new GridBagConstraints();
		gbc_byNameBtn.anchor = GridBagConstraints.EAST;
		gbc_byNameBtn.insets = new Insets(0, 0, 5, 5);
		gbc_byNameBtn.gridx = 2;
		gbc_byNameBtn.gridy = 0;
		add(byNameBtn, gbc_byNameBtn);
		byNameBtn.addActionListener(e ->{
			setActionName();
			removeActionCode();
		});

		ButtonGroup searchGroup = new ButtonGroup();
		searchGroup.add(byCodeBtn);
		searchGroup.add(byNameBtn);		
        
		
		JLabel codigoDeBarras = new JLabel("Codigo de Barras");
		GridBagConstraints gbc_codigoDeBarras = new GridBagConstraints();
		gbc_codigoDeBarras.insets = new Insets(0, 0, 5, 5);
		gbc_codigoDeBarras.fill = GridBagConstraints.VERTICAL;
		gbc_codigoDeBarras.anchor = GridBagConstraints.EAST;
		gbc_codigoDeBarras.gridx = 0;
		gbc_codigoDeBarras.gridy = 1;
		add(codigoDeBarras, gbc_codigoDeBarras);
		
		codigoBarrasField = new TextFieldSuggestion(Util.getCodeFilter(catalogo));
		GridBagConstraints gbc_codigoBarrasField = new GridBagConstraints();
		gbc_codigoBarrasField.insets = new Insets(0, 0, 5, 5);
		gbc_codigoBarrasField.fill = GridBagConstraints.BOTH;
		gbc_codigoBarrasField.gridx = 1;
		gbc_codigoBarrasField.gridy = 1;
		add(codigoBarrasField, gbc_codigoBarrasField);
		
		JLabel nombre = new JLabel("Nombre");
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.fill = GridBagConstraints.VERTICAL;
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 2;
		gbc_nombre.gridy = 1;
		add(nombre, gbc_nombre);
		
		
		nombreField = new TextFieldSuggestion(Util.getNameFilter(catalogo));
		GridBagConstraints gbc_nombreField = new GridBagConstraints();
		gbc_nombreField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreField.fill = GridBagConstraints.BOTH;
		gbc_nombreField.gridx = 3;
		gbc_nombreField.gridy = 1;
		add(nombreField, gbc_nombreField);


		JLabel marca = new JLabel("Marca");
		GridBagConstraints gbc_marca = new GridBagConstraints();
		gbc_marca.insets = new Insets(0, 0, 5, 5);
		gbc_marca.fill = GridBagConstraints.VERTICAL;
		gbc_marca.anchor = GridBagConstraints.EAST;
		gbc_marca.gridx = 0;
		gbc_marca.gridy = 2;
		add(marca, gbc_marca);
		
		marcaField = new JTextField();
		GridBagConstraints gbc_marcaField = new GridBagConstraints();
		gbc_marcaField.insets = new Insets(0, 0, 5, 5);
		gbc_marcaField.fill = GridBagConstraints.BOTH;
		gbc_marcaField.gridx = 1;
		gbc_marcaField.gridy = 2;
		add(marcaField, gbc_marcaField);
		marcaField.setColumns(10);
		marcaField.addActionListener(focusField);
		
		JLabel tipo = new JLabel("Tipo");
		GridBagConstraints gbc_tipo = new GridBagConstraints();
		gbc_tipo.insets = new Insets(0, 0, 5, 5);
		gbc_tipo.fill = GridBagConstraints.VERTICAL;
		gbc_tipo.anchor = GridBagConstraints.EAST;
		gbc_tipo.gridx = 2;
		gbc_tipo.gridy = 2;
		add(tipo, gbc_tipo);
		
		tipoBox = new JComboBox<String>();
		tipoBox.addItemListener(focusBox);
		
		GridBagConstraints gbc_tipoBox = new GridBagConstraints();
		gbc_tipoBox.insets = new Insets(0, 0, 5, 5);
		gbc_tipoBox.fill = GridBagConstraints.BOTH;
		gbc_tipoBox.gridx = 3;
		gbc_tipoBox.gridy = 2;
		add(tipoBox, gbc_tipoBox);
		
		JLabel contenido = new JLabel("Contenido");
		GridBagConstraints gbc_contenido = new GridBagConstraints();
		gbc_contenido.insets = new Insets(0, 0, 5, 5);
		gbc_contenido.fill = GridBagConstraints.VERTICAL;
		gbc_contenido.anchor = GridBagConstraints.EAST;
		gbc_contenido.gridx = 0;
		gbc_contenido.gridy = 3;
		add(contenido, gbc_contenido);
		
		contenidoField = new JTextField();
		GridBagConstraints gbc_contenidoField = new GridBagConstraints();
		gbc_contenidoField.insets = new Insets(0, 0, 5, 5);
		gbc_contenidoField.fill = GridBagConstraints.BOTH;
		gbc_contenidoField.gridx = 1;
		gbc_contenidoField.gridy = 3;
		add(contenidoField, gbc_contenidoField);
		contenidoField.setColumns(10);
		contenidoField.addActionListener(focusField);

		
		JLabel unidadDeMedida = new JLabel("Unidad de Medida");
		GridBagConstraints gbc_unidadDeMedida = new GridBagConstraints();
		gbc_unidadDeMedida.insets = new Insets(0, 0, 5, 5);
		gbc_unidadDeMedida.fill = GridBagConstraints.VERTICAL;
		gbc_unidadDeMedida.anchor = GridBagConstraints.EAST;
		gbc_unidadDeMedida.gridx = 2;
		gbc_unidadDeMedida.gridy = 3;
		add(unidadDeMedida, gbc_unidadDeMedida);
		
		medidaBox = new JComboBox<String>();
		GridBagConstraints gbc_medidaBox = new GridBagConstraints();
		gbc_medidaBox.insets = new Insets(0, 0, 5, 5);
		gbc_medidaBox.fill = GridBagConstraints.BOTH;
		gbc_medidaBox.gridx = 3;
		gbc_medidaBox.gridy = 3;
		add(medidaBox, gbc_medidaBox);
		medidaBox.addItemListener(focusBox);

		
		JLabel presentacion = new JLabel("Presentacion");
		GridBagConstraints gbc_presentacion = new GridBagConstraints();
		gbc_presentacion.insets = new Insets(0, 0, 5, 5);
		gbc_presentacion.fill = GridBagConstraints.VERTICAL;
		gbc_presentacion.anchor = GridBagConstraints.EAST;
		gbc_presentacion.gridx = 0;
		gbc_presentacion.gridy = 4;
		add(presentacion, gbc_presentacion);	
		presentacionBox = new JComboBox<String>();
		GridBagConstraints gbc_presentacionBox = new GridBagConstraints();
		gbc_presentacionBox.insets = new Insets(0, 0, 5, 5);
		gbc_presentacionBox.fill = GridBagConstraints.BOTH;
		gbc_presentacionBox.gridx = 1;
		gbc_presentacionBox.gridy = 4;
		add(presentacionBox, gbc_presentacionBox);
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
		GridBagConstraints gbc_precioField = new GridBagConstraints();
		gbc_precioField.insets = new Insets(0, 0, 5, 5);
		gbc_precioField.fill = GridBagConstraints.BOTH;
		gbc_precioField.gridx = 3;
		gbc_precioField.gridy = 4;
		add(precioField, gbc_precioField);
		
		
		JLabel stockMaximo = new JLabel("Stock Maximo");
		GridBagConstraints gbc_stockMaximo = new GridBagConstraints();
		gbc_stockMaximo.insets = new Insets(0, 0, 5, 5);
		gbc_stockMaximo.fill = GridBagConstraints.VERTICAL;
		gbc_stockMaximo.anchor = GridBagConstraints.EAST;
		gbc_stockMaximo.gridx = 0;
		gbc_stockMaximo.gridy = 5;
		add(stockMaximo, gbc_stockMaximo);
		
		maximoBox = new JComboBox<Integer>();
		GridBagConstraints gbc_maximoBox = new GridBagConstraints();
		gbc_maximoBox.insets = new Insets(0, 0, 5, 5);
		gbc_maximoBox.fill = GridBagConstraints.BOTH;
		gbc_maximoBox.gridx = 1;
		gbc_maximoBox.gridy = 5;
		add(maximoBox, gbc_maximoBox);
		maximoBox.addItemListener(focusBox);
		Stream.of(range(100)).forEach(i -> maximoBox.addItem(i));
		
		JLabel stockMinimo = new JLabel("Stock Minimo");
		GridBagConstraints gbc_stockMinimo = new GridBagConstraints();
		gbc_stockMinimo.insets = new Insets(0, 0, 5, 5);
		gbc_stockMinimo.fill = GridBagConstraints.VERTICAL;
		gbc_stockMinimo.anchor = GridBagConstraints.EAST;
		gbc_stockMinimo.gridx = 2;
		gbc_stockMinimo.gridy = 5;
		add(stockMinimo, gbc_stockMinimo);
		
		minimoBox = new JComboBox<Integer>();
		GridBagConstraints gbc_minimoBox = new GridBagConstraints();
		gbc_minimoBox.insets = new Insets(0, 0, 5, 5);
		gbc_minimoBox.fill = GridBagConstraints.BOTH;
		gbc_minimoBox.gridx = 3;
		gbc_minimoBox.gridy = 5;
		add(minimoBox, gbc_minimoBox);
		Stream.of(range(100)).forEach(i -> minimoBox.addItem(i));
		minimoBox.addItemListener(focusBox);


		JLabel descripcion = new JLabel("Descripción");
		GridBagConstraints gbc_descripcion = new GridBagConstraints();
		gbc_descripcion.fill = GridBagConstraints.BOTH;
		gbc_descripcion.gridx = 1;
		gbc_descripcion.gridy = 5;

		descripcionField = new JTextField();
		GridBagConstraints gbc_descripcionField = new GridBagConstraints();
		gbc_descripcionField.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionField.gridwidth = 3;
		gbc_descripcionField.fill = GridBagConstraints.BOTH;
		gbc_descripcionField.gridx = 1;
		gbc_descripcionField.gridy = 7;
		add(descripcionField, gbc_descripcionField);
		descripcionField.setColumns(10);
		
		
		getMedidas().forEach(i -> medidaBox.addItem(i));
		getTipos().forEach(i -> tipoBox.addItem(i));
		getPresentaciones().forEach(i -> presentacionBox.addItem(i));
	
		
		style(new Component[] {
				codigoBarrasField,
				nombreField,
				marcaField,
				contenidoField,
				descripcionField,
				maximoBox,
				minimoBox,
				tipoBox,
				medidaBox,
				presentacionBox,
				descripcionField,
				precioField,
				codigoDeBarras,
				nombre,
				marca,
				tipo,
				contenido,
				unidadDeMedida,
				presentacion,
				stockMaximo, 
				stockMinimo,
				descripcion,
				precio
		}, new GridBagConstraints[] {
				gbc_codigoBarrasField,
				gbc_nombreField,
				gbc_marcaField,
				gbc_contenidoField,
				gbc_descripcionField,
				gbc_maximoBox,
				gbc_minimoBox,
				gbc_tipoBox,
				gbc_medidaBox,
				gbc_presentacionBox,
				gbc_descripcionField,
				gbc_precioField,
				gbc_codigoDeBarras,
				gbc_nombre,
				gbc_marca,
				gbc_tipo,
				gbc_contenido,
				gbc_unidadDeMedida,
				gbc_presentacion,
				gbc_stockMaximo, 
				gbc_stockMinimo,
				gbc_descripcion,
				gbc_precio
		}
		);
		
		setActionCode();
		removeActionName();

	}
	
	public void modificarProducto() {
		/**
		 * 
		 * 
		 * funcion modificar
		 * 
		 * 
		 */
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


	public void style(Component[] components, GridBagConstraints[] constraints) {		
		for(GridBagConstraints constraint : constraints) {
			constraint.insets = separation;
		}
		
		for(Component c : components) {
			if(c.getClass().getName().equals("javax.swing.JLabel")) {
				c.setFont(fontLabel);
			}
			else c.setFont(fontFunc);	
		}
		for(int i = 0; i < components.length; i++)
		add(components[i], constraints[i]);
	}
	
	public void removeActionCode() {
		codigoBarrasField.removeKeyListener(codeKeyListener);
		codigoBarrasField.getDocument().removeDocumentListener(codeTextListener);
		codigoBarrasField.setEnabled(false);
	}
	public void setActionCode() {
		codigoBarrasField.addKeyListener(codeKeyListener);
		codigoBarrasField.getDocument().addDocumentListener(codeTextListener);
		codigoBarrasField.setEnabled(true);
		clearComponents();
	}
	public void removeActionName() {
		nombreField.getDocument().removeDocumentListener(nameTextListener);
		nombreField.setEnabled(false);
	}
	public void setActionName() {
		nombreField.getDocument().addDocumentListener(nameTextListener);
		nombreField.setEnabled(true);
		clearComponents();
	}
	public void clearComponents() {
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
	}
}
