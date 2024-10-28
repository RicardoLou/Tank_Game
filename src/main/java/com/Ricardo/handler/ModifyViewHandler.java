package com.Ricardo.handler;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModifyViewHandler extends KeyAdapter {
    private JTextField usernameField;
    private JTextField passwordField;

    public ModifyViewHandler(JTextField usernameField, JTextField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            JOptionPane.showMessageDialog(null, "Attempting modify with\nUsername: " + username + "\nPassword: " + password);
        }
    }
}
