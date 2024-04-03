package app.UI.vista;

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

import app.modelos.Catalogo;
import app.modelos.Clientes;
import app.modelos.DetallesVenta;
import app.modelos.Producto;
import app.modelos.Venta;
import app.util.Util;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PanelCapturaVenta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable productsTable;
	private JTextField codigoField;
	private DefaultTableModel model;
	
	private Clientes clientes;
	private Catalogo catalogo;
	private ArrayList<DetallesVenta> lista;
	private JPanel topPanel;
	private JSpinner cantidadSpin;
	private JButton agregarButton;
	private JScrollPane tablePanel;
	
	private String[] columnNames = {
		"Codigo", "Cantidad", "Precio", "Total"
	};
	
	public PanelCapturaVenta(Catalogo catalogo, Clientes clientes) {
		setLayout(new BorderLayout(0, 0));
		this.clientes = clientes;
		this.catalogo = catalogo;
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
					agregarProducto(codigoField.getText());
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
		add(tablePanel, BorderLayout.CENTER);
		
		productsTable = new JTable();
		model = new DefaultTableModel(Util.anyToString(lista), columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		productsTable.setModel(model);
		tablePanel.setViewportView(productsTable);
		
	}
	
	
	public void agregarProducto(String codigo) {
		Producto producto = catalogo.get(codigo);
		
		if(producto == null) {
			JOptionPane.showMessageDialog(null, "No existe el producto");
		}
		else if(producto.getStockActual() <= producto.getStockMinimo()) {
			JOptionPane.showMessageDialog(null, "No hay existencias");
		} else {
			DetallesVenta detalles = new DetallesVenta(codigo, producto.getPrecioVenta(), (int)cantidadSpin.getValue());
			agregarList(detalles, producto);
		}
	}
	
	
	public void agregarList(DetallesVenta detalles, Producto producto) {
		if(lista.contains(detalles)) {
			int cantidadTotal = lista.get(lista.indexOf(detalles)).getCantidad() + detalles.getCantidad();
			if(producto.getStockActual() - cantidadTotal >= producto.getStockMinimo()) {
				lista.get(lista.indexOf(detalles)).setCantidad(cantidadTotal);
			}else {
				JOptionPane.showMessageDialog(null, "No hay existencias");
			}
		} else {
			lista.add(detalles);
		}
		updateTable();
	}
	
	
	public void guardarVenta() {
		if(lista.size() == 0) return;
		double total = lista.stream().mapToDouble(detalles -> detalles.getTotal()).sum();
		LocalDate fecha = LocalDate.now();
		Venta venta = new Venta(total, fecha.toString(), lista);
		lista.forEach(detalle -> {
			Producto p = catalogo.get(detalle.getCodigo());
			p.setStockActual(p.getStockActual() - detalle.getCantidad());
		});
			
		clientes.add(venta);
		lista.clear();
		updateTable();
	}
	
	
	public void showTicket(ArrayList<DetallesVenta> lista) {
		JFrame ticketFrame = new JFrame();
		ticketFrame.setBounds(0, 0, 300, 700);
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		JLabel textTicket = new JLabel(generateTicket(lista), JLabel.CENTER);
		textTicket.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		System.out.println(generateTicket(lista));
		ticketFrame.setLayout(layout);
		ticketFrame.add(textTicket);
		ticketFrame.setVisible(true);
		ticketFrame.setResizable(false);
	}
	
	private static String centerText( String texto, int espacio ) {
		int tlength = texto.length();
		
		if(tlength > espacio) return "error";
		
		int margin = (espacio - tlength) / 2;
		
		String centeredText = "<p>" +
				" ".repeat(margin) + 
				texto + 
				" ".repeat(margin) + 
				( margin % 2 == 0 ? "" : " " );
		
		return centeredText + "<p/>";
	}
	private static String formatProduct(DetallesVenta details, int space) {
		String finalText = "";
		StringBuilder textoLeft = new StringBuilder(details.getCodigo());
		int middle = space/2;
		
		if(textoLeft.length() > middle) {
			finalText = textoLeft.substring(0, middle) + "  ";
		}
		else {
			finalText = String.format( ("%-" + (middle + 2) + "s"), textoLeft.toString());
		}				
		finalText += String.format("%" + (middle-2) + "s", "$" + details.getTotal()) + "<br/>";	
		return finalText;	
	}
	public static String generateTicket(ArrayList<DetallesVenta> detailsList) {
		StringBuilder ticket = new StringBuilder("<html>");
		ticket.append( centerText("Tienda", 35) );
		ticket.append( centerText("Tel: 55-5555-5555", 35) );
		ticket.append( centerText("Suc. CDMX", 35) );
		
		
		detailsList.forEach(detail -> ticket.append(formatProduct(detail, 35)));
		return String.valueOf(ticket) + "<html/>";
	}
	public void updateTable() {
		model.setDataVector(Util.anyToString(lista), columnNames);
	}
	
	
	

}
