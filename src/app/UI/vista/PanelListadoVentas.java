package app.UI.vista;

import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import app.interfaces.Funcionable;
import app.modelos.Clientes;

public class PanelListadoVentas extends JPanel {

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
	private Clientes clientes;
	private JScrollPane tableScroll;
	
	@SuppressWarnings("serial")
	public PanelListadoVentas(Clientes clientes) {
		setLayout(new GridLayout(1, 1, 0, 0));

		this.clientes = clientes;		
		String[][] data = clientes.getData();
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

}
