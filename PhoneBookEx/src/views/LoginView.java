package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField; // don't make the password just a text field

    private JButton loginButton, registerButton;

    public LoginView(){ // Constructor
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the components
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        //create panel
        JPanel panel = new JPanel(new GridLayout(4, 2));

        //add the labels and corresponding fields
        panel.add(new JLabel("Username: "));
        panel.add(usernameField);

        panel.add(new JLabel("Password: "));
        panel.add(passwordField);

        //add the buttons to the panel
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        //set button color
        Color c1 = new Color(43,143,178);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(c1);
        loginButton.setForeground(Color.white);

        Color c2 = new Color(43,178,96);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setBackground(c2);
        registerButton.setForeground(Color.white);

        panel.add(registerButton);
        panel.add(loginButton);

        add(panel); //Adds panel to the window

        setLocationRelativeTo(null);
        //setVisible(true); //Makes windows visible
    }
    public void addRegisterButtonListener(ActionListener Listener){

        registerButton.addActionListener(Listener);

    }
    public void addLoginButtonListener(ActionListener Listener){

        loginButton.addActionListener(Listener);

    }

    public String getUsername(){
       return usernameField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }

}