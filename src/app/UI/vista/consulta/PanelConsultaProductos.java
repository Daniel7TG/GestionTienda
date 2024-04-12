package app.UI.vista.consulta;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.modelos.Producto;
import app.modelos.containers.Catalogo;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.swing.ScrollPaneConstants;

public class PanelConsultaProductos extends JPanel {

	private static final long serialVersionUID = 1L;
	private Catalogo catalogo;
	

	private JTextField textField;
	private JScrollPane scp;
	private JList<String> list; 

	private JTextField textField_1;
	private JScrollPane scp_1;
	private JList<String> list_1;
	
	
	Function<JTextField, String[]> filterByName = field -> {
		return catalogo.getList().stream()
				.map(Producto::getNombre)
				.filter(name -> name.startsWith(field.getText()))
				.toArray(String[]::new);
	};
	Function<JTextField, String[]> filterByCode = field -> {
		return catalogo.getList().stream()
				.map(Producto::getCodigoBarras)
				.filter(code -> code.startsWith(field.getText()))
				.toArray(String[]::new);
	};

	
	public PanelConsultaProductos(Catalogo catalogo) {
		if(catalogo.isEmpty()) {
			
		Producto[] listaP = new Producto[]{new Producto(), new Producto(), new Producto(), new Producto(), 
				new Producto(), new Producto(), new Producto(), new Producto(),
				new Producto(), new Producto(), new Producto(), new Producto()
				};
		String[] listaS = new String[] {"aaa", "aab", "abb", "bba", "baa", "bbb", "bca", "acb", "aaaa", "bbbb", "aaab", "aaac"};
		String[] listaC = new String[] {"11111","11112", "111114", "12222", "112222", "111333", "3131", "13133", "1", "22223", "32332", "99999"};
		for(int i = 0; i < listaP.length; i++) {
			listaP[i].setNombre(listaS[i]);
			listaP[i].setCodigoBarras(listaC[i]);
			catalogo.add(listaP[i]);
		}
		}
		
		this.catalogo = catalogo;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{100, 100, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		list = new JList<String>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.anchor = GridBagConstraints.NORTH;
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;

		scp = new JScrollPane();
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scp.setViewportView(list);
		add(scp, gbc_list);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 2;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		

		
		scp_1 = new JScrollPane();
		scp_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scp_1 = new GridBagConstraints();
		gbc_scp_1.insets = new Insets(0, 0, 5, 0);
		gbc_scp_1.fill = GridBagConstraints.BOTH;
		gbc_scp_1.gridx = 0;
		gbc_scp_1.gridy = 3;
		add(scp_1, gbc_scp_1);
		
		list_1 = new JList<String>();
		scp_1.setViewportView(list_1);
	
		configSet(textField, list, scp, textField_1, filterByName);
		configSet(textField_1, list_1, scp_1, textField, filterByCode);
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {}
			  public void removeUpdate(DocumentEvent e) {
				  if(catalogo.exists(textField_1.getText())) {
						textField.setText(catalogo.get(textField_1.getText()).getNombre());
					}
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(catalogo.exists(textField_1.getText())) {
						textField.setText(catalogo.get(textField_1.getText()).getNombre());
					}
			  }
		});
		
	}
	
	

	public void configSet(JTextField first, JList<String> firstList, JScrollPane firstScroll, JTextField next, Function<JTextField, String[]> function){
		first.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() != 40) updateList(first, firstList, firstScroll, function); // arrow 
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 40) firstList.requestFocus(); // arrow
			}
		});	
		first.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) { updateList(first, firstList, firstScroll, function);}
			@Override
			public void focusLost(FocusEvent e) {
				if(e.getOppositeComponent() != firstList) hideList(firstList, firstScroll); 
				}
		});
		firstList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {hideList(firstList, firstScroll);}
		});
		firstList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					first.setText(firstList.getSelectedValue());
					hideAndNext(firstList, firstScroll, next);
				}
			}
		});
		firstList.addListSelectionListener(l->{
			if(l.getValueIsAdjusting() & firstList.getSelectedValue() != null) { // != null necesario
				first.setText(firstList.getSelectedValue());
				hideAndNext(firstList, firstScroll, next);
			}
		});
	}	
	
	
	public void updateList(JTextField field, JList<String> list, JScrollPane scp, Function<JTextField, String[]> getter) {
		String[] listArray = getter.apply(field);
		if(listArray.length == 0)			
			hideList(list, scp);			
		else if(listArray.length == 1 & listArray[0].equals(field.getText())) 
			hideList(list, scp);				
		else {
			scp.setVisible(true);
			list.setListData(listArray);			
		}
	}
	
	
	public void hideList(JList<String> list, JScrollPane scp) {
		scp.setVisible(false);				
		list.setListData(new String[0]);
	}
	public void hideAndNext(JList<String> list, JScrollPane scp, JComponent component) {
		hideList(list, scp);
		component.requestFocus();
	}

}
