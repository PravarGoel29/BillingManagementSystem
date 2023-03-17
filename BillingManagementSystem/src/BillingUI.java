import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BillingUI extends JFrame {
	private BillingManagementSystem billingManagementSystem;

	public BillingUI() {
		setTitle("Billing Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JLabel label = new JLabel("Select an option:");
		panel.add(label);

		JButton addItemButton = new JButton("Add item");
		JButton removeItemButton = new JButton("Remove item");
		JButton displayInventoryStatusButton = new JButton("Display inventory status");
		JButton generateReportButton = new JButton("Generate report");
		JButton makeSaleButton = new JButton("Make a sale");
		JButton changeItemNameButton = new JButton("Change item name");
		JButton changeItemPriceButton = new JButton("Change item price");
		JButton changeItemQuantityButton = new JButton("Change item quantity");
		JButton exitButton = new JButton("Exit");

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(addItemButton);
		buttonGroup.add(removeItemButton);
		buttonGroup.add(displayInventoryStatusButton);
		buttonGroup.add(generateReportButton);
		buttonGroup.add(makeSaleButton);
		buttonGroup.add(changeItemNameButton);
		buttonGroup.add(changeItemPriceButton);
		buttonGroup.add(changeItemQuantityButton);
		buttonGroup.add(exitButton);

		panel.add(addItemButton);
		panel.add(removeItemButton);
		panel.add(displayInventoryStatusButton);
		panel.add(generateReportButton);
		panel.add(makeSaleButton);
		panel.add(changeItemNameButton);
		panel.add(changeItemPriceButton);
		panel.add(changeItemQuantityButton);
		panel.add(exitButton);

		billingManagementSystem = new BillingManagementSystem();

		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				Double itemPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter item price:"));
				Integer itemQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter item quantity:"));
				billingManagementSystem.addItem(itemName, itemPrice, itemQuantity);
			}
		});

		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				Integer itemQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter item quantity:"));
				billingManagementSystem.removeItem(itemName, itemQuantity);
			}
		});

		displayInventoryStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				billingManagementSystem.displayInventoryStatus();
			}
		});

		generateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null,
						"Enter * to generate a total report or enter item name to generate item report");
				billingManagementSystem.generateReportOption(itemName);
			}
		});

		makeSaleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quantity:"));
				billingManagementSystem.makeSale(itemName, quantity);
			}
		});

		changeItemNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				String newItemName = JOptionPane.showInputDialog(null, "Enter new item name:");
				billingManagementSystem.changeItemName(itemName, newItemName);
			}
		});

		changeItemPriceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				double newPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter new item Price:"));
				billingManagementSystem.changeItemPrice(itemName, newPrice);

			}
		});
		changeItemQuantityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new item Quantity:"));
				// Call the addCustomer() method in the billing management class
				billingManagementSystem.changeItemQuantity(itemName, newQuantity);
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				billingManagementSystem.exit();
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new BillingUI();
	}
}
