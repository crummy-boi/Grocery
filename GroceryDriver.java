/**
 * Name: Nuranissa Sofia Abdul Wahid
 * CSS 143 A
 * Assignment: GroceryManager, final assignment. To stock
 * inventory, order items in the inventory, and reorder out
 * of stock items in the inventory by using multiple classes
 * 
 */
package Grocery;

/**
* CSS 143 Final Assignment: Grocery Manager
* Instructor: Lesley Kalmin
*
*
*/

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
* @author Lesley Kalmin (and possibly others)
* 
* Driver for Grocery Manager:  Read files, process orders, sort and print results
*/
public class GroceryDriver {
	/*stores the list of items that need to be ordered*/
	static ArrayList<GroceryOrder<GroceryItem>> orders = new ArrayList<>();
	
	/**
	 * read files, stock inventory, processes orders, sort, and print results
	 * */
	public static void main(String[] args){
		GroceryManager manager = new GroceryManager();

		// stock store
		/*write the file directory to fit to your computer*/
		manager.loadInventory("groceryInventory.txt");

		System.out.println("******** Initial Inventory ********");
		manager.displayInventory();

		// purchase items
		System.out.println("\n******** Processing Orders ********");
		readOrders();
		/*process order. if fail, print error message*/
		for (GroceryOrder<GroceryItem> order : orders) {
			try {
				manager.processOrder(order);
			} catch (GroceryException e) {
				System.out.println(e.getMessage());
			}
		}
		manager.displayInventory();

		// sort inventory
		/*sort inventory by name*/
		manager.sortInventoryByName();
		System.out.println("\n******** Sort by name ********");
		manager.displayInventory();

		/*sort inventory by price*/
		manager.sortInventoryByPrice();
		System.out.println("\n******** Sort by price ********");
		manager.displayInventory();

		/*print reorder list*/
		System.out.println("\n********  Reorder List ********");
		manager.displayReorders();
	}

	/**
	 * read reorderList text in the items' respective category (dairy, meat, produce)
	 * */
	public static void readOrders() {
		Scanner input = null;
		String line;
		String[] parts;
		/*catches any exceptions within the try block*/
		try {
			/*write the file directory to fit to your computer*/
			input = new Scanner(new FileInputStream("groceryOrders.txt"));
			/*while there is a next line, add the items into the list with
			 * their respective classes of product types (dairy, produce, or meat)*/
			while (input.hasNext()) {
				GroceryOrder<GroceryItem> list = new GroceryOrder<>();
				input.nextLine();// ORDER
				line = input.nextLine();
				parts = line.split(" ");
				list.add(new Dairy(parts[1], Integer.parseInt(parts[2]), 0, 0));
				line = input.nextLine();
				parts = line.split(" ");
				list.add(new Produce(parts[1], Integer.parseInt(parts[2]), 0, false));
				line = input.nextLine();
				parts = line.split(" ");
				list.add(new Meat(parts[1], Integer.parseInt(parts[2]), 0, false));
				/*add the list to the order list*/
				orders.add(list);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		/*close the file reader/scanner*/
		finally {
			input.close();
		}
	}
}
