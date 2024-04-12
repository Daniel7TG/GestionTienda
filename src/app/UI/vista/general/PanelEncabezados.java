package app.UI.vista.general;

import javax.swing.JPanel;

//import app.enums.ColorStyles;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import static app.enums.ColorStyles.*;

public class PanelEncabezados extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEncabezados(String titulo) {
		setBackground(OPTION.color);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lbTitulo = new JLabel(titulo);
		lbTitulo.setForeground(TITLE.color);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 36));
		add(lbTitulo, BorderLayout.NORTH);

	}

}
