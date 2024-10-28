package com.Ricardo.handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class LoginViewHandler extends KeyAdapter {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginViewHandler(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            JOptionPane.showMessageDialog(null, "Attempting login with\nUsername: " + username + "\nPassword: " + password);
        }
    }
}
