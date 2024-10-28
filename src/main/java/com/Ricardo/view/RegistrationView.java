package com.Ricardo.view;

import com.Ricardo.DAO.RegisterDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends JFrame {
    public RegistrationView() {
        setTitle("Registration");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // ID
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(idField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(confirmPasswordField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setBackground(Color.black);
        registerButton.setFocusPainted(false);
        panel.add(registerButton, gbc);

        // Login link
        gbc.gridy = 6;
        JButton loginButton = new JButton("Already have an account? Return to login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setForeground(new Color(0, 123, 255));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        panel.add(loginButton, gbc);

        add(panel);

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegistrationView.this,
                            "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //found
                if(!RegisterDAO.selectById(Integer.parseInt(id))) {
                    System.out.println("ID重复");
                } else if (!RegisterDAO.selectByName(username)) {
                    System.out.println("username重复");
                } else if(RegisterDAO.register(Integer.parseInt(id), username, password)){
                    System.out.println(Integer.parseInt(id));
                    System.out.println(username);
                    System.out.println(password);
                    // Perform registration logic here
                    // For demonstration, just showing a message
                    JOptionPane.showMessageDialog(RegistrationView.this,
                            "Registration successful!\nID: " + id + "\nUsername: " + username,
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    // After successful registration, open the login view
                    dispose();
                    new LoginView().setVisible(true); // Open the login frame
                } else {
                    System.out.println("id或账号出错");
                }

            }
        });

        // Login button action
        loginButton.addActionListener(e -> {
            dispose(); // Close the registration frame
            new LoginView().setVisible(true); // Open the login frame
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationView().setVisible(true));
    }
}
