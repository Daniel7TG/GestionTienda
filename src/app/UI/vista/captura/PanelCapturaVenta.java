package app.UI.vista.captura;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import app.abstractClasses.Detalles;
import app.interfaces.Service;
import app.modelos.*;
import app.util.TableModel;
import app.util.Util;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PanelCapturaVenta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable productsTable;
	private JTextField codigoField;
	private TableModel model;
	
	private Usuario user;
	private Service<Venta> clientes;
	private Service<Producto> catalogo;
	private Service<Cliente> clientesService;
	private List<DetallesVenta> lista;
	
	private JPanel topPanel;
	private JSpinner cantidadSpin;
	private JButton agregarButton;
	private JScrollPane tablePanel;
	
	private String[] columnNames = {
		"Codigo", "Cantidad", "Precio", "Total"
	};
	private String[] headers = new String[]{"Tienda", "Sucursal Zitacuaro", "Telefono: 55-5555-5555", "RFC: AAAA0000ASD",
			LocalDate.now().toString() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))};
	
	public PanelCapturaVenta(Service<Producto> catalogo, Service<Venta> clientes, Service<Cliente> clientesService, Usuario user) {
		setLayout(new BorderLayout(0, 0));
		this.clientes = clientes;
		this.catalogo = catalogo;
		this.clientesService = clientesService;
		this.user = user;
		
		lista = new ArrayList<>();
		
		topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {0, 0, 0};
		gbl_topPanel.rowHeights = new int[] {0};
		gbl_topPanel.columnWeights = new double[]{2.0, 6.0, 2.0};
		gbl_topPanel.rowWeights = new double[]{0.0};
		topPanel.setLayout(gbl_topPanel);
		
		cantidadSpin = new JSpinner();
		GridBagConstraints gbc_cantidadSpin = new GridBagConstraints();
		gbc_cantidadSpin.fill = GridBagConstraints.HORIZONTAL;
		gbc_cantidadSpin.insets = new Insets(0, 0, 0, 5);
		gbc_cantidadSpin.gridx = 0;
		gbc_cantidadSpin.gridy = 0;
		topPanel.add(cantidadSpin, gbc_cantidadSpin);
		SpinnerNumberModel cantidadModel = new SpinnerNumberModel(1, 1, 1000, 1);
		cantidadSpin.setModel(cantidadModel);
		cantidadSpin.setEditor(new JSpinner.DefaultEditor(cantidadSpin));
		
		codigoField = new JTextField();
		GridBagConstraints gbc_codigoField = new GridBagConstraints();
		gbc_codigoField.insets = new Insets(0, 0, 0, 5);
		gbc_codigoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codigoField.gridx = 1;
		gbc_codigoField.gridy = 0;
		topPanel.add(codigoField, gbc_codigoField);
		codigoField.setColumns(10);
		codigoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
//				(int)e.getKeyChar() != 8, 8 es el boton de borrar
				if(codigoField.getText().length() > 12 & (int)e.getKeyChar() != 8 ) {
					addToList(codigoField.getText());
					codigoField.setText("");
					e.consume();
				}
			}
		});
		
		agregarButton = new JButton("Preview Ticket");
		GridBagConstraints gbc_agregarButton = new GridBagConstraints();
		gbc_agregarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_agregarButton.gridx = 2;
		gbc_agregarButton.gridy = 0;
		topPanel.add(agregarButton, gbc_agregarButton);
		agregarButton.addActionListener(e -> showTicket(lista));
		
		
		tablePanel = new JScrollPane();
		productsTable = new JTable();
		model = new TableModel(productsTable, lista, columnNames);
		productsTable.setModel(model);
		tablePanel.setViewportView(productsTable);

		add(tablePanel, BorderLayout.CENTER);
	}
	
	
	public void addToList(String codigo) {
		Producto producto = catalogo.get(codigo);
		if(producto == null) {
			JOptionPane.showMessageDialog(null, "No existe el producto");
		}
		else if(producto.getStockActual() <= producto.getStockMinimo()) {
			JOptionPane.showMessageDialog(null, "No hay existencias");
		} else {
			DetallesVenta detalles = new DetallesVenta(codigo, producto.getPrecioVenta(), (int)cantidadSpin.getValue());
			addToList(detalles, producto);
		}
	}
	
	
	public void addToList(DetallesVenta det, Producto prod) {
		int index = lista.indexOf(det);
		if(index != -1) {
			DetallesVenta detExst = lista.get(index);
			int cantTotal = detExst.getCantidad() + det.getCantidad();
			if (prod.valStockMin(cantTotal)) detExst.setCantidad(cantTotal);
			else JOptionPane.showMessageDialog(null, "No hay existencias");
		} else {
			lista.add(det);
		}
		updateTable();
	}
	
	
	public void guardarVenta() {
		if(lista.isEmpty()) return;

		String cliente = JOptionPane.showInputDialog("Numero de telefono del cliente: ");
		if(cliente.length() != 10 | !clientesService.exists(cliente)) cliente = "SIN CLIENTE";

		LocalDate fecha = LocalDate.now();
		Venta venta = new Venta(fecha, lista, user.getUserName(), cliente);

		lista.forEach(detalle -> 
			catalogo.get(detalle.getCodigo()).subtractStock(detalle.getCantidad())
		);
		clientes.save(venta);
		showTicket(lista);
		lista.clear();
		updateTable();
	}
	
	
	public void showTicket(List<DetallesVenta> lista) {
		JFrame ticketFrame = new JFrame();
		ticketFrame.setBounds(0, 0, 500, 750);
		JLabel textTicket = new JLabel(Util.generateTicket(lista, catalogo, headers), JLabel.CENTER);
		textTicket.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		ticketFrame.add(textTicket);
		ticketFrame.setVisible(true);
		ticketFrame.setResizable(false);
	}
	

	public void updateTable() {
		model.update(lista.stream().map(Detalles::toRow).toArray(Object[][]::new));
	}

}
