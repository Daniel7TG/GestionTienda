package app.UI.vista.captura;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;

public class PanelCapturaCompra extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<DetallesCompra> listaDetalles;

	private JTextField codigoField;
	private JTextField totalField;
	private JTable productsTable;

	private JTextField fechaField;
	private JTextField precioSpin;
	private JSpinner cantidadSpin;			//private JTextField cantidadSpin; 
	private JLabel lbCodigo;
	private JLabel lbPrecio;
	private JLabel lbCantidad;
	private JLabel lbTotal;


	private HistorialCompra historialCompra;
	private Proveedores proveedores;
	private Catalogo catalogo;
	private Insets separation;
	private Font fontLabel;
	private Font fontFunc;

	private TableModel model;
	private Object[][] data = new Object[0][0];
	private String[] columnNames = {"Codigo",
			"Cantidad",
			"Precio", 
	"Total"};
	private List<String> headers = List.of("Registro de compra", LocalDate.now().toString());
	private JButton confirmarButton;

	private JScrollPane tablePanel;
	private JLabel lbFecha;
	private JLabel lbProveedor;
	private JComboBox<String> rfcBox;

	public PanelCapturaCompra(Catalogo catalogo, HistorialCompra historialCompra, Proveedores proveedores) {

		this.catalogo = catalogo;
		this.historialCompra = historialCompra;
		this.proveedores = proveedores;

		listaDetalles = new ArrayList<DetallesCompra>();
		separation = new Insets(10, 5, 10, 5);
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{182, 86, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 3.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 9.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		tablePanel = new JScrollPane();
		productsTable = new JTable();
		model = new TableModel(productsTable, data, columnNames);
		productsTable.setModel(model);
		tablePanel.setViewportView(productsTable);

		GridBagConstraints gbc_tableScroll = new GridBagConstraints();
		gbc_tableScroll.fill = GridBagConstraints.BOTH;
		gbc_tableScroll.gridy = 0;
		gbc_tableScroll.gridx = 2;
		gbc_tableScroll.gridheight = 7;
		add(tablePanel, gbc_tableScroll);
		
		codigoField = new TextFieldSuggestion(Util.getCodeFilter(catalogo));
		codigoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char[] texto = codigoField.getText().toCharArray();
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				if(texto.length == 13) {
					buscarProductoCodigo(codigoField.getText());
					e.consume();
				}
			}
		});

		codigoField.setColumns(10);
		GridBagConstraints gbc_codigoField = new GridBagConstraints();
		gbc_codigoField.insets = new Insets(0, 0, 5, 5);
		gbc_codigoField.fill = GridBagConstraints.BOTH;
		gbc_codigoField.gridx = 0;
		gbc_codigoField.gridy = 1;
		add(codigoField, gbc_codigoField);

		precioSpin = new JTextField();
		GridBagConstraints gbc_precioSpin = new GridBagConstraints();
		gbc_precioSpin.fill = GridBagConstraints.BOTH;
		gbc_precioSpin.insets = new Insets(0, 0, 5, 5);
		gbc_precioSpin.gridx = 1;
		gbc_precioSpin.gridy = 1;
		add(precioSpin, gbc_precioSpin);

		cantidadSpin = new JSpinner();		
		SpinnerNumberModel cantidadModel = new SpinnerNumberModel(1, 1, 1000, 1);
		cantidadSpin.setModel(cantidadModel);
		cantidadSpin.addChangeListener(c-> actualizarTotal());

		lbProveedor = new JLabel("Rfc del Proveedor");
		GridBagConstraints gbc_lbProveedor = new GridBagConstraints();
		gbc_lbProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_lbProveedor.gridx = 1;
		gbc_lbProveedor.gridy = 2;
		add(lbProveedor, gbc_lbProveedor);

		GridBagConstraints gbc_cantidadSpin = new GridBagConstraints();
		gbc_cantidadSpin.fill = GridBagConstraints.BOTH;
		gbc_cantidadSpin.insets = new Insets(0, 0, 5, 5);
		gbc_cantidadSpin.gridx = 0;
		gbc_cantidadSpin.gridy = 3;
		add(cantidadSpin, gbc_cantidadSpin);

		lbCodigo = new JLabel("Còdigo");
		GridBagConstraints gbc_lbCodigo = new GridBagConstraints();
		gbc_lbCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lbCodigo.gridx = 0;
		gbc_lbCodigo.gridy = 0;
		add(lbCodigo, gbc_lbCodigo);

		lbPrecio = new JLabel("Precio");
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lbPrecio.gridx = 1;
		gbc_lbPrecio.gridy = 0;
		add(lbPrecio, gbc_lbPrecio);

		lbCantidad = new JLabel("Cantidad");
		GridBagConstraints gbc_lbCantidad = new GridBagConstraints();
		gbc_lbCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lbCantidad.gridx = 0;
		gbc_lbCantidad.gridy = 2;
		add(lbCantidad, gbc_lbCantidad);




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

		rfcBox = new JComboBox<String>();
		proveedores.getList().stream().map(Proveedor::getRfc).forEach(rfcBox::addItem);
		
		GridBagConstraints gbc_rfcBox = new GridBagConstraints();
		gbc_rfcBox.insets = new Insets(0, 0, 5, 5);
		gbc_rfcBox.fill = GridBagConstraints.BOTH;
		gbc_rfcBox.gridx = 1;
		gbc_rfcBox.gridy = 3;
		add(rfcBox, gbc_rfcBox);

		lbFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lbFecha.gridx = 0;
		gbc_lbFecha.gridy = 4;
		add(lbFecha, gbc_lbFecha);

		lbTotal = new JLabel("Total");
		GridBagConstraints gbc_lbTotal = new GridBagConstraints();
		gbc_lbTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lbTotal.gridx = 1;
		gbc_lbTotal.gridy = 4;
		add(lbTotal, gbc_lbTotal);

		fechaField = new JTextField();
		fechaField.setText(LocalDate.now().toString());
		fechaField.setEditable(false);
		GridBagConstraints gbc_fechaField = new GridBagConstraints();
		gbc_fechaField.insets = new Insets(0, 0, 5, 5);
		gbc_fechaField.fill = GridBagConstraints.BOTH;
		gbc_fechaField.gridx = 0;
		gbc_fechaField.gridy = 5;
		add(fechaField, gbc_fechaField);
		fechaField.setColumns(10);

		totalField = new JTextField();
		totalField.setColumns(10);
		totalField.setEditable(false);
		GridBagConstraints gbc_totalField = new GridBagConstraints();
		gbc_totalField.insets = new Insets(0, 0, 5, 5);
		gbc_totalField.fill = GridBagConstraints.BOTH;
		gbc_totalField.gridx = 1;
		gbc_totalField.gridy = 5;
		add(totalField, gbc_totalField);

		GridBagConstraints gbc_confirmarButton = new GridBagConstraints();
		gbc_confirmarButton.gridwidth = 2;
		gbc_confirmarButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmarButton.gridx = 0;
		gbc_confirmarButton.gridy = 6;
		add(confirmarButton, gbc_confirmarButton);


		precioSpin.addKeyListener(new KeyAdapter() { //calcula el total cada que se cambia el valor //el mismo para cantidadSpin o text
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(precioSpin.getText() + e.getKeyChar()).matches("^[0-9]+(\\.[0-9]*)?$") ) {
					e.consume();
				} 
				if(!precioSpin.getText().isEmpty()) {
					totalField.setText(String.valueOf(Double.valueOf(precioSpin.getText()) * (Integer)cantidadSpin.getValue()));					
				}
			}
		});


		style(new Component[] {
				codigoField,
				rfcBox,
				totalField,
				precioSpin,
				cantidadSpin,
				lbFecha,
				fechaField,
				lbProveedor,
				lbCodigo,
				lbPrecio,
				lbCantidad,
				lbTotal,	
		},
				new GridBagConstraints[] {
						gbc_codigoField,
						gbc_rfcBox,
						gbc_totalField,
						gbc_precioSpin,
						gbc_cantidadSpin,
						gbc_lbFecha,
						gbc_fechaField,
						gbc_lbProveedor,
						gbc_lbCodigo,
						gbc_lbPrecio,
						gbc_lbCantidad,
						gbc_lbTotal,	
		});
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
		for(int i = 0; i < components.length; i++)
			add(components[i], constraints[i]);
	}


	public JTextField getField() {
		return totalField;
	}


	public void guardarCompra() {

		//		double total = listaDetalles.stream().mapToDouble(DetallesCompra::getTotal).sum();
		listaDetalles.forEach(detalles -> 
		catalogo.get(detalles.getCodigo()) 
		.addStock(detalles.getCantidad())	
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
			limpiarCampos();
			codigoField.requestFocus();
			return;
		} else {
			precioSpin.requestFocus();
		}

		int detallesIndex = listaDetalles.indexOf(new DetallesCompra(codigo));
		if (detallesIndex != -1) {
			DetallesCompra detalles = listaDetalles.get(detallesIndex);
			cantidadSpin.setValue((Integer) detalles.getCantidad());
			precioSpin.setText(String.valueOf(detalles.getPrecio()));
			totalField.setText(String.valueOf(detalles.getTotal()));
		}
	}



	private void registrarProducto() {
		String codigo = codigoField.getText();
		double precio = Double.parseDouble(precioSpin.getText());
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
		limpiarCampos();

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


	private void limpiarCampos() {
		codigoField.setText("");
		precioSpin.setText("");
		cantidadSpin.setValue(0);
		totalField.setText("");
	}

	private void actualizarTotal() {
		String precioText = precioSpin.getText().trim(); 
		if (!precioText.isEmpty()) {
			double precio = Double.parseDouble(precioText);
			int cantidad = (int) cantidadSpin.getValue();
			double total = precio * cantidad;
			totalField.setText(String.valueOf(total)); 
		} else {
			totalField.setText(""); 
		}
	}

}

