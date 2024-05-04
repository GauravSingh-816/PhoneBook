package controllers;

import models.Contact;
import models.ContactDataAccess;
import models.UserDataAccess;
import views.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactController {

    private ContactView contactView;

    public ContactController(ContactView cv) {
        this.contactView = cv;

        updateContactList();

        contactView.addAddButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Create an object of a contact
                Contact contact = new Contact();
                contact.setFirstname(contactView.getTxtFirstname());
                contact.setLastname(contactView.getTxtLastname());
                contact.setPhoneNumber(contactView.getTxtPhoneNumber());

                ContactDataAccess contactDataAccess = new ContactDataAccess();

                if (contactDataAccess.addContact(contact)) {
                    JOptionPane.showMessageDialog(null, "Contact added successfully");
                    updateContactList();

                } else {
                    JOptionPane.showMessageDialog(null, "An error occurred");
                }
            }
        });

        contactView.addContactListButtonListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Contact contact = getSelectedContact();

                if (contact != null) {
                    updateFields(contact);
                }
            }
        });

        //Update button click
        contactView.addUpdateButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Contact contact = getSelectedContact();
                System.out.println(getSelectedContact());

                String fn = contactView.getTxtFirstname();
                String ln = contactView.getTxtLastname();
                String pn = contactView.getTxtPhoneNumber();

                contact.setFirstname(fn);
                contact.setLastname(ln);
                contact.setPhoneNumber(pn);

                if (new ContactDataAccess().updateContact(contact)) {
                    JOptionPane.showMessageDialog(null, "Contact updated successfully!");
                    updateContactList();
                } else
                    JOptionPane.showMessageDialog(null, "Cannot update");
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

                contact.setFirstname(contactView.getTxtFirstname());
                contact.setLastname(contactView.getTxtLastname());
                contact.setPhoneNumber(contactView.getTxtPhoneNumber());

                if (new ContactDataAccess().deleteContact(contact)) {
                    updateContactList();
                    JOptionPane.showMessageDialog(null, "Contact deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong.");
                }
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

        if (row != -1) {
            contact = new ContactDataAccess().getContacts().get(row);
        }
        return contact;
    }

    private void updateFields(Contact contact) {
        contactView.getFirstNameField().setText(contact.getFirstname());
        contactView.getLastNameField().setText(contact.getLastname());
        contactView.getPhoneNumberField().setText(contact.getPhoneNumber());
    }
}
