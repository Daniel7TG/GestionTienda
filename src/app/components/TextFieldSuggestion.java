package app.components;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.function.Function;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

public class TextFieldSuggestion extends JTextField implements WindowFocusListener {

	private static final long serialVersionUID = 1L;
	private Function<JTextField, String[]> filter;
	private JTextField field;
	private JScrollPane scp;
	private JList<String> list;
	private Popup popup;
	private PopupFactory popupFactory;
	private boolean showing;
	
	public TextFieldSuggestion(Function<JTextField, String[]> filter) {
		this(filter, 400, 200);
	}
	public TextFieldSuggestion(Function<JTextField, String[]> filter, int width, int height) {
		this.filter = filter; 
		this.setPreferredSize(new Dimension(width, height));
		popupFactory = new PopupFactory();
		field = this;
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_ENTER) {
					updateList();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					list.requestFocus();
					list.getSelectionModel().setValueIsAdjusting(false);
				}
			}
		});
		field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				updateList();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(e.getOppositeComponent() != list) {
					hideList(); 
				}
			}
		});
		
		list = new JList<String>();
		list.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(e.getOppositeComponent() != field) {
					hideList(); 
				}
			}
		});
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					field.setText(list.getSelectedValue());
					hideList();
				}
			}
		});
		list.addListSelectionListener(e->{
			if(e.getValueIsAdjusting() & list.getSelectedValue() != null) {
				field.setText(list.getSelectedValue());
				hideList();
			}
		});
		
		scp = new JScrollPane();
		scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scp.setViewportView(list);
		
		SwingUtilities.invokeLater(()->{
			list.setFixedCellWidth(this.getWidth()-18);
		});
	}
	public void updateList() {
		String[] listArray = filter.apply(field);
		if(listArray.length == 0)			
			hideList();			
		else if(listArray.length == 1 & listArray[0].equals(field.getText())) 
			hideList();				
		else {
			list.setListData(listArray);
			if(showing) return;
			popup = popupFactory.getPopup(field, scp, this.getLocationOnScreen().x, this.getLocationOnScreen().y + this.getHeight());				
			showing = true;
			list.setVisible(true);			
			scp.setVisible(true);			
			popup.show();
			repaint();
			revalidate();
		}
	}
	private void hideList() {
		showing = false;
		list.setVisible(false);			
		scp.setVisible(false);	
		popup.hide();
		revalidate();
		repaint();
	}
	@Override
	public void windowGainedFocus(WindowEvent e) {
	}
	@Override
	public void windowLostFocus(WindowEvent e) {
		hideList();
	}
}
