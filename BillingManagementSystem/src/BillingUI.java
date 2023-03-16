import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BillingUI extends JFrame {
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
		JButton DisplayInventoryStatusButton = new JButton("Display inventory status");
		JButton GenerateReportButton = new JButton("Generate report");
		JButton MakeSaleButton = new JButton("Make a sale");
		JButton ChangeItemNameButton = new JButton("Change item name");
		JButton ChangeItemPriceButton = new JButton("Change item price");
		JButton ChangeItemQuantityButton = new JButton("Change item quantity");
		JButton ExitButton = new JButton("Exit");

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(addItemButton);
		buttonGroup.add(removeItemButton);
		buttonGroup.add(DisplayInventoryStatusButton);
		buttonGroup.add(GenerateReportButton);
		buttonGroup.add(MakeSaleButton);
		buttonGroup.add(ChangeItemNameButton);
		buttonGroup.add(ChangeItemPriceButton);
		buttonGroup.add(ChangeItemQuantityButton);
		buttonGroup.add(ExitButton);

		panel.add(addItemButton);
		panel.add(removeItemButton);
		panel.add(DisplayInventoryStatusButton);
		panel.add(GenerateReportButton);
		panel.add(MakeSaleButton);
		panel.add(ChangeItemNameButton);
		panel.add(ChangeItemPriceButton);
		panel.add(ChangeItemQuantityButton);
		panel.add(ExitButton);

		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.addItem();
			}
		});

		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.removeItem();
			}
		});
		DisplayInventoryStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.displayInventoryStatus();
			}
		});
		GenerateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.generateReportOption();
			}
		});
		MakeSaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.makeSale();
			}
		});
		ChangeItemNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.changeItemName();
			}
		});
		ChangeItemPriceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.changeItemPrice();
			}
		});
		ChangeItemQuantityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.changeItemQuantity();
			}
		});
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call the addCustomer() method in the billing management class
				BillingManagementSystem.exit();
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new BillingUI();
	}
}
