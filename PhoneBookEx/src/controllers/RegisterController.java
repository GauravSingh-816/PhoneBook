package controllers;

import views.*;
import models.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    //jdbc:mysql://localhost:3306/PhoneBookEx
    private RegisterView registerView;

    public RegisterController(RegisterView rv){

        this.registerView = rv;

        registerView.addRegisterButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                User user = new User();
                user.setUsername(registerView.getUsername());
                user.setPassword(registerView.getPassword());

                UserDataAccess uda = new UserDataAccess();
                if (uda.registerUser(user))
                    JOptionPane.showMessageDialog(null,"Registered successfully");
                else
                    JOptionPane.showMessageDialog(null,"Registration failed womp womp");
            }
        });

        registerView.addRegisterButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                registerView.setVisible(false);

                LoginView lv = new LoginView();
                LoginController lc = new LoginController(lv);

                lv.setVisible(true);
            }
        });
    }
}