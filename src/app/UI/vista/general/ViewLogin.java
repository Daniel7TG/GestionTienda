package app.UI.vista.general;

import java.awt.Component;
import java.awt.Container;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import app.interfaces.Service;
import app.modelos.Usuario;
import app.modelos.services.UsuarioServiceImp;

public class ViewLogin extends JFrame {

	Service<Usuario> userService;
	JTextField usernameField;
	JPasswordField passwordField;
	
	public ViewLogin() {
		
		userService = new UsuarioServiceImp();
		
		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		Insets insetsField = new Insets(5, 5, 5, 5);
		Font titleFont = new Font("Serif", Font.BOLD, 20);
		Font font = new Font("Serif", Font.PLAIN, 16);

		
		GridBagLayout layout = new GridBagLayout();
		layout.columnWeights = new double[]{1, 5, 2};
		layout.rowWeights = new double[]{2, 1, 1};
		
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		
		JLabel titleLabel = new JLabel("Iniciar Sesión");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(titleFont);
		
		JLabel usernameLabel = new JLabel("Usuario");
		usernameLabel.setFont(font);
		usernameLabel.setHorizontalAlignment(JLabel.RIGHT);

		JButton loginBtn = new JButton("Iniciar"); 
		loginBtn.setFont(titleFont);
		loginBtn.addActionListener(e -> validateUser());

		usernameField = new JTextField();
		usernameField.setFont(font);
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				passwordField.requestFocus();
			}
		});
		
		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setFont(font);
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		passwordField = new JPasswordField();		
		passwordField.setFont(font);
		
		
		GridBagConstraints titleLabelC = new GridBagConstraints();
		titleLabelC.gridx = 0;
		titleLabelC.gridy = 0;
		titleLabelC.gridwidth = 3;
		titleLabelC.fill = GridBagConstraints.BOTH;
		titleLabelC.insets = insetsField;

		GridBagConstraints usernameLabelC = new GridBagConstraints();
		usernameLabelC.gridx = 0;
		usernameLabelC.gridy = 1;
		usernameLabelC.fill = GridBagConstraints.BOTH;
		usernameLabelC.insets = insetsField;
		
		GridBagConstraints usernameFieldC = new GridBagConstraints();
		usernameFieldC.gridx = 1;
		usernameFieldC.gridy = 1;
		usernameFieldC.fill = GridBagConstraints.HORIZONTAL;
		usernameFieldC.insets = insetsField;
		
		GridBagConstraints passwordLabelC = new GridBagConstraints();
		passwordLabelC.gridx = 0;
		passwordLabelC.gridy = 2;
		passwordLabelC.fill = GridBagConstraints.BOTH;
		passwordLabelC.insets = insetsField;
		
		GridBagConstraints passwordFieldC = new GridBagConstraints();
		passwordFieldC.gridx = 1;
		passwordFieldC.gridy = 2;
		passwordFieldC.fill = GridBagConstraints.HORIZONTAL;
		passwordFieldC.insets = insetsField;
		
		GridBagConstraints loginBtnC = new GridBagConstraints();
		loginBtnC.gridx = 2;
		loginBtnC.gridy = 1;
		loginBtnC.gridheight = 2;
		loginBtnC.insets = insetsField;
		
		
		panel.setBorder(border);

		panel.add(titleLabel, titleLabelC);
		panel.add(usernameLabel, usernameLabelC);
		panel.add(usernameField, usernameFieldC);
		panel.add(passwordLabel, passwordLabelC);
		panel.add(passwordField, passwordFieldC);
		panel.add(loginBtn, loginBtnC);
		
		this.add(panel);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(500, 300));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	
	private void validateUser() {
		String username = usernameField.getText();
		char[] passwordChar = passwordField.getPassword();
		
		if(username == null | passwordChar == null) {
			JOptionPane.showMessageDialog(null, "Llenar todos los campos");
			return;
		}
		String password = String.valueOf(passwordChar);
		
		if(!userService.exists(username)) {
			JOptionPane.showMessageDialog(null, "Contraseña o Usuario Incorrectos");
			return;
		}
		
		Usuario user = userService.get(username);
		
		if(user.getPassword().equals(password)) {
			new VentanaPrincipal(user);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Contraseña o Usuario Incorrectos");
		}
		
	}
	
	
}
