package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame{

    //controls or components needed
    private JTextField usernameField;
    private  JPasswordField passwordField, confirmPassword;
    private JButton registerButton, loginButton;

    public RegisterView(){
        setTitle("Register");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initialize the components
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        confirmPassword = new JPasswordField(10);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        JPanel panel = new JPanel(new GridLayout(5,2));
        panel.add(new JLabel("Username: "));
        panel.add(usernameField);

        panel.add(new JLabel("Password: "));
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password: "));
        panel.add(confirmPassword);

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

        add(panel);

        setLocationRelativeTo(null);
        //setVisible(true);
    }

    public void addLoginButtonListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }

    public void addRegisterButtonListener(ActionListener listener){
        registerButton.addActionListener(listener);
    }

    public String getUsername(){
        return usernameField.getText();
    }

    public String getPassword(){
        return new String(passwordField.getText());
    }

    public String getConfirmPassword(){
        return new String(confirmPassword.getText());
    }
}
