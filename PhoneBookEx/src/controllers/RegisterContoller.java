package controllers;

import models.User;
import models.UserDataAccess;
import views.LoginView;
import views.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterContoller {

	private RegisterView registerView;
	
	public RegisterContoller(RegisterView rv) 
	{
		this.registerView = rv;
		
		registerView.addRegisterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				User user = new User();
				user.setUsername(registerView.getUsername());
				user.setPassword(registerView.getPassword());
				
				// UserDataAccess uda = new UserDataAccess();
				
				if(new UserDataAccess().registerUser(user))
					JOptionPane.showMessageDialog(null, "Registered successfully");
				else
					JOptionPane.showMessageDialog(null, "Registeration failed");
				
			}
		});
		
		registerView.addLoginButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				LoginView lv = new LoginView();
				LoginController lc = new LoginController(lv);
				
				// lv.setLocationRelativeTo(null);
				lv.setVisible(true);
				
				registerView.setVisible(false);
			}
		});
	}
}
