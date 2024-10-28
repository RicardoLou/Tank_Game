package com.Ricardo.view;

import com.Ricardo.App;
import com.Ricardo.DAO.LoginDAO;
import com.Ricardo.handler.LoginViewHandler;
import com.Ricardo.handler.ModifyViewHandler;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Getter
public class LoginView extends JFrame {
    public JTextField usernameField;
    public JPasswordField passwordField;

    public LoginView() {
        setTitle("Login");
        setSize(1000, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Login");
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

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setBackground(Color.black);
        loginButton.setFocusPainted(false);
        panel.add(loginButton, gbc);
        // Modify link
        gbc.gridy = 4;
        JButton modifyButton = new JButton("Modify now");
        modifyButton.setFont(new Font("Arial", Font.PLAIN, 16));
        modifyButton.setForeground(new Color(0, 123, 255));
        modifyButton.setContentAreaFilled(false);
        modifyButton.setBorderPainted(false);
        panel.add(modifyButton, gbc);

        // Register link
        gbc.gridy = 5;
        JButton registerButton = new JButton("No account? Register now");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.setForeground(new Color(0, 123, 255));
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        panel.add(registerButton, gbc);



        add(panel);
        setVisible(true);
        // Add keyboard listener
        LoginViewHandler keyListener = new LoginViewHandler(usernameField, passwordField);
        usernameField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);

        // Register button action
        registerButton.addActionListener(e -> {
            dispose(); // Close the login frame
            new RegistrationView().setVisible(true); // Open the registration frame
        });
        // Modify button action
        modifyButton.addActionListener(e -> {
            dispose(); // Close the login frame
            new ModifyView().setVisible(true); // Open the registration frame
        });

        // Login button action (for demonstration purposes)
        loginButton.addActionListener(e ->{
            boolean flag = LoginDAO.login(usernameField.getText(), passwordField.getText());
                if (flag) {
                    JOptionPane.showMessageDialog(LoginView.this, "Login Success", "Info", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new App(usernameField.getText());
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Login fail,please try again", "Info", JOptionPane.INFORMATION_MESSAGE);
                    repaint();
                }
            }
        );
    }
    public String getUserName() {
        return usernameField.getName();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
