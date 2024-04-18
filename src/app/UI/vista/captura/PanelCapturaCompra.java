package app.UI.vista.captura;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import app.UI.vista.general.PanelOpciones;
import app.abstractClasses.Detalles;
import app.components.TextFieldSuggestion;
import app.interfaces.Funcionable;
import app.modelos.Compra;
import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.modelos.Producto;
import app.modelos.Proveedor;
import app.modelos.containers.Catalogo;
import app.modelos.containers.HistorialCompra;
import app.modelos.containers.Proveedores;
import app.util.TableModel;
import app.util.Util;

import java.time.*;

import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.visualizar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class PanelCapturaCompra extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<DetallesCompra> listaDetalles;
	private JTable productsTable;
	private JButton confirmarButton;
	private JPanel panelProducto;
	private JPanel panelProveedor;

	private HistorialCompra historialCompra;
	private Proveedores proveedores;
	private Catalogo catalogo;
	private Insets separation;
	private Font fontLabel;
	private Font fontFunc;

	private JScrollPane tablePanel;
	private TableModel model;
	private Object[][] data = new Object[0][0];
	private String[] columnNames = {"Codigo",
			"Cantidad",
			"Precio", 
	"Total"};
	private List<String> headers = List.of("Registro de compra", LocalDate.now().toString());

	private JLabel lbPrecio;
	private JLabel lbCantidad;
	private JLabel lbTotal;
	private JLabel lbFecha;
	private JLabel lblRfc;
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JLabel lblCodigo;
	private JLabel lblName;
	private JLabel lblProv;
	private JLabel lblProvDetalles;
	private JLabel lblProd;
	private JLabel lblProdDetalles;
	private JTextField fieldNombre;
	private JTextField totalField;
	private JTextField fechaField;
	private JTextField precioField;
	private JTextField fieldTelefono;
	private TextFieldSuggestion fieldCodigoP;
	private TextFieldSuggestion fieldNombreP;
	private TextFieldSuggestion fieldRfc;
	private JSpinner cantidadSpin;
	private JRadioButton radByCode;
	private JRadioButton radByName;
	private final boolean BY_CODE = true, BY_NAME = false; 



	DocumentListener codeTextListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent e) {}
		public void removeUpdate(DocumentEvent e) {
			Producto p = catalogo.get(fieldCodigoP.getText());
			if(p != null) {
				autoCompleteFields(p, BY_CODE);
			}
		}
		public void insertUpdate(DocumentEvent e) {
			Producto p = catalogo.get(fieldCodigoP.getText());
			if(p != null) {
				autoCompleteFields(p, BY_CODE);
			}
		}		
	};
	
	
	DocumentListener nameTextListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent e) {}
		public void removeUpdate(DocumentEvent e) {
			Producto p = new Producto();
			p.setMainData(fieldNombreP.getText());
			Producto producto = catalogo.get(p);
			if(producto != null) {
				autoCompleteFields(producto, BY_NAME);
			}
		}
		public void insertUpdate(DocumentEvent e) {
			Producto p = new Producto();
			p.setMainData(fieldNombreP.getText());
			Producto producto = catalogo.get(p);
			if(producto != null) {
				autoCompleteFields(producto, BY_NAME);
			}
		}
	};

	public PanelCapturaCompra(Catalogo catalogo, HistorialCompra historialCompra, Proveedores proveedores) {

		this.catalogo = catalogo;
		this.historialCompra = historialCompra;
		this.proveedores = proveedores;

		listaDetalles = new ArrayList<DetallesCompra>();
		separation = new Insets(10, 5, 10, 5);
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{2.0, 2.0, 3.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 3.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		panelProducto = new JPanel();
		GridBagConstraints gbc_panelProducto = new GridBagConstraints();
		gbc_panelProducto.gridheight = 3;
		gbc_panelProducto.gridwidth = 2;
		gbc_panelProducto.insets = new Insets(5, 5, 5, 5);
		gbc_panelProducto.fill = GridBagConstraints.BOTH;
		gbc_panelProducto.gridx = 0;
		gbc_panelProducto.gridy = 0;
		add(panelProducto, gbc_panelProducto);
		GridBagLayout gbl_panelProducto = new GridBagLayout();
		gbl_panelProducto.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelProducto.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelProducto.columnWeights = new double[]{2.0, 2.0, 1.0, Double.MIN_VALUE};
		gbl_panelProducto.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelProducto.setLayout(gbl_panelProducto);

		lblCodigo = new JLabel("Codigo");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		panelProducto.add(lblCodigo, gbc_lblCodigo);

		fieldCodigoP = new TextFieldSuggestion(Util.getCodeFilter(catalogo), 0, 0); 
		fieldCodigoP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}				
			}
		});

		GridBagConstraints gbc_fieldCodigoP = new GridBagConstraints();
		gbc_fieldCodigoP.fill = GridBagConstraints.BOTH;
		gbc_fieldCodigoP.insets = new Insets(0, 0, 5, 5);
		gbc_fieldCodigoP.gridx = 0;
		gbc_fieldCodigoP.gridy = 1;
		panelProducto.add(fieldCodigoP, gbc_fieldCodigoP);

		fieldNombreP = new TextFieldSuggestion(Util.getNameFilter(catalogo), 0, 0); 
		GridBagConstraints gbc_fieldNombreP = new GridBagConstraints();
		gbc_fieldNombreP.fill = GridBagConstraints.BOTH;
		gbc_fieldNombreP.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNombreP.gridx = 1;
		gbc_fieldNombreP.gridy = 1;
		panelProducto.add(fieldNombreP, gbc_fieldNombreP);

		lblName = new JLabel("Nombre");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		panelProducto.add(lblName, gbc_lblName);

		radByCode = new JRadioButton("Buscar por Codigo");
		radByCode.setSelected(true);
		GridBagConstraints gbc_radByCode = new GridBagConstraints();
		gbc_radByCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_radByCode.insets = new Insets(0, 0, 5, 0);
		gbc_radByCode.gridx = 2;
		gbc_radByCode.gridy = 0;
		panelProducto.add(radByCode, gbc_radByCode);

		radByName = new JRadioButton("Buscar por Nombre");
		GridBagConstraints gbc_radByName = new GridBagConstraints();
		gbc_radByName.fill = GridBagConstraints.BOTH;
		gbc_radByName.insets = new Insets(0, 0, 5, 0);
		gbc_radByName.gridx = 2;
		gbc_radByName.gridy = 1;
		panelProducto.add(radByName, gbc_radByName);

		ButtonGroup group = new ButtonGroup();
		group.add(radByCode);
		group.add(radByName);

		lblProd = new JLabel("Producto:");
		GridBagConstraints gbc_lblProd = new GridBagConstraints();
		gbc_lblProd.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProd.insets = new Insets(0, 0, 0, 5);
		gbc_lblProd.gridx = 0;
		gbc_lblProd.gridy = 2;
		panelProducto.add(lblProd, gbc_lblProd);

		lblProdDetalles = new JLabel(" ");
		GridBagConstraints gbc_lblProdDetalles = new GridBagConstraints();
		gbc_lblProdDetalles.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProdDetalles.gridwidth = 2;
		gbc_lblProdDetalles.gridx = 1;
		gbc_lblProdDetalles.gridy = 2;
		panelProducto.add(lblProdDetalles, gbc_lblProdDetalles);

		tablePanel = new JScrollPane();
		productsTable = new JTable();
		model = new TableModel(productsTable, data, columnNames);
		productsTable.setModel(model);
		tablePanel.setViewportView(productsTable);

		GridBagConstraints gbc_tableScroll = new GridBagConstraints();
		gbc_tableScroll.fill = GridBagConstraints.BOTH;
		gbc_tableScroll.gridy = 0;
		gbc_tableScroll.gridx = 2;
		gbc_tableScroll.gridheight = 11;
		add(tablePanel, gbc_tableScroll);
		SpinnerNumberModel cantidadModel = new SpinnerNumberModel(1, 1, 1000, 1);




		confirmarButton = new JButton("Confirmar");
		confirmarButton.setFocusPainted(false);
		confirmarButton.setRolloverSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		confirmarButton.setSelectedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		confirmarButton.setBorderPainted(false);
		confirmarButton.setContentAreaFilled(false);
		confirmarButton.setForeground(Color.WHITE);
		confirmarButton.setHorizontalTextPosition(SwingConstants.CENTER);
		confirmarButton.setPressedIcon(new ImageIcon(PanelOpciones.class.getResource("/img/selectedConfirm.png")));
		confirmarButton.setDisabledIcon(new ImageIcon(PanelOpciones.class.getResource("/img/disabledConfirm.png")));
		confirmarButton.setRolloverIcon(new ImageIcon(PanelOpciones.class.getResource("/img/hoverConfirm.png")));
		confirmarButton.setIcon(new ImageIcon(PanelOpciones.class.getResource("/img/defaultConfirm.png")));
		confirmarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		confirmarButton.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				confirmarButton.setSelected(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				confirmarButton.setSelected(false);							
			}						
		});
		confirmarButton.addActionListener(e->registrarProducto());

		panelProveedor = new JPanel();
		GridBagConstraints gbc_panelProveedor = new GridBagConstraints();
		gbc_panelProveedor.gridheight = 3;
		gbc_panelProveedor.gridwidth = 2;
		gbc_panelProveedor.insets = new Insets(5, 5, 5, 5);
		gbc_panelProveedor.fill = GridBagConstraints.BOTH;
		gbc_panelProveedor.gridx = 0;
		gbc_panelProveedor.gridy = 3;
		add(panelProveedor, gbc_panelProveedor);
		GridBagLayout gbl_panelProveedor = new GridBagLayout();
		gbl_panelProveedor.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelProveedor.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelProveedor.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelProveedor.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelProveedor.setLayout(gbl_panelProveedor);

		lblRfc = new JLabel("RFC del proveedor");
		GridBagConstraints gbc_lblRfc = new GridBagConstraints();
		gbc_lblRfc.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRfc.insets = new Insets(0, 0, 5, 5);
		gbc_lblRfc.gridx = 0;
		gbc_lblRfc.gridy = 0;
		panelProveedor.add(lblRfc, gbc_lblRfc);

		fieldRfc = new TextFieldSuggestion(Util.getRfcFilter(proveedores), 0, 0);
		fieldRfc.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {
				Proveedor p = proveedores.get(fieldRfc.getText());
				if(p != null) {
					autoCompleteFields(p);
				}
			}
			public void insertUpdate(DocumentEvent e) {
				Proveedor p = proveedores.get(fieldRfc.getText());
				if(p != null) {
					autoCompleteFields(p);
				}
			} 
		});
		GridBagConstraints gbc_fieldRfc = new GridBagConstraints();
		gbc_fieldRfc.fill = GridBagConstraints.BOTH;
		gbc_fieldRfc.insets = new Insets(0, 0, 5, 5);
		gbc_fieldRfc.gridx = 0;
		gbc_fieldRfc.gridy = 1;
		panelProveedor.add(fieldRfc, gbc_fieldRfc);

		lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 0;
		panelProveedor.add(lblNombre, gbc_lblNombre);

		lblTelefono = new JLabel("Teléfono");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_lblTelefono.gridx = 2;
		gbc_lblTelefono.gridy = 0;
		panelProveedor.add(lblTelefono, gbc_lblTelefono);

		fieldNombre = new JTextField();
		GridBagConstraints gbc_fieldNombre = new GridBagConstraints();
		gbc_fieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNombre.fill = GridBagConstraints.BOTH;
		gbc_fieldNombre.gridx = 1;
		gbc_fieldNombre.gridy = 1;
		panelProveedor.add(fieldNombre, gbc_fieldNombre);
		fieldNombre.setColumns(10);

		fieldTelefono = new JTextField();
		GridBagConstraints gbc_fieldTelefono = new GridBagConstraints();
		gbc_fieldTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_fieldTelefono.fill = GridBagConstraints.BOTH;
		gbc_fieldTelefono.gridx = 2;
		gbc_fieldTelefono.gridy = 1;
		panelProveedor.add(fieldTelefono, gbc_fieldTelefono);
		fieldTelefono.setColumns(10);

		lblProv = new JLabel("Proveedor:");
		GridBagConstraints gbc_lblProv = new GridBagConstraints();
		gbc_lblProv.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProv.insets = new Insets(0, 0, 0, 5);
		gbc_lblProv.gridx = 0;
		gbc_lblProv.gridy = 2;
		panelProveedor.add(lblProv, gbc_lblProv);

		lblProvDetalles = new JLabel(" ");
		GridBagConstraints gbc_lblProvDetalles = new GridBagConstraints();
		gbc_lblProvDetalles.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProvDetalles.gridwidth = 2;
		gbc_lblProvDetalles.insets = new Insets(0, 0, 0, 5);
		gbc_lblProvDetalles.gridx = 1;
		gbc_lblProvDetalles.gridy = 2;
		panelProveedor.add(lblProvDetalles, gbc_lblProvDetalles);

		lbCantidad = new JLabel("Cantidad");
		GridBagConstraints gbc_lbCantidad = new GridBagConstraints();
		gbc_lbCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lbCantidad.gridx = 0;
		gbc_lbCantidad.gridy = 6;
		add(lbCantidad, gbc_lbCantidad);

		cantidadSpin = new JSpinner();		
		cantidadSpin.setModel(cantidadModel);
		cantidadSpin.addChangeListener(c-> actualizarTotal());

		lbPrecio = new JLabel("Precio");
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lbPrecio.gridx = 1;
		gbc_lbPrecio.gridy = 6;
		add(lbPrecio, gbc_lbPrecio);

		GridBagConstraints gbc_cantidadSpin = new GridBagConstraints();
		gbc_cantidadSpin.fill = GridBagConstraints.BOTH;
		gbc_cantidadSpin.insets = new Insets(0, 0, 5, 5);
		gbc_cantidadSpin.gridx = 0;
		gbc_cantidadSpin.gridy = 7;
		add(cantidadSpin, gbc_cantidadSpin);

		precioField = new JTextField();
		GridBagConstraints gbc_precioField = new GridBagConstraints();
		gbc_precioField.fill = GridBagConstraints.BOTH;
		gbc_precioField.insets = new Insets(0, 0, 5, 5);
		gbc_precioField.gridx = 1;
		gbc_precioField.gridy = 7;
		add(precioField, gbc_precioField);


		precioField.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyPressed(KeyEvent e) {
				if(!(precioField.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				} 
				if(!precioField.getText().isEmpty()) {
					totalField.setText(String.valueOf(Double.valueOf(precioField.getText()) * (Integer)cantidadSpin.getValue()));					
				}
			}
		});

		lbFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lbFecha.gridx = 0;
		gbc_lbFecha.gridy = 8;
		add(lbFecha, gbc_lbFecha);

		lbTotal = new JLabel("Total");
		GridBagConstraints gbc_lbTotal = new GridBagConstraints();
		gbc_lbTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lbTotal.gridx = 1;
		gbc_lbTotal.gridy = 8;
		add(lbTotal, gbc_lbTotal);

		fechaField = new JTextField();
		fechaField.setText(LocalDate.now().toString());
		fechaField.setEditable(false);
		GridBagConstraints gbc_fechaField = new GridBagConstraints();
		gbc_fechaField.insets = new Insets(0, 0, 5, 5);
		gbc_fechaField.fill = GridBagConstraints.BOTH;
		gbc_fechaField.gridx = 0;
		gbc_fechaField.gridy = 9;
		add(fechaField, gbc_fechaField);
		fechaField.setColumns(10);

		totalField = new JTextField();
		totalField.setColumns(10);
		totalField.setEditable(false);
		GridBagConstraints gbc_totalField = new GridBagConstraints();
		gbc_totalField.insets = new Insets(0, 0, 5, 5);
		gbc_totalField.fill = GridBagConstraints.BOTH;
		gbc_totalField.gridx = 1;
		gbc_totalField.gridy = 9;
		add(totalField, gbc_totalField);

		GridBagConstraints gbc_confirmarButton = new GridBagConstraints();
		gbc_confirmarButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmarButton.gridwidth = 2;
		gbc_confirmarButton.gridx = 0;
		gbc_confirmarButton.gridy = 10;
		add(confirmarButton, gbc_confirmarButton);


		style(new Component[] {
				lbPrecio,
				lbCantidad,
				lbTotal,
				lbFecha,
				lblRfc,
				lblNombre,
				lblTelefono,
				lblCodigo,
				lblName,
				lblProv,
				lblProvDetalles,
				lblProd,
				lblProdDetalles,
				fieldNombre,
				totalField,
				fechaField,
				precioField,
				fieldTelefono,
				fieldCodigoP,
				fieldNombreP,
				fieldRfc,
				cantidadSpin,
				radByCode,
				radByName

		},
		new GridBagConstraints[] {
			gbc_lbPrecio,
			gbc_lbCantidad,
			gbc_lbTotal,
			gbc_lbFecha,
			gbc_lblRfc,
			gbc_lblNombre,
			gbc_lblTelefono,
			gbc_lblCodigo,
			gbc_lblName,
			gbc_lblProv,
			gbc_lblProvDetalles,
			gbc_lblProd,
			gbc_lblProdDetalles,
			gbc_fieldNombre,
			gbc_totalField,
			gbc_fechaField,
			gbc_precioField,
			gbc_fieldTelefono,
			gbc_fieldCodigoP,
			gbc_fieldNombreP,
			gbc_fieldRfc,
			gbc_cantidadSpin,
			gbc_radByCode,
			gbc_radByName
		});
		
		setActionCode();
	}



	private void actualizarTabla() { 
		model.update(Util.anyToString(listaDetalles, Detalles.class));
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
//		for(int i = 0; i < components.length; i++)
//			add(components[i], constraints[i]);
	}


	public JTextField getField() {
		return totalField;
	}


	public void guardarCompra() {
		listaDetalles.forEach(detalles -> 
			catalogo.get(detalles.getCodigo()).addStock(detalles.getCantidad())	
		);
		LocalDate fecha = LocalDate.now();
		Compra compra = new Compra(fecha, listaDetalles);
		historialCompra.add(compra);
		showTicket(listaDetalles);
		listaDetalles.clear();
		actualizarTabla();
	}


	public void buscarProductoCodigo(String codigo) {
		if (!catalogo.exists(codigo)) {
			visualizar("No existe este producto");
			clearProducto();
			fieldCodigoP.requestFocus();
			return;
		} else {
			precioField.requestFocus();
		}
		int detallesIndex = listaDetalles.indexOf(new DetallesCompra(codigo));
		if (detallesIndex != -1) {
			DetallesCompra detalles = listaDetalles.get(detallesIndex);
			cantidadSpin.setValue((Integer) detalles.getCantidad());
			precioField.setText(String.valueOf(detalles.getPrecio()));
			totalField.setText(String.valueOf(detalles.getTotal()));
		}
	}



	private void registrarProducto() {
		String codigo = fieldCodigoP.getText();
		double precio = Double.parseDouble(precioField.getText());
		int cantidad = (int) cantidadSpin.getValue();
		double total = precio * cantidad;
		Producto producto = catalogo.get(codigo);

		int detallesIndex = listaDetalles.indexOf(new DetallesCompra(codigo));

		if (!producto.valStockMax(cantidad)) {
			JOptionPane.showMessageDialog(null, "Supera el StockMaximo");
			return;
		}

		if (detallesIndex != -1) {
			// Si existe, actualiza la cantidad y el total
			DetallesCompra detalleExistente = listaDetalles.get(detallesIndex);
			detalleExistente.setCantidad(cantidad);
			detalleExistente.setPrecio(precio);
			detalleExistente.setTotal(total);
		} else {
			listaDetalles.add(new DetallesCompra(codigo, precio, cantidad));
		}
		
		actualizarTabla();
		clearCantidad();
		clearProducto();
		clearProveedor();
	}

	public void showTicket(ArrayList<DetallesCompra> lista) {
		JFrame ticketFrame = new JFrame();
		ticketFrame.setBounds(0, 0, 500, 750);
		JLabel textTicket = new JLabel(Util.generateTicket(lista, catalogo, headers), JLabel.CENTER);
		textTicket.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		ticketFrame.getContentPane().add(textTicket);
		ticketFrame.setVisible(true);
		ticketFrame.setResizable(false);
	}


	private void actualizarTotal() {
		String precioText = precioField.getText().trim(); 
		if (!precioText.isEmpty()) {
			double precio = Double.parseDouble(precioText);
			int cantidad = (int) cantidadSpin.getValue();
			double total = precio * cantidad;
			totalField.setText(String.valueOf(total)); 
		} else {
			totalField.setText(""); 
		}
	}

	
	private void autoCompleteFields(Producto producto, boolean code) {
		if(code) {
			fieldNombreP.setText(producto.getNombre());			
		} else {
			fieldCodigoP.setText(producto.getCodigoBarras());			
			SwingUtilities.invokeLater(()->{			
				fieldNombreP.setText(producto.getNombre());
			});
		}
		lblProdDetalles.setText(producto.getMainData());
		repaint();
	}
	private void autoCompleteFields(Proveedor proveedor) {
		fieldNombre.setText(proveedor.getNombre() + " " + proveedor.getApellido());			
		fieldTelefono.setText(proveedor.getTelefono());			
		lblProvDetalles.setText(proveedor.getDomicilio().toString());
		repaint();
	}
	

	public void setActionName() {
		fieldCodigoP.getDocument().removeDocumentListener(codeTextListener);
		fieldCodigoP.setEnabled(false);
		fieldNombreP.getDocument().addDocumentListener(nameTextListener);
		fieldNombreP.setEnabled(true);
		clearProducto();
	}
	public void setActionCode() {
		fieldCodigoP.getDocument().addDocumentListener(codeTextListener);
		fieldCodigoP.setEnabled(true);
		fieldNombreP.getDocument().removeDocumentListener(nameTextListener);
		fieldNombreP.setEnabled(false);
		clearProducto();
	}
	public void clearProducto() {
		fieldNombreP.setText("");
		fieldCodigoP.setText("");
		lblProdDetalles.setText("");
	}
	private void clearCantidad() {
		precioField.setText("0.0");
		cantidadSpin.setValue(0);
		totalField.setText("0.0");
	}
	private void clearProveedor() {
		fieldNombre.setText("");
		fieldRfc.setText("");
		fieldTelefono.setText("");
		lblProvDetalles.setText("");
	}
	

	
}

