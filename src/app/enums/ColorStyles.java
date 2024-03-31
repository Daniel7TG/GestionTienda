package app.enums;

import java.awt.Color;

public enum ColorStyles {
	TITLE(Color.decode("#283343")), 
	TEXT(Color.decode("#000000")), 
	CONTENT(Color.decode("#FFFFFF")), 
	OPTION(Color.decode("#d0e2ed")), 
	LEADING(Color.decode("#b0cfe0"));
	
	public Color color;
	
	ColorStyles(Color color) {
		this.color = color;
	}
	
	
	
}
