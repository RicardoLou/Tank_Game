package com.Ricardo.view;

import com.Ricardo.App;
import com.Ricardo.DAO.LoginDAO;
import com.Ricardo.DAO.ModifyDAO;
import com.Ricardo.handler.LoginViewHandler;
import com.Ricardo.handler.ModifyViewHandler;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ModifyView extends JFrame {
    public JTextField usernameField;
    public JPasswordField passwordField;

    public ModifyView() {
        setTitle("Modify");
        setSize(1000, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Modify");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(usernameField, gbc);

        // change Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("change Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(passwordField, gbc);

        // modify button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton modifyButton = new JButton("Modify");
        modifyButton.setFont(new Font("Arial", Font.BOLD, 18));
        modifyButton.setBackground(new Color(0, 123, 255));
        modifyButton.setBackground(Color.black);
        modifyButton.setFocusPainted(false);
        panel.add(modifyButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Arial", Font.BOLD, 18));
        removeButton.setBackground(new Color(0, 123, 255));
        removeButton.setBackground(Color.black);
        removeButton.setFocusPainted(false);
        panel.add(removeButton, gbc);

        // Register link
        gbc.gridy = 5;
        JButton loginButton = new JButton("Won't modify? Login now");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setForeground(new Color(0, 123, 255));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        panel.add(loginButton, gbc);

        add(panel);
        setVisible(true);
        // Add keyboard listener
        ModifyViewHandler keyListener = new ModifyViewHandler(usernameField, passwordField);
        usernameField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);

        // Register button action
        loginButton.addActionListener(e -> {
            dispose(); // Close the login frame
            new LoginView().setVisible(true); // Open the Login frame
        });

        // Login button action (for demonstration purposes)
        modifyButton.addActionListener(e -> {
                    boolean flag = ModifyDAO.modify(usernameField.getText(), passwordField.getText());
                    if (flag) {
                        JOptionPane.showMessageDialog(ModifyView.this, "Modify Success", "Info", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new LoginView().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(ModifyView.this, "Modify fail,please check and try again", "Info", JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    }
                }
        );
        removeButton.addActionListener(e -> {
                boolean flag = ModifyDAO.removeByName(usernameField.getText());
                if(flag) {
                    JOptionPane.showMessageDialog(ModifyView.this, "Remove Success", "Info", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new LoginView().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ModifyView.this, "Remove fail,please check and try again", "Info", JOptionPane.INFORMATION_MESSAGE);
                    repaint();
                }
        });
    }
    public String getUserName() {
        return usernameField.getName();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModifyView().setVisible(true));
    }
}

