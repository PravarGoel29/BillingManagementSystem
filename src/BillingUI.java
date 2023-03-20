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

		JLabel label = new JLabel("Select an option:\n");
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
				String itemName = null;
				Double itemPrice = null;
				Integer itemQuantity = null;
				boolean invalidInput = true;
				while (invalidInput) {
					try {
						itemName = JOptionPane.showInputDialog(null, "Enter item name:");
						itemPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter item price:"));
						itemQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter item quantity:"));
						invalidInput = false;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
					}
				}
				billingManagementSystem.addItem(itemName, itemPrice, itemQuantity);
				JOptionPane.showMessageDialog(null, "Item added successfully!");
			}
		});

		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				Integer itemQuantity = null;
				boolean invalidInput = true;
				InventoryItem item = billingManagementSystem.findInventoryItem(itemName);
				while (invalidInput) {

					try {

						JOptionPane.showMessageDialog(null,
								String.format("Total number of %s: %d", itemName, item.getQuantity()));
						itemQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter item quantity:"));
						invalidInput = false;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
					}
				}
				billingManagementSystem.removeItem(itemName, itemQuantity);
				JOptionPane.showMessageDialog(null,
						String.format("Total number of %s removed: %d", itemName, item.getQuantity()));

			}
		});

		displayInventoryStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, billingManagementSystem.displayInventoryStatus());
			}
		});

		generateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null,
						"Enter * to generate a total report or enter item name to generate item report");
				try {
					JOptionPane.showMessageDialog(null, billingManagementSystem.generateReportOption(itemName));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		makeSaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				Integer itemQuantity = null;
				boolean invalidInput = true;
				while (invalidInput) {
					try {
						itemQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter item quantity:"));
						invalidInput = false;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
					}
				}
				JOptionPane.showMessageDialog(null, billingManagementSystem.makeSale(itemName, itemQuantity));
			}
		});

		changeItemNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentName = JOptionPane.showInputDialog(null, "Enter current item name:");
				InventoryItem item = billingManagementSystem.findInventoryItem(currentName);

				if (item == null) {
					JOptionPane.showMessageDialog(null, "Item not found in inventory!");
				} else {
					String newName = JOptionPane.showInputDialog(null, "Enter new item name:");
					JOptionPane.showMessageDialog(null, billingManagementSystem.changeItemName(currentName, newName));
				}
			}
		});

		changeItemPriceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				Double newPrice = null;
				boolean invalidInput = true;
				InventoryItem item = billingManagementSystem.findInventoryItem(itemName);
				if (item == null) {
					JOptionPane.showMessageDialog(null, "Item not found in inventory!");
				} else {
					while (invalidInput) {
						try {
							newPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter new item price:"));
							invalidInput = false;
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
						}
					}
					JOptionPane.showMessageDialog(null, billingManagementSystem.changeItemPrice(itemName, newPrice));
				}
			}
		});

		changeItemQuantityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog(null, "Enter item name:");
				Integer newQuantity = null;
				boolean invalidInput = true;
				while (invalidInput) {
					try {
						newQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new item quantity:"));
						invalidInput = false;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
					}
				}
				billingManagementSystem.changeItemQuantity(itemName, newQuantity);
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new BillingUI();
	}
}
