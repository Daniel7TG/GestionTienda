package app.UI.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import app.interfaces.Funcionable;
import app.modelos.Catalogo;
import app.util.Util;

public class PanelListadoProductos extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
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
	"Descripcion"};
	private Funcionable catalogo;
	private JScrollPane tableScroll;
	private int size;
	
	@SuppressWarnings("serial")
	public PanelListadoProductos(Funcionable catalogo) {
		setLayout(new GridLayout(1, 1, 0, 0));

		this.catalogo = catalogo;
		this.size = catalogo.getSize();
		this.table = new JTable(new ModeloTabla());
		
		
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
		tableScroll = new JScrollPane(table);		
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(tableScroll);


		
	}

	
	public void configurarTabla() {
		SwingUtilities.invokeLater(()->{
			int[] columnWeights = {3, 3, 2, 2, 1, 1, 2, 2, 2, 3};
			int parcialWeights = table.getWidth() / Arrays.stream(columnWeights).sum();
			for(int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel()
				.getColumn(i)
				.setPreferredWidth( columnWeights[i]*parcialWeights );
			}
			table.repaint();
			table.revalidate();
		});
	}
	
	
	private class ModeloTabla extends AbstractTableModel{

		private int numProductos;
		private Object[][] datos;
		private String[] columnNames = {"Codigo",
				"Nombre",
				"Marca",
				"Contenido",
				"Maximo",
				"Minimo",
				"Tipo",
				"Medida",
				"Presentacion",
		"Descripcion"}; 
		
		public ModeloTabla() {
			numProductos = size;
			
		}
		
		@Override
		public int getRowCount() {
			return numProductos;
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return datos[rowIndex][columnIndex];
		}
		
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		
		private void generarMatriz() {
			Util.toStringMat(catalogo.getList());
		}
		
		
	}
	

}
