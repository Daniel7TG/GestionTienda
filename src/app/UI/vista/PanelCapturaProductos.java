package app.UI.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Insets;
import java.awt.KeyboardFocusManager;

import app.interfaces.Funcionable;
import app.modelos.Catalogo;
import app.modelos.Producto;
import app.util.Util;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;


import static app.dao.DaoUtility.*;
import static app.enums.ColorStyles.*;
import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.*;
import static app.util.Util.*;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;



public class PanelCapturaProductos extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel imageLabel;
	private JTextField codigoBarrasField;
	private JTextField nombreField;
	private JTextField marcaField;
	private JTextField contenidoField;
	private JTextField descripcionField;
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

	private Catalogo catalogo;
	private DefaultTableModel model;
	private String[] columnNames = {"Codigo",
				"Nombre",
				"Marca",
				"Contenido",
				"Maximo",
				"Minimo",
				"Tipo",
				"Medida",
				"Presentacion",
				"Descripcion",};
	/**
	 * Create the panel.
	 * @param catalogo 
	 */
	@SuppressWarnings("serial")
	public PanelCapturaProductos(Catalogo catalogo) {
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
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 3.0, 15.0};
		setLayout(gridBagLayout);
		
		JLabel codigoDeBarras = new JLabel("Codigo de Barras");
		GridBagConstraints gbc_codigoDeBarras = new GridBagConstraints();
		gbc_codigoDeBarras.insets = new Insets(0, 0, 5, 5);
		gbc_codigoDeBarras.fill = GridBagConstraints.VERTICAL;
		gbc_codigoDeBarras.anchor = GridBagConstraints.EAST;
		gbc_codigoDeBarras.gridx = 0;
		gbc_codigoDeBarras.gridy = 0;
		add(codigoDeBarras, gbc_codigoDeBarras);
		
		codigoBarrasField = new JTextField();
		codigoBarrasField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char[] texto = codigoBarrasField.getText().toCharArray();
				
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				if(texto.length > 12) {
					if(catalogo.exists(codigoBarrasField.getText())) {
						visualizar("Ya existe el producto");
						codigoBarrasField.setText("");
					} else {
						nombreField.requestFocus();						
					}
					e.consume();
				}				
			}
		});
		
		
		GridBagConstraints gbc_codigoBarrasField = new GridBagConstraints();
		gbc_codigoBarrasField.insets = new Insets(0, 0, 5, 5);
		gbc_codigoBarrasField.fill = GridBagConstraints.BOTH;
		gbc_codigoBarrasField.gridx = 1;
		gbc_codigoBarrasField.gridy = 0;
		add(codigoBarrasField, gbc_codigoBarrasField);
		codigoBarrasField.setColumns(10);
		
		JLabel nombre = new JLabel("Nombre");
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.fill = GridBagConstraints.VERTICAL;
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 2;
		gbc_nombre.gridy = 0;
		add(nombre, gbc_nombre);
		
		
		nombreField = new JTextField();
		GridBagConstraints gbc_nombreField = new GridBagConstraints();
		gbc_nombreField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreField.fill = GridBagConstraints.BOTH;
		gbc_nombreField.gridx = 3;
		gbc_nombreField.gridy = 0;
		add(nombreField, gbc_nombreField);
		nombreField.setColumns(10);
		
		nombreField.addActionListener(e->{
			String gotNombre = nombreField.getText();
			if(gotNombre.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insertar Nombre");
			} else{
				nombreField.setText(capitalize(nombreField.getText()));
				nombreField.transferFocus();
			}
		});
		
		
		
		imageLabel = new JLabel("");
