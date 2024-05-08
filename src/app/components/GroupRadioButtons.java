package app.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import app.enums.Permission;

public class GroupRadioButtons extends JPanel {

	Permission[] permisos;
	List<JRadioButton> buttons;
	int amount;
	
	public GroupRadioButtons(String[] names, Permission[] permisos, String title) {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
				title, TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Montserrat", Font.BOLD, 16)));
		
		if(names.length < 2) return;
		else this.amount = names.length;

		this.permisos = permisos;
		this.buttons = new ArrayList<JRadioButton>();
		this.setLayout(new GridLayout(1, amount, 5, 0));
		
		for(int i = 0; i < this.amount; i++) {
			JRadioButton button = new JRadioButton(names[i]);
			
			if(i == 0) button.addActionListener(e -> universalSelected()); 
			else button.addActionListener(e-> areAllSelected());
			
			buttons.add(button);
			this.add(button);
		}
		
	}
	
	
	public List<Permission> getPressed() {
		List<Permission> activados = new ArrayList<Permission>();
		for(int i = 0; i < amount; i++) {
			if(buttons.get(i).isSelected()) activados.add(permisos[i]);
		}
		return activados;
	}
	
	
	public void universalSelected() {
		if(buttons.get(0).isSelected())
		for(int i = 0; i < this.amount; i++) {
			if(i != 0) buttons.get(i).setSelected(false); 
		}		
	}
	public void areAllSelected() {
		buttons.get(0).setSelected(false);
		if(buttons.stream().skip(1L).allMatch(JRadioButton::isSelected)) 
		for(int i = 0; i < this.amount; i++) {
			if(i != 0) buttons.get(i).setSelected(false);
			else buttons.get(i).setSelected(true);
		}		
	}
	
	
}
