package views;

import models.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactView extends JFrame {

	// create component references
	private JTextField txtFirstname, txtLastname, txtPhoneNumber;
	private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnLogout;
	private JList<String> contactList;
	
	private DefaultListModel<String> listModel;
	
	// constructor
	public ContactView() {

		setTitle("Phonebook");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		

		// initialize the components
		listModel = new DefaultListModel<>();
		setContactList(new JList<>(listModel));
		
		txtFirstname = new JTextField(20);
		txtLastname = new JTextField(20);
		txtPhoneNumber = new JTextField(20);
		btnAdd = new JButton("Add");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnSearch = new JButton("Search");
		btnLogout = new JButton("Logout");
		contactList = new JList<String>();
		contactList.setModel(listModel);
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4, 2));
		inputPanel.add(new JLabel("Firstname:"));
		inputPanel.add(txtFirstname);
		inputPanel.add(new JLabel("Lastname:"));
		inputPanel.add(txtLastname);
		inputPanel.add(new JLabel("Phone number:"));
		inputPanel.add(txtPhoneNumber);
		inputPanel.add(btnAdd);
		inputPanel.add(btnUpdate);
		
		// list panel
		JPanel listPanel = new JPanel(new GridLayout(2, 1));
		listPanel.add(new JLabel("Contact list"));
		listPanel.add(new JScrollPane(getContactList()));
		
		
		// buttons panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnSearch);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnLogout);
		
		// add panel to the window
		setLayout(new BorderLayout());
		add(inputPanel, BorderLayout.NORTH);
		add(listPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		// getContacts(loadContacts());
		
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void addAddButtonListener(ActionListener listener) {
		btnAdd.addActionListener(listener);
	}
	
	public void addUpdateButtonListener(ActionListener listener) {
		btnUpdate.addActionListener(listener);
	}
	
	public void addDeleteButtonListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
	}
	
	public void addSearchButtonListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}
	
	public void addLogoutButtonListener(ActionListener listener) {
		btnLogout.addActionListener(listener);
	}
	
	public void addContactListListener(ListSelectionListener listener) {
		contactList.addListSelectionListener(listener);
	}
	
	// getters
	public JTextField getFirstNameField() {
		return txtFirstname;
	}
	
	public String getFirstName() {
		return getFirstNameField().getText();
	}
	
	public JTextField getLastNameField() {
		return txtLastname;
	}
	public String getLastName() {
		return getLastNameField().getText();
	}
	
	public JTextField getPhoneNumberField() {
		return txtPhoneNumber;
	}
	public String getPhoneNumber() {
		return getPhoneNumberField().getText();
	}
	
	public void setContactsToModel(List<Contact> contacts) {
		
		listModel.clear();
		//listModel.addElement("Test");
		
		for(Contact c: contacts) {
			listModel.addElement(c.getFirstname() +"\t\t"+c.getLastname()+"\t\t - "+c.getPhoneNumber());
		}
	}
	
	public void setContactList(JList<String> contactList) {
		this.contactList = contactList;
	}
	
	public JList<String> getContactList(){
		
		return contactList;
	}
	
//	private List<Contact> loadContacts() {
//		
//		ContactDataAccess data = new ContactDataAccess();
//		
//		List<Contact> c = data.getContacts(UserDataAccess.currentUserId);
//		return c;
//	}
	
	
	
	
	
	
	
}
