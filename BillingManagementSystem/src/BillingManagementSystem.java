import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BillingManagementSystem {
	private static Scanner scanner = new Scanner(System.in);
	private static int totalItems = 0;
	private static double totalAmount = 0;

	private static InventoryItem[] inventory = new InventoryItem[0];;

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
				System.out.println("Do you wish to change the price for this item ? Press 1");
				int priceOption = Integer.parseInt(scanner.nextLine());
				if (priceOption == 1) {
					changeItemPrice(item, price);
				}
			}
			item.increaseQuantity(quantity);
			System.out.println("Success!");
		}
	}

	static void removeItem(String parsedName, int quantity) {
		InventoryItem item = findInventoryItem(parsedName);
		if (item == null) {
			System.out.println("Item not found in inventory!");
		} else {
			inventory = removeInventoryItem(inventory, item, quantity);
		}
		System.out.println("Success!");
	}

	static void removeItemOption(String parsedName, int quantity) {

	}

	static void displayInventoryStatus() {
		System.out.println("Inventory status:");
		if (inventory.length == 0) {
			System.out.println("Inventory is empty !");
		} else {

			System.out.println("Name\tPrice\tQuantity");

			for (InventoryItem item : inventory) {
				if (item == null) {
					System.out.println("Inventory is empty !");
					break;
				}
				System.out.printf("%s\t%.2f\t%d\n", item.getName(), item.getPrice(), item.getQuantity());
			}
		}
	}

	static void generateReportOption(String reportOption) {
		if (reportOption == "*")
			generateReport();
		else {
			generateReport(reportOption);
		}
	}

	private static void generateReport() {
		System.out.println("Sales report:");
		System.out.println("Name\tPrice\tQuantity\tTotal");
		int TotalSoldQuantity = 0;

		for (InventoryItem item : inventory) {

			int soldQuantity = item.getOriginalQuantity() - item.getQuantity();
			TotalSoldQuantity += soldQuantity;
			double total = item.getPrice() * soldQuantity;
			totalAmount += total;
			System.out.printf("%s\t%.2f\t%d\t%.2f\n", item.getName(), item.getPrice(), soldQuantity, total);
		}

		System.out.printf("Total Items: %d, Total Amount: $%.2f\n", TotalSoldQuantity, totalAmount);
	}

	private static void generateReport(String name) {

		System.out.printf("Sales report for %s:", name);
		System.out.println("Name\tPrice\tQuantitySold\tQuatityLeft\tTotal");

		for (InventoryItem item : inventory) {
			if (item.getName().equals(name)) {
				int soldQuantity = item.getOriginalQuantity() - item.getQuantity();
				double total = item.getPrice() * soldQuantity;
				System.out.printf("%s\t%.2f\t%d\t%d\t%.2f\n", item.getName(), item.getPrice(), soldQuantity,
						item.getQuantity(), total);

				break;
			}
		}
		System.out.println("(Item does not exist in the inventory");
	}

	static void makeSale(String parsedName, int parsedQuantity) {
		HashMap<InventoryItem, Integer> bufferValues = new HashMap<InventoryItem, Integer>();
		double total = 0;

		System.out.println("Enter item name (or type 'end' to finish):");
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
			System.out.println("Item not found in inventory!");

		}
		bufferValues.put(item, item.getQuantity());

		System.out.println("Enter item quantity:");
		int quantity = parsedQuantity;

		if (quantity > item.getQuantity()) {
			System.out.println("Insufficient quantity in inventory!");

		}
		double itemTotal = item.getPrice() * quantity;
		total += item.getPrice() * quantity;
		totalItems += quantity;
		totalAmount += total;

		item.decreaseQuantity(quantity);
		System.out.printf("Item total: $%.2f\n", itemTotal);
		System.out.printf("%d %s added to cart, total: $%.2f\n", quantity, itemName, total);

	}

	private static InventoryItem findInventoryItem(String name) {
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
				System.out.printf("Total: %d\n", inventory[i].getQuantity());
				System.out.println("Enter the quantity to be removed:");

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
	static void changeItemName(String parsedName, String parsedNewName) {
		System.out.println("Enter item name to change:");
		String name = parsedName;

		InventoryItem item = findInventoryItem(name);

		if (item == null) {
			System.out.println("Item not found in inventory!");
		} else {
			System.out.println("Enter new item name:");
			String newName = parsedNewName;
			item.setName(newName);
			System.out.println("Item name changed successfully!");
		}
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
	static void changeItemPrice(String parsedName, Double parsedNewPrice) {
		System.out.println("Enter item name to change price:");
		String name = parsedName;

		InventoryItem item = findInventoryItem(name);

		if (item == null) {
			System.out.println("Item not found in inventory!");
		} else {
			System.out.println("Enter new price:");
			double newPrice = parsedNewPrice;
			item.setPrice(newPrice);
			System.out.println("Price changed successfully!");
		}
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
