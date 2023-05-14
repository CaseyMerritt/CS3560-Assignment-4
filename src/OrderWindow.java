import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import java.util.List;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class OrderWindow extends JPanel{
	String[] itemsList = {"Cobb Salad", "Caesar Salad", "Greek Salad"};

	JTextField numberInput;
	JTextField dateInput;
	JTextField priceInput;
	
	public OrderWindow(Dao orderDao) {
		setLayout(new BorderLayout());

		String[] customersList = updateUI(orderDao);
		
		JPanel top = new JPanel(new GridLayout(3,1, 0, 60));
		
		JPanel one = new JPanel(new GridLayout(2,2, 20 , 0));
		
		JLabel num = new JLabel("Number");
		JLabel Date = new JLabel("Date");
		
		one.add(num);
		one.add(Date);
		
		numberInput = new JTextField();
		dateInput = new JTextField();
		
		one.add(numberInput);
		one.add(dateInput);
		
		top.add(one);
		
		JPanel two = new JPanel(new GridLayout(2,1));
		
		JLabel customerLabel = new JLabel("Customer");
		
		two.add(customerLabel);
		
		JComboBox<String> customers = new JComboBox<>(customersList);
		
		two.add(customers);
		
		top.add(two);
		
		JPanel three = new JPanel(new GridLayout(2,2, 20 , 0));
		
		JLabel itemLabel = new JLabel("Number");
		JLabel priceLabel = new JLabel("Price ($)");
		
		three.add(itemLabel);
		three.add(priceLabel);
		
		JComboBox<String> items = new JComboBox<>(itemsList);
		priceInput = new JTextField();
		
		three.add(items);
		three.add(priceInput);
		
		top.add(three);
		
        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            int number = Integer.parseInt(numberInput.getText());

            try {
                List<Order> orders = orderDao.getOrder(number);
                //update UI with first found customer
                Order order = orders.get(0);
                numberInput.setText(String.valueOf(order.getNumber()));

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
				String strDate = dateFormat.format(order.getDate());  
                dateInput.setText(strDate);

				priceInput.setText(String.valueOf(order.getPrice()));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Order Not Found!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add add button button.
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
			int number = Integer.parseInt(numberInput.getText());
            String date = dateInput.getText();
			String item = (String) items.getSelectedItem();

            try {
				Date D = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				double price = Double.parseDouble(priceInput.getText());
				Order order = new Order(number, D, price, item);
                orderDao.saveOrder(order);
                JOptionPane.showMessageDialog(null, "Successfully added Order!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something Went Wrong!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add update button.
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
			int number = Integer.parseInt(numberInput.getText());
            String date = dateInput.getText();
			String item = (String) items.getSelectedItem();

            try {
				Date D = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				double price = Double.parseDouble(priceInput.getText());
				Order order = new Order(number, D, price, item);
                orderDao.updateOrder(order);
                JOptionPane.showMessageDialog(null, "Successfully updated customer!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Order Doesn't Exist!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
			int number = Integer.parseInt(numberInput.getText());

            try {
                orderDao.deleteOrder(number);
                JOptionPane.showMessageDialog(null, "Successfully deleted order!", "Success", JOptionPane.INFORMATION_MESSAGE);

                numberInput.setText("");
                dateInput.setText("");
                priceInput.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Order Doesn't Exist!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });   

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        
        add(top, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
	}

	public String[] updateUI(Dao customerDao){
		List<Customer> customers = customerDao.getAllCustomers();

		String[] customersList = new String[customers.size()];

		for (int i = 0; i < customers.size(); i++) {
			customersList[i] = customers.get(i).getName();
		}

		return customersList;
	}
}
