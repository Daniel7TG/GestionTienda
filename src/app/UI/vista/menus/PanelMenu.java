package app.UI.vista.menus;

import app.components.MenuButton;
import app.enums.Permission;
import app.modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PanelMenu extends JPanel {

    private final Usuario usuario;

    protected PanelMenu(String header, Usuario usuario) {
        this.usuario = usuario;
        setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        setMaximumSize(new Dimension(200, 32767));
        setBackground(Color.decode("#b0cfe0"));
        setLayout(new GridLayout(6, 1, 0, 30));

        JLabel label = new JLabel(header.toUpperCase(), SwingConstants.CENTER);
        label.setBackground(Color.decode("#bdcfe9"));
        label.setOpaque(true);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        add(label);

    }


    protected void addButtons(MenuButton... buttons) {
        for (MenuButton button : buttons) {
            add(button);
            if (!usuario.hasAccessTo(button.getPermissionNeeded())) button.setEnabled(false);
        }
    }


}
