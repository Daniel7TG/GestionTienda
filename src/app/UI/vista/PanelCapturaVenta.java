package app.UI.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.interfaces.Funcionable;
import app.modelos.Catalogo;
import app.modelos.DetallesVenta;
import app.modelos.Producto;
import app.util.Util;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PanelCapturaVenta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable productsTable;
	private JTextField codigoField;
	private DefaultTableModel model;
	
	private Catalogo catalogo;
	private List<DetallesVenta> lista;
	private JPanel topPanel;
	private JSpinner cantidadSpin;
	private JButton agregarButton;
	private JScrollPane tablePanel;
	
	private String[] columnNames = {
		"Codigo", "Precio", "Cantidad", "Total"
	};
	
	public PanelCapturaVenta(Catalogo catalogo) {
		setLayout(new BorderLayout(0, 0));
	
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
					e.consume();
				}
			}
		});
		
		agregarButton = new JButton("Agregar");
		GridBagConstraints gbc_agregarButton = new GridBagConstraints();
		gbc_agregarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_agregarButton.gridx = 2;
		gbc_agregarButton.gridy = 0;
		topPanel.add(agregarButton, gbc_agregarButton);
		
		tablePanel = new JScrollPane();
		add(tablePanel, BorderLayout.CENTER);
		
		productsTable = new JTable();
		model = new DefaultTableModel(Util.detallesVentaToStringMat(lista), columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablePanel.setViewportView(productsTable);
	}
	
	
	public void agregarProducto(String codigo) {
		Producto producto = catalogo.get(codigo);
		if(producto != null) {
			DetallesVenta detalles = new DetallesVenta(codigo, producto.getPrecioVenta(), (int)cantidadSpin.getValue());
			agregarList(detalles, producto);
		} else {
			JOptionPane.showMessageDialog(null, "No existe el producto");
		}
	}
	
	
	public void agregarList(DetallesVenta detalles, Producto producto) {
		if(lista.contains(detalles)) {
			int cantidadTotal = lista.get(lista.indexOf(detalles)).getCantidad() + detalles.getCantidad();
			if(producto.getStockActual() - cantidadTotal > producto.getStockMinimo()) {
				detalles.setCantidad(cantidadTotal);				
			}
		} else {
			lista.add(detalles);
		}
		updateTable();
	}
	
	
	public void guardarVenta() {
		
	}
	
	
	public void updateTable() {
		model.setDataVector(Util.detallesVentaToStringMat(lista), columnNames);
	}
	
	
	

}
