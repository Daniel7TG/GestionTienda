package app.util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.modelos.DetallesVenta;

public class TableModel extends DefaultTableModel {

	JTable table;
	Object[][] data;
	String[] columns;
	int size;
	
	public TableModel(JTable table, Object[][] data, String[] columns) {
		this.table = table;
		this.data = data;
		this.columns = columns;		
		this.size = data.length;
		setDataVector(data, columns);
	}
	
	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	} 
	@Override
	public int getRowCount() {
		return size;
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public void configurarTabla(int ...columnWeights) {
		SwingUtilities.invokeLater(()->{
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
	
	public void update(Object[][] data) {
		this.size = data.length;
		setDataVector(data, columns);
	}
	
	public void renderListColumn(int column) {
		table.getColumnModel().getColumn(column).setCellRenderer(new ListRenderer());
	}
	
	
	class ListRenderer extends DefaultTableCellRenderer {
		public ListRenderer() {
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if(size <= 0) return null;			
			List<DetallesVenta> arrayList = (List<DetallesVenta>)data[row][column];
			String[] stringArray = new String[arrayList.size()];
			table.setRowHeight(row, 19 * arrayList.size() );
			for(int i = 0; i < arrayList.size(); i++) stringArray[i] = arrayList.get(i).toString();
			return new JList<String>(stringArray);
		}
	}

}

