package controllers;

import models.Contact;
import models.ContactDataAccess;
import models.UserDataAccess;
import views.ContactView;
import views.LoginView;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContactController {

	private ContactView contactView;
	
	public ContactController(ContactView cv) {
		
		this.contactView = cv;
		
		updateContactList();
		
		contactView.addAddButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// create an object of a contact
				Contact contact = new Contact();
				contact.setFirstname(contactView.getFirstName());
				contact.setLastname(contactView.getLastName());
				contact.setPhoneNumber(contactView.getPhoneNumber());
				
				ContactDataAccess contactData = new ContactDataAccess();
				if(contactData.addContact(contact)) {
					JOptionPane.showMessageDialog(null, "Contact added successfully");
					updateContactList();
				}
				else {
					JOptionPane.showMessageDialog(null, "An error occured");
				}
			}
		});
		
		contactView.addContactListListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				Contact contact = getSelectedContact();
				
				if(contact != null) {
					updateFields(contact);
				}
			}
		});
		
		// Update button click
		contactView.addUpdateButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Contact contact = getSelectedContact();
				
				String fn = contactView.getFirstName();
				String ln = contactView.getLastName();
				String pn = contactView.getPhoneNumber();
				
				contact.setFirstname(fn);
				contact.setLastname(ln);
				contact.setPhoneNumber(pn);
				
				if(new ContactDataAccess().updateContact(contact)) {
					JOptionPane.showMessageDialog(null, "Contact updated successfully");
					updateContactList();
				}
				
			}
		});
		
		contactView.addLogoutButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserDataAccess.currentUserId = 0;
				
				contactView.setVisible(false);
				
				LoginView lv = new LoginView();
				lv.setVisible(true);
				new LoginController(lv);
			}
		});

		contactView.addDeleteButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact contact = getSelectedContact();

				contact.setFirstname(contactView.getFirstName());
				contact.setLastname(contactView.getLastName());
				contact.setPhoneNumber(contactView.getPhoneNumber());

				if(new ContactDataAccess().deleteContact(contact)){
					updateContactList();
					JOptionPane.showMessageDialog(null,"Contact deleted.");
				}
				else{
					JOptionPane.showMessageDialog(null,"Not able to delete.");
				}
			}
		});

		contactView.addSearchButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,"Contact's Name");
				if (input == null || input.isBlank()){
					updateContactList();
				}
				else{
					updateContactList(input);
				}
			}
		});

		contactView.addExportListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportContactsToCSV("contacts_export.csv");  // Specify the path or use a file chooser
			}
		});
	}

	private void updateContactList() {
		ContactDataAccess data = new ContactDataAccess();
		
		List<Contact> contacts = data.getContacts();
			
		contactView.setContactsToModel(contacts);
	}
	
	private Contact getSelectedContact() {
		Contact contact = null;
		
		int row = contactView.getContactList().getSelectedIndex();
		
		if(row != -1) {
			contact = new ContactDataAccess().getContacts().get(row);
		}
		return contact;
	}
	
	private void updateFields(Contact contact) {
		
		contactView.getFirstNameField().setText(contact.getFirstname());
		contactView.getLastNameField().setText(contact.getLastname());
		contactView.getPhoneNumberField().setText(contact.getPhoneNumber());
	}

	private void updateContactList(String contactname){ // overloaded update specifically for search function
		ContactDataAccess data = new ContactDataAccess();

		List<Contact> contacts = data.getContacts(contactname);

		contactView.setContactsToModel(contacts);

	}

	public void exportContactsToCSV(String filename) {
		ContactDataAccess contactData = new ContactDataAccess();
		List<Contact> contacts = contactData.getContacts();  // Retrieve all contacts for the current user

		try (FileWriter fileWriter = new FileWriter(filename)) {
			fileWriter.append("First Name,Last Name,Phone Number\n");  // CSV Header
			for (Contact contact : contacts) {
				fileWriter.append(contact.getFirstname()).append(",");
				fileWriter.append(contact.getLastname()).append(",");
				fileWriter.append(contact.getPhoneNumber()).append("\n");
			}
			JOptionPane.showMessageDialog(null, "Contacts exported successfully to " + filename);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occurred while exporting contacts: " + e.getMessage());
		}
	}
}
