import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

import java.util.List;

public class CustomerWindow extends JPanel{
	JTextField nameInput;
	JTextField phoneInput;
	JTextField emailInput;
	JTextField streetInput;
	JTextField cityInput;
	JTextField stateInput;
	JTextField zipInput;
	
	public CustomerWindow(Dao customerDao ) {
		super(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        nameInput = new JTextField();
        phoneInput = new JTextField();
        emailInput= new JTextField();
        streetInput = new JTextField();
        cityInput = new JTextField();
        stateInput= new JTextField();
        zipInput= new JTextField();

        // name label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gridPanel.add(new JLabel("Name"), gbc);

        // name input
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gridPanel.add(nameInput, gbc);

        // phone label
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gridPanel.add(new JLabel("Phone"), gbc);

        // phone input
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gridPanel.add(phoneInput, gbc);

        // email label
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gridPanel.add(new JLabel("Email"), gbc);

        // email input
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gridPanel.add(emailInput, gbc);

     // middle panel
        JPanel middlePanel = new JPanel(new GridLayout(1, 2));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Address");
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        EmptyBorder marginBorder = new EmptyBorder(20, 20, 20, 20);
        CompoundBorder compoundBorder = new CompoundBorder(titledBorder, marginBorder);// Set border color to black
        middlePanel.setBorder(compoundBorder);
        JPanel leftPanel = new JPanel(new GridLayout(2, 1, 20, 0));
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 20 , 0));

        streetInput = new JTextField();
        cityInput = new JTextField();
        stateInput = new JTextField();
        zipInput = new JTextField();

        // array of labels
        String[] labels = {"Street", "City", "State", "Zip"};
        for (int i = 0; i < labels.length / 2; i++) {
            JPanel leftSubPanel = new JPanel(new GridBagLayout());
            GridBagConstraints lgbc = new GridBagConstraints();
            lgbc.anchor = GridBagConstraints.WEST;
            lgbc.fill = GridBagConstraints.HORIZONTAL;
            lgbc.gridx = 0;
            lgbc.gridy = 0;
            leftSubPanel.add(new JLabel(labels[i]), lgbc);
            lgbc.gridy = 1;
            lgbc.weightx = 1.0; // This will make the text field take up the remaining horizontal space

            if(i == 0){
                leftSubPanel.add(streetInput, lgbc);
            }else if(i == 1){
                leftSubPanel.add(cityInput, lgbc);
            }
            leftSubPanel.add(new JTextField(), lgbc);
            leftPanel.add(leftSubPanel);

            JPanel rightSubPanel = new JPanel(new GridBagLayout());
            GridBagConstraints rgbc = new GridBagConstraints();
            rgbc.anchor = GridBagConstraints.WEST;
            rgbc.fill = GridBagConstraints.HORIZONTAL;
            rgbc.gridx = 0;
            rgbc.gridy = 0;
            rightSubPanel.add(new JLabel(labels[i + 2]), rgbc);
            rgbc.gridy = 1;
            rgbc.weightx = 1.0; // This will make the text field take up the remaining horizontal space

            if(i == 0){
                rightSubPanel.add(stateInput, lgbc);
            }else if(i == 1){
                rightSubPanel.add(zipInput, lgbc);
            }
            rightPanel.add(rightSubPanel);
        }
        
        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        
        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String name = nameInput.getText();

            try {
                List<Customer> customers = customerDao.getCustomers(name);

                //update UI with first found customer
                Customer customer = customers.get(0);
                nameInput.setText(customer.getName());
                phoneInput.setText(customer.getPhone());
                emailInput.setText(customer.getEmail());
            
                Address address = customer.getAddress();
                streetInput.setText(address.getStreet());
                cityInput.setText(address.getCity());
                stateInput.setText(address.getState());
                zipInput.setText(String.valueOf(address.getZip()));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "User Not Found!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add add button button.
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name = nameInput.getText();
            String email = emailInput.getText();
            String phone = phoneInput.getText();
            Customer customer = new Customer(name, email, phone);

            String street = streetInput.getText();
	        String city = cityInput.getText();
	        String state = stateInput.getText();
	        int zip = Integer.parseInt(zipInput.getText());
            Address address = new Address(street, city, state, zip);
            customer.setAddress(address);

            try {
                customerDao.saveCustomer(customer);
                JOptionPane.showMessageDialog(null, "Successfully added customer!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something Went Wrong!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add update button.
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            String name = nameInput.getText();
            String email = emailInput.getText();
            String phone = phoneInput.getText();
            Customer customer = new Customer(name, email, phone);

            String street = streetInput.getText();
	        String city = cityInput.getText();
	        String state = stateInput.getText();
	        int zip = Integer.parseInt(zipInput.getText());
            Address address = new Address(street, city, state, zip);
            customer.setAddress(address);

            try {
                customerDao.updateCustomer(customer);
                JOptionPane.showMessageDialog(null, "Successfully updated customer!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Customer Doesn't Exist!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String name = nameInput.getText();
            customerDao.deleteCustomer(name);

            try {
                customerDao.deleteCustomer(name);
                JOptionPane.showMessageDialog(null, "Successfully deleted customer!", "Success", JOptionPane.INFORMATION_MESSAGE);

                nameInput.setText("");
                phoneInput.setText("");
                emailInput.setText("");

                streetInput.setText("");
                cityInput.setText("");
                stateInput.setText("");
                zipInput.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Customer Doesn't Exist!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });      

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(gridPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

	}
}