//		imageLabel.setSize(new Dimension(200, 200));
		imageLabel.setBounds(0, 0, 200, 200);
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_imageLabel.fill = GridBagConstraints.VERTICAL;
		gbc_imageLabel.gridheight = 5;
		gbc_imageLabel.gridx = 4;
		gbc_imageLabel.gridy = 1;
		add(imageLabel, gbc_imageLabel);
		
		JButton imageButton = new JButton("Cambiar Imagen");
		imageButton.setFocusable(false);
		GridBagConstraints gbc_imageButton = new GridBagConstraints();
		gbc_imageButton.insets = new Insets(0, 0, 5, 0);
		gbc_imageButton.fill = GridBagConstraints.VERTICAL;
		gbc_imageButton.gridx = 4;
		gbc_imageButton.gridy = 0;
		add(imageButton, gbc_imageButton);
		imageButton.addActionListener(e->{
			
			JFileChooser chooser = new JFileChooser(Util.RUTAIMG);
			int result = chooser.showOpenDialog(null); 
			if(result == JFileChooser.APPROVE_OPTION) {
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
		gbc_marca.gridy = 1;
		add(marca, gbc_marca);
		
		marcaField = new JTextField();
		GridBagConstraints gbc_marcaField = new GridBagConstraints();
		gbc_marcaField.insets = new Insets(0, 0, 5, 5);
		gbc_marcaField.fill = GridBagConstraints.BOTH;
		gbc_marcaField.gridx = 1;
		gbc_marcaField.gridy = 1;
		add(marcaField, gbc_marcaField);
		marcaField.setColumns(10);
		marcaField.addActionListener(focusField);
		
		JLabel tipo = new JLabel("Tipo");
		GridBagConstraints gbc_tipo = new GridBagConstraints();
		gbc_tipo.insets = new Insets(0, 0, 5, 5);
		gbc_tipo.fill = GridBagConstraints.VERTICAL;
		gbc_tipo.anchor = GridBagConstraints.EAST;
		gbc_tipo.gridx = 2;
		gbc_tipo.gridy = 1;
		add(tipo, gbc_tipo);
		
		tipoBox = new JComboBox<String>();
		tipoBox.addItemListener(focusBox);
		
		GridBagConstraints gbc_tipoBox = new GridBagConstraints();
		gbc_tipoBox.insets = new Insets(0, 0, 5, 5);
		gbc_tipoBox.fill = GridBagConstraints.BOTH;
		gbc_tipoBox.gridx = 3;
		gbc_tipoBox.gridy = 1;
		add(tipoBox, gbc_tipoBox);
		
		JLabel contenido = new JLabel("Contenido");
		GridBagConstraints gbc_contenido = new GridBagConstraints();
		gbc_contenido.insets = new Insets(0, 0, 5, 5);
		gbc_contenido.fill = GridBagConstraints.VERTICAL;
		gbc_contenido.anchor = GridBagConstraints.EAST;
		gbc_contenido.gridx = 0;
		gbc_contenido.gridy = 2;
		add(contenido, gbc_contenido);
		
		contenidoField = new JTextField();
		GridBagConstraints gbc_contenidoField = new GridBagConstraints();
		gbc_contenidoField.insets = new Insets(0, 0, 5, 5);
		gbc_contenidoField.fill = GridBagConstraints.BOTH;
		gbc_contenidoField.gridx = 1;
		gbc_contenidoField.gridy = 2;
		add(contenidoField, gbc_contenidoField);
		contenidoField.setColumns(10);
		contenidoField.addActionListener(focusField);

		
		JLabel unidadDeMedida = new JLabel("Unidad de Medida");
		GridBagConstraints gbc_unidadDeMedida = new GridBagConstraints();
		gbc_unidadDeMedida.insets = new Insets(0, 0, 5, 5);
		gbc_unidadDeMedida.fill = GridBagConstraints.VERTICAL;
		gbc_unidadDeMedida.anchor = GridBagConstraints.EAST;
		gbc_unidadDeMedida.gridx = 2;
		gbc_unidadDeMedida.gridy = 2;
		add(unidadDeMedida, gbc_unidadDeMedida);
		
		medidaBox = new JComboBox<String>();
		GridBagConstraints gbc_medidaBox = new GridBagConstraints();
		gbc_medidaBox.insets = new Insets(0, 0, 5, 5);
		gbc_medidaBox.fill = GridBagConstraints.BOTH;
		gbc_medidaBox.gridx = 3;
		gbc_medidaBox.gridy = 2;
		add(medidaBox, gbc_medidaBox);
		medidaBox.addItemListener(focusBox);

		
		JLabel presentacion = new JLabel("Presentacion");
		GridBagConstraints gbc_presentacion = new GridBagConstraints();
		gbc_presentacion.insets = new Insets(0, 0, 5, 5);
		gbc_presentacion.fill = GridBagConstraints.VERTICAL;
		gbc_presentacion.anchor = GridBagConstraints.EAST;
		gbc_presentacion.gridx = 0;
		gbc_presentacion.gridy = 3;
		add(presentacion, gbc_presentacion);
		
		presentacionBox = new JComboBox<String>();
		GridBagConstraints gbc_presentacionBox = new GridBagConstraints();
		gbc_presentacionBox.insets = new Insets(0, 0, 5, 5);
		gbc_presentacionBox.fill = GridBagConstraints.BOTH;
		gbc_presentacionBox.gridx = 1;
		gbc_presentacionBox.gridy = 3;
		add(presentacionBox, gbc_presentacionBox);
		presentacionBox.addItemListener(focusBox);

		
		JLabel stockMaximo = new JLabel("Stock Maximo");
		GridBagConstraints gbc_stockMaximo = new GridBagConstraints();
		gbc_stockMaximo.insets = new Insets(0, 0, 5, 5);
		gbc_stockMaximo.fill = GridBagConstraints.VERTICAL;
		gbc_stockMaximo.anchor = GridBagConstraints.EAST;
		gbc_stockMaximo.gridx = 0;
		gbc_stockMaximo.gridy = 4;
		add(stockMaximo, gbc_stockMaximo);
		
		maximoBox = new JComboBox<Integer>();
		GridBagConstraints gbc_maximoBox = new GridBagConstraints();
		gbc_maximoBox.insets = new Insets(0, 0, 5, 5);
		gbc_maximoBox.fill = GridBagConstraints.BOTH;
		gbc_maximoBox.gridx = 1;
		gbc_maximoBox.gridy = 4;
		add(maximoBox, gbc_maximoBox);
		maximoBox.addItemListener(focusBox);
		Stream.of(range(100)).forEach(i -> maximoBox.addItem(i));
		
		JLabel stockMinimo = new JLabel("Stock Minimo");
		GridBagConstraints gbc_stockMinimo = new GridBagConstraints();
		gbc_stockMinimo.insets = new Insets(0, 0, 5, 5);
		gbc_stockMinimo.fill = GridBagConstraints.VERTICAL;
		gbc_stockMinimo.anchor = GridBagConstraints.EAST;
		gbc_stockMinimo.gridx = 2;
		gbc_stockMinimo.gridy = 4;
		add(stockMinimo, gbc_stockMinimo);
		
		minimoBox = new JComboBox<Integer>();
		GridBagConstraints gbc_minimoBox = new GridBagConstraints();
		gbc_minimoBox.insets = new Insets(0, 0, 5, 5);
		gbc_minimoBox.fill = GridBagConstraints.BOTH;
		gbc_minimoBox.gridx = 3;
		gbc_minimoBox.gridy = 4;
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
		gbc_descripcionField.gridy = 6;
		add(descripcionField, gbc_descripcionField);
		descripcionField.setColumns(10);
		
	    String[][] data = catalogo.getData();
	    model = new DefaultTableModel(data, columnNames) {
	        	@Override
				public boolean isCellEditable(int row, int column){
	        		return false;
	        	}
	        };
	    model.setDataVector(data, columnNames);				
        table = new JTable(model);
		table.setRowHeight(30);
		
		SwingUtilities.invokeLater(()->{
			int[] columnWeights = {3, 3, 2, 2, 1, 1, 2, 2, 2, 3};
			int parcialWeights = table.getWidth() / Arrays.stream(columnWeights).sum();;
			
			for(int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel()
				.getColumn(i)
				.setPreferredWidth( columnWeights[i]*parcialWeights );
			}
			table.repaint();
			table.revalidate();
		});
		
		
        JScrollPane tableScroll = new JScrollPane(table);		
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 5;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 7;
		add(tableScroll, gbc_table);
		
		
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
				codigoDeBarras,
				nombre,
				marca,
				tipo,
				contenido,
				unidadDeMedida,
				presentacion,
				stockMaximo, 
				stockMinimo,
				descripcion
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
				gbc_codigoDeBarras,
				gbc_nombre,
				gbc_marca,
				gbc_tipo,
				gbc_contenido,
				gbc_unidadDeMedida,
				gbc_presentacion,
				gbc_stockMaximo, 
				gbc_stockMinimo,
				gbc_descripcion
				}
		);
		
    	
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
	
	
	
	
	public Optional<Producto> getProducto() {
		Producto p = null;

		if(codigoBarrasField.getText().length() != 13 ) {
			visualizar("El codigo de barras debe tener un largo de 13");
			codigoBarrasField.requestFocus();
		}
		else if(nombreField.getText().isBlank()) {
			visualizar("El nombre no debe estar vacio");
			nombreField.requestFocus();
		}
		else if(marcaField.getText().isBlank()) {
			visualizar("La marca no debe estar vacia");
			marcaField.requestFocus();
		}
		else if(contenidoField.getText().isBlank()) {
			visualizar("La descripcion no debe estar vacia");
			contenidoField.requestFocus();
		}
		else if(descripcionField.getText().isBlank()) {
			visualizar("La descripcion no debe estar vacia");
			descripcionField.requestFocus();
		}
		else if((Integer)maximoBox.getSelectedItem() <= (Integer)minimoBox.getSelectedItem()) {
			visualizar("El inventario maximo debe ser mayor al inventario minimo");
			maximoBox.requestFocus();
		}
		else {
			 p = new Producto(
					codigoBarrasField.getText(),
					nombreField.getText(),
					marcaField.getText(),
					tipoBox.getSelectedItem().toString(),
					contenidoField.getText(),
					medidaBox.getSelectedItem().toString(),
					presentacionBox.getSelectedItem().toString(),
					(Integer)maximoBox.getSelectedItem(),
					(Integer)minimoBox.getSelectedItem(),
					descripcionField.getText()
					);	
			reiniciarCaptura();
		}
		return Optional.ofNullable(p);
		
	}
	
	
	public void actualizarTabla() {
		model.setDataVector(catalogo.getData(), columnNames);
	}
	
	
	public void reiniciarCaptura() {
		vaciarComponents();
		codigoBarrasField.requestFocus();
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
		imageLabel.setIcon(null);
	}
	

	static class FocusField implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			((JTextField)e.getSource()).transferFocus();
		}
		
	}
	static class FocusBox implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			((JComboBox<?>)e.getSource()).transferFocus();
		}
		
	}
	public JTextField getDescripcionField() {
		return descripcionField;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
