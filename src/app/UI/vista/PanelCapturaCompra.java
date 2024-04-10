package app.UI.vista;

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

import app.interfaces.Funcionable;
import app.modelos.Catalogo;
import app.modelos.Compra;
import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.modelos.Producto;
import app.modelos.Proveedores;
import app.util.Util;

import java.time.*;

import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.visualizar;

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

public class PanelCapturaCompra extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<DetallesCompra> listaDetalles;

	private JTextField codigoField;
	private JTextField totalField;
	private JTable table;

	private JTextField precioSpin;
	private JSpinner cantidadSpin;			//private JTextField cantidadSpin; 
	private JLabel fechaLabel;
	private JLabel lbCodigo;
	private JLabel lbPrecio;
	private JLabel lbCantidad;
	private JLabel lbTotal;


	private Proveedores proveedores;
	private Catalogo catalogo;
	private Insets separation;
	private Font fontLabel;
	private Font fontFunc;

	private JScrollPane tableScroll;
	private DefaultTableModel model;
	private String[] columnNames = {"Codigo",
			"Cantidad",
			"Precio", 
	"Total"};
	private List<String> headers = List.of("Registro de compra", LocalDate.now().toString());
	private JButton confirmarButton;

	public PanelCapturaCompra(Catalogo catalogo, Proveedores proveedores) {

		this.catalogo = catalogo;
		this.proveedores = proveedores;
		
		listaDetalles = new ArrayList<DetallesCompra>();
		separation = new Insets(10, 5, 10, 5);
		fontLabel = new Font("Montserrat", Font.PLAIN, 16);
		fontFunc = new Font("Montserrat", Font.PLAIN, 13);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{182, 86, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 3.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 9.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		codigoField = new JTextField();

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
		//    */

		codigoField.setColumns(10);
		GridBagConstraints gbc_codigoField = new GridBagConstraints();
		gbc_codigoField.insets = new Insets(0, 0, 5, 5);
		gbc_codigoField.fill = GridBagConstraints.BOTH;
		gbc_codigoField.gridx = 0;
		gbc_codigoField.gridy = 2;
		add(codigoField, gbc_codigoField);

		precioSpin = new JTextField();
		GridBagConstraints gbc_precioSpin = new GridBagConstraints();
		gbc_precioSpin.fill = GridBagConstraints.BOTH;
		gbc_precioSpin.insets = new Insets(0, 0, 5, 5);
		gbc_precioSpin.gridx = 1;
		gbc_precioSpin.gridy = 2;
		add(precioSpin, gbc_precioSpin);

		cantidadSpin = new JSpinner();			//cantidadSpin = new JTextField();
		SpinnerNumberModel cantidadModel = new SpinnerNumberModel(1, 1, 1000, 1);
		cantidadSpin.setModel(cantidadModel);
		///////////
		cantidadSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				actualizarTotal();
			}
		});

		//////////////
		GridBagConstraints gbc_cantidadSpin = new GridBagConstraints();
		gbc_cantidadSpin.fill = GridBagConstraints.BOTH;
		gbc_cantidadSpin.insets = new Insets(0, 0, 5, 5);
		gbc_cantidadSpin.gridx = 0;
		gbc_cantidadSpin.gridy = 4;
		add(cantidadSpin, gbc_cantidadSpin);

		totalField = new JTextField();
		totalField.setColumns(10);
		totalField.setEditable(false);
		GridBagConstraints gbc_totalField = new GridBagConstraints();
		gbc_totalField.insets = new Insets(0, 0, 5, 5);
		gbc_totalField.fill = GridBagConstraints.BOTH;
		gbc_totalField.gridx = 1;
		gbc_totalField.gridy = 4;
		add(totalField, gbc_totalField);

		fechaLabel = new JLabel(LocalDate.now().toString());
		GridBagConstraints gbc_fechaLabel = new GridBagConstraints();
		gbc_fechaLabel.gridx = 0;
		gbc_fechaLabel.gridwidth = 2;
		gbc_fechaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLabel.gridy = 0;
		add(fechaLabel, gbc_fechaLabel);

		lbCodigo = new JLabel("Còdigo");
		GridBagConstraints gbc_lbCodigo = new GridBagConstraints();
		gbc_lbCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lbCodigo.gridx = 0;
		gbc_lbCodigo.gridy = 1;
		add(lbCodigo, gbc_lbCodigo);

		lbPrecio = new JLabel("Precio");
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lbPrecio.gridx = 1;
		gbc_lbPrecio.gridy = 1;
		add(lbPrecio, gbc_lbPrecio);

		lbCantidad = new JLabel("Cantidad");
		GridBagConstraints gbc_lbCantidad = new GridBagConstraints();
		gbc_lbCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lbCantidad.gridx = 0;
		gbc_lbCantidad.gridy = 3;
		add(lbCantidad, gbc_lbCantidad);

		lbTotal = new JLabel("Total");
		GridBagConstraints gbc_lbTotal = new GridBagConstraints();
		gbc_lbTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lbTotal.gridx = 1;
		gbc_lbTotal.gridy = 3;
		add(lbTotal, gbc_lbTotal);


		Object[][] data = Util.anyToString(listaDetalles);
		model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		model.setDataVector(data, columnNames);				
		table = new JTable(model);
		table.setRowHeight(30);
		tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_tableScroll = new GridBagConstraints();
		gbc_tableScroll.fill = GridBagConstraints.BOTH;
		gbc_tableScroll.gridy = 0;
		gbc_tableScroll.gridx = 2;
		gbc_tableScroll.gridheight = 6;
		add(tableScroll, gbc_tableScroll);




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

		confirmarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				registrarProducto();
			}
		});

		GridBagConstraints gbc_confirmarButton = new GridBagConstraints();
		gbc_confirmarButton.anchor = GridBagConstraints.EAST;
		gbc_confirmarButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmarButton.gridx = 0;
		gbc_confirmarButton.gridy = 5;
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
				totalField,
				precioSpin,
				cantidadSpin,
				fechaLabel,
				lbCodigo,
				lbPrecio,
				lbCantidad,
				lbTotal,	
		},
				new GridBagConstraints[] {
						gbc_codigoField,
						gbc_totalField,
						gbc_precioSpin,
						gbc_cantidadSpin,
						gbc_fechaLabel,
						gbc_lbCodigo,
						gbc_lbPrecio,
						gbc_lbCantidad,
						gbc_lbTotal,	
		});
	}



	private void actualizarTabla() {
		Object[][] data = Util.anyToString(listaDetalles);
		DefaultTableModel newModel = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(newModel);
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
		
		double total = listaDetalles.stream().mapToDouble(DetallesCompra::getTotal).sum();
		listaDetalles.forEach(detalles -> 
			catalogo.get(detalles.getCodigo()) 
			.addStock(detalles.getCantidad())	
		);
		LocalDate fecha = LocalDate.now();
		Compra compra = new Compra(total, fecha.toString(), listaDetalles);
		proveedores.add(compra);
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
			listaDetalles.add(new DetallesCompra(codigo, total, precio, cantidad));
		}

		actualizarTabla();
		limpiarCampos();

	}

	public void showTicket(ArrayList<DetallesCompra> lista) {
		JFrame ticketFrame = new JFrame();
		ticketFrame.setBounds(0, 0, 500, 750);
		JLabel textTicket = new JLabel(Util.generateTicketCompra(lista, catalogo, headers), JLabel.CENTER);
		textTicket.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		ticketFrame.add(textTicket);
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

