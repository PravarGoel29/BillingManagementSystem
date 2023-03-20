import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class BillingManagementSystem {
	BillingDB db = new BillingDB();
	private static Scanner scanner = new Scanner(System.in);
	private static int totalItems = 0;
	private static double totalAmount = 0;

	static InventoryItem[] inventory = new InventoryItem[0];;

	static void exit() {
		System.out.println("Exiting...");
		System.exit(0);
	}

	private String inputValue;

	static void addItem(String parsedName, Double parsedPrice, int parsedQuantity) {

		String name = parsedName;

		double price = parsedPrice;

		int quantity = parsedQuantity;

		InventoryItem item = findInventoryItem(name);

		if (item == null) {
			inventory = addInventoryItem(inventory, new InventoryItem(name, price, quantity));
		} else {
			if (price != item.getPrice()) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to change the price for this item?",
						"Change Price", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					changeItemPrice(item, price);
				}
			}
			item.increaseQuantity(quantity);

		}
	}

	static void removeItem(String parsedName, int quantity) {
		InventoryItem item = findInventoryItem(parsedName);
		if (item == null) {
			System.out.println("Item not found in inventory!");
		} else {
			inventory = removeInventoryItem(inventory, item, quantity);
		}
	}

	static void removeItemOption(String parsedName, int quantity) {

	}

	static StringBuilder displayInventoryStatus() {
		StringBuilder message = new StringBuilder();
		message.append("Inventory status:\n\n");
		if (inventory.length == 0) {
			message.append("Inventory is empty !");
		} else {
			message.append(String.format("%-15s %-10s %-10s\n", "Name", "Price", "Quantity"));
			for (InventoryItem item : inventory) {
				if (item == null) {
					message.append("Inventory is empty !");
					break;
				}
				message.append(
						String.format("%-15s $%-9.2f %d\n", item.getName(), item.getPrice(), item.getQuantity()));
			}
		}
		return message;
	}

	static StringBuilder generateReportOption(String reportOption) {
		if (reportOption == "*")
			return generateReport();
		else {
			return generateReport(reportOption);
		}
	}

	private static StringBuilder generateReport() {
		StringBuilder report = new StringBuilder();
		report.append("Sales report:\n");
		report.append("Name\tPrice\tQuantity\tTotal\n");
		int totalSoldQuantity = 0;
		double totalAmount = 0;

		for (InventoryItem item : inventory) {
			int soldQuantity = item.getOriginalQuantity() - item.getQuantity();
			totalSoldQuantity += soldQuantity;
			double total = item.getPrice() * soldQuantity;
			totalAmount += total;
			report.append(String.format("%s\t%.2f\t%d\t%.2f\n", item.getName(), item.getPrice(), soldQuantity, total));
		}

		report.append(String.format("Total Items: %d, Total Amount: $%.2f\n", totalSoldQuantity, totalAmount));
		return report;
	}

	private static StringBuilder generateReport(String name) {

		StringBuilder report = new StringBuilder();
		report.append("Sales report for " + name + ":\n");
		report.append("Name\tPrice\tQuantitySold\tQuatityLeft\tTotalSale\n");

		boolean itemFound = false;

		for (InventoryItem item : inventory) {
			if (item.getName().equals(name)) {
				itemFound = true;
				int soldQuantity = Math.abs(item.getOriginalQuantity() - item.getQuantity());
				double total = Math.abs(item.getPrice() * soldQuantity);
				report.append(String.format("%s\t%.2f\t%d\t%d\t%.2f\n", item.getName(), item.getPrice(), soldQuantity,
						item.getQuantity(), total));
				break;
			}
		}

		if (!itemFound) {
			report.append("(Item does not exist in the inventory)\n");
		}

		return report;
	}

	static StringBuilder makeSale(String parsedName, int parsedQuantity) {
		StringBuilder message = new StringBuilder();
		HashMap<InventoryItem, Integer> bufferValues = new HashMap<InventoryItem, Integer>();
		double total = 0;
		String itemName = parsedName;

		if (itemName.equals("end")) {

		}

		Set<InventoryItem> keys = bufferValues.keySet();

		if (itemName.equals("cancel")) {
			for (InventoryItem key : keys) {
				key.setQuantity(bufferValues.get(key));
			}
		}

		InventoryItem item = findInventoryItem(itemName);

		if (item == null) {
			message.append("Item not found in inventory!\n");

		}
		bufferValues.put(item, item.getQuantity());

		int quantity = parsedQuantity;

		if (quantity > item.getQuantity()) {
			message.append("Insufficient quantity in inventory!\n");

		}
		double itemTotal = item.getPrice() * quantity;
		total += item.getPrice() * quantity;
		totalItems += quantity;
		totalAmount += total;

		item.decreaseQuantity(quantity);
		message.append("Item\\Total\n");
		message.append(itemName + "  " + itemTotal + "\n");
		message.append(quantity + " " + itemName + " added to cart, total:" + total);
		return message;

	}

	static InventoryItem findInventoryItem(String name) {
		for (InventoryItem item : inventory) {
			if (item.getName().equals(name)) {
				return item;
			}
		}

		return null;
	}

	private static InventoryItem[] addInventoryItem(InventoryItem[] inventory, InventoryItem item) {
		int length = inventory.length;
		InventoryItem[] newInventory = new InventoryItem[length + 1];

		for (int i = 0; i < length; i++) {
			newInventory[i] = inventory[i];
		}

		newInventory[length] = item;

		return newInventory;
	}

	private static InventoryItem[] removeInventoryItem(InventoryItem[] inventory, InventoryItem item,
			int statedQuantity) {
		int length = inventory.length;
		InventoryItem[] newInventory = new InventoryItem[length];

		int j = 0;

		for (int i = 0; i < length; i++) {
			if (inventory[i] != item) {
				newInventory[j++] = inventory[i];
			} else {

				if (statedQuantity == inventory[i].getQuantity()) {
					continue;
				} else {
					System.out.println(inventory[i].getName());
					changeItemQuantity(inventory[i], statedQuantity);
					// addInventoryItem(newInventory, new InventoryItem(inventory[i].getName(),
					// inventory[i].getPrice(),
					// statedQuantity - inventory[i].getQuantity()));
					newInventory[j++] = inventory[i];
					;
				}
			}
		}

		return newInventory;
	}

	// Change the name of an item in the inventory
	static StringBuilder changeItemName(String parsedName, String parsedNewName) {
		StringBuilder message = new StringBuilder();

		String name = parsedName;

		InventoryItem item = findInventoryItem(name);

		if (item == null) {
			message.append("Item not found in inventory!");
		} else {
			String newName = parsedNewName;
			item.setName(newName);
			message.append("Item name changed successfully!");
		}
		return message;
	}

	// private static void changeItemName(InventoryItem item) {
	// InventoryItem itemName = findInventoryItem(item.getName());

	// if (itemName == null) {
	// System.out.println("Item not found in inventory!");
	// } else {
	// System.out.println("Enter new item name:");
	// String newName = scanner.nextLine();
	// System.out.println("Item name changed successfully!");
	// }
	// }

	// Change the price of an item in the inventory
	static StringBuilder changeItemPrice(String parsedName, Double parsedNewPrice) {
		StringBuilder message = new StringBuilder();

		String name = parsedName;

		InventoryItem item = findInventoryItem(name);

		if (item == null) {
			message.append("Item not found in inventory!");
		} else {
			double newPrice = parsedNewPrice;
			item.setPrice(newPrice);
			message.append("Price changed successfully!");
		}
		return message;
	}

	private static void changeItemPrice(InventoryItem item, double newPrice) {

		InventoryItem itemName = findInventoryItem(item.getName());

		if (itemName == null) {
			System.out.println("Item not found in inventory!");
		} else {
			System.out.println("Enter new price:");
			double updatedPrice = newPrice;
			item.setPrice(updatedPrice);
			System.out.println("Price changed successfully!");
		}
	}

	// Change the quantity of an item in the inventory
	static void changeItemQuantity(String parsedName, int parsedQuantity) {
		System.out.println("Enter item name to change quantity:");
		String name = parsedName;

		InventoryItem item = findInventoryItem(name);

		if (item == null) {
			System.out.println("Item not found in inventory!");
		} else {
			System.out.println("Enter new quantity:");
			int newQuantity = parsedQuantity;
			item.setQuantity(newQuantity);
			System.out.println("Quantity changed successfully!");
		}
	}

	private static void changeItemQuantity(InventoryItem item, int removedQuantity) {

		InventoryItem itemName = findInventoryItem(item.getName());

		if (itemName == null) {
			System.out.println("Item not found in inventory!");
		} else {
			if (removedQuantity <= item.getQuantity()) {
				int newQuantity = item.getQuantity() - removedQuantity;
				item.setQuantity(newQuantity);
				System.out.println("Quantity changed successfully!");
			} else {
				System.out.println("Invalid Quantity provided!");
			}
		}
	}

}

class InventoryItem {
	private String name;
	private double price;
	private int quantity;
	private int originalQuantity;

	public InventoryItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.originalQuantity = quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getOriginalQuantity() {
		return originalQuantity;
	}

	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void decreaseQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public void resetQuantity(String name) {

	}

}