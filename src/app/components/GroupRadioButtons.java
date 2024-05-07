package app.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

public class GroupRadioButtons {

	List<JRadioButton> buttons;
	int amount;
	
	public GroupRadioButtons(String[] names) {
		if(names.length < 2) this.amount = 2;
		else this.amount = names.length;
		
		for(int i = 0; i < this.amount; i++) {
			JRadioButton button = new JRadioButton(names[i]);
			
			if(i == 0) button.addActionListener(e -> universalSelected()); 
			
			buttons.add(new JRadioButton());
			
			
		}
		
	}
	
	
	public void universalSelected() {
		if(buttons.get(0).isSelected())
		for(int i = 0; i < this.amount; i++) {
			if(i != 0) buttons.get(i).setSelected(false); 
		}		
	}
	
	
}
