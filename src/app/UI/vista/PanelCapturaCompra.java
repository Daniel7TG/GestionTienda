package app.UI.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import app.interfaces.Funcionable;
import app.modelos.Catalogo;
import app.modelos.Compra;
import app.modelos.DetallesCompra;
import app.modelos.Producto;
import app.util.Util;

import java.time.*;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PanelCapturaCompra extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<DetallesCompra> listaDetalles;

	private JTextField codigoField;
	private JTextField totalField;
	private JTable table;

	private JTextField precioSpin;
	private JSpinner cantidadSpin;
	private JLabel fechaLabel;
	private JLabel lbCodigo;
	private JLabel lbPrecio;
	private JLabel lbCantidad;
	private JLabel lbTotal;

	
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
	
	public PanelCapturaCompra(Catalogo catalogo) {

		this.catalogo = catalogo;
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

		cantidadSpin = new JSpinner();
		GridBagConstraints gbc_cantidadSpin = new GridBagConstraints();
		gbc_cantidadSpin.fill = GridBagConstraints.BOTH;
		gbc_cantidadSpin.insets = new Insets(0, 0, 5, 5);
		gbc_cantidadSpin.gridx = 0;
		gbc_cantidadSpin.gridy = 4;
		add(cantidadSpin, gbc_cantidadSpin);

		totalField = new JTextField();
		totalField.setColumns(10);
		GridBagConstraints gbc_totalField = new GridBagConstraints();
		gbc_totalField.insets = new Insets(0, 0, 5, 5);
		gbc_totalField.fill = GridBagConstraints.BOTH;
		gbc_totalField.gridx = 1;
		gbc_totalField.gridy = 4;
		add(totalField, gbc_totalField);

		fechaLabel = new JLabel("New label");
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

		
		String[][] data = Util.detallesToStringMat(listaDetalles);
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

		
		precioSpin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!precioSpin.getText().isEmpty())
				totalField.setText(String.valueOf(Double.valueOf(precioSpin.getText()) * (Integer)cantidadSpin.getValue()));
			}
		});
		cantidadSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(!precioSpin.getText().isEmpty())
					totalField.setText(String.valueOf(Double.valueOf(precioSpin.getText()) * (Integer)cantidadSpin.getValue()));				
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


	public void getDetalles() {
		
		double precio = Double.valueOf(precioSpin.getText());
		int cantidad= (int)cantidadSpin.getValue();
		String codigo = (String) codigoField.getText();
		double total = precio*cantidad;

		Producto p = catalogo.get(codigo);
		if(p != null) {
			List<DetallesCompra> detallesExistente = listaDetalles.stream().filter(d -> d.getCodigo().equals(codigo)).collect(Collectors.toList());
			
			if(!detallesExistente.isEmpty()) {
				if(p.getStockMaximo() >= cantidad + detallesExistente.get(0).getCantidad()) {					
				detallesExistente.get(0).setCantidad(detallesExistente.get(0).getCantidad() + cantidad);
				detallesExistente.get(0).setTotal(detallesExistente.get(0).getCantidad() * precio);
				}
			}
			else {
				if(p.getStockMaximo() >= cantidad) listaDetalles.add(new DetallesCompra(codigo, total, precio, cantidad));							
			}
			model.setDataVector(Util.detallesToStringMat(listaDetalles), columnNames);				
		} else {
			JOptionPane.showMessageDialog(null, "no existe el producto");
		}
		
	}
	
	public void guardarCompra() {
		
		double total = listaDetalles.stream().mapToDouble(detalles -> detalles.getTotal()).sum();
		listaDetalles.forEach(detalles -> {
			Producto p = catalogo.get(detalles.getCodigo()); 
			p.setStockActual(p.getStockActual() + detalles.getCantidad());
			
		});
		LocalDate fecha = LocalDate.now();
		Compra compra = new Compra(total, fecha.toString(), listaDetalles);
		listaDetalles.clear();
		model.setDataVector(Util.detallesToStringMat(listaDetalles), columnNames);				

		System.out.println(compra.toString());
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
	
	


}
