/**
 * Name: Nuranissa Sofia Abdul Wahid
 * CSS 143 A
 * Assignment: GroceryManager, final assignment. To stock
 * inventory, order items in the inventory, and reorder out
 * of stock items in the inventory by using multiple classes
 * 
 */
package Grocery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Nuranissa Sofia Abdul Wahid
 * 
 * This class takes all of the classes together with its many methods.
 * This class reads the inventory file, stocks the inventory, processes
 * the orders, sorts the inventory by name and price, and prints the
 * inventory and reorder list.
 * 
 */
public class GroceryManager {

	/*holds the item in the current inventory*/
	private ArrayList<GroceryItem> inventory = new ArrayList<>();
	/*holds the item that needs to be reordered*/
	private HashSet<String> reorderList = new HashSet<>();

	/**
	 * Reads the inventory file and stocks the ArrayList inventory with the
	 * item specific data from the inventory file. Catches FileNotFoundException
	 * if there is no file to read from.
	 */
	public void loadInventory(String filename) {
		/*initialise scanner with null*/
		Scanner input = null;
		/*catches if there is no file found within the try block*/
		try {
			/*reads the input file to be parsed later*/
			input = new Scanner(new FileInputStream(filename));
			String nextLine = input.nextLine();
			/*to separate the number of dairy, produce, and meat from each other*/
			String[] partLine = nextLine.split(" ");
			/*to convert the number of dairy products from String to integer*/
			int numDairy = Integer.parseInt(partLine[0]);
			System.out.println("Number of Dairy Products: " + numDairy);
			/*to convert the number of produce products from String to integer*/
			int numProduce = Integer.parseInt(partLine[1]);
			System.out.println("Number of Produce Products: " + numProduce);
			/*to convert the number of meat products from String to integer*/
			int numMeat = Integer.parseInt(partLine[2]);
			System.out.println("Number of Meat Products: " + numMeat);
			/*while there is a next line, add the items into the inventory with
			 * their respective classes of product types (dairy, produce, or meat)*/
			while (input.hasNext()) {
				/*to move to next line*/
				String line = input.nextLine();
				/*to separate the product type from the rest of the item data*/
				String[] parts = line.split(" ", 5);
				/*if the item is a dairy product, refer it to the dairy class and
				 * add the item data into the inventory*/
				if(parts[0].equals("Dairy")) {
					inventory.add(new Dairy(line));
				}
				/*if the item is a produce product, refer it to the produce class and
				 * add the item data into the inventory*/
				else if(parts[0].equals("Produce")) { 
					inventory.add(new Produce(line));
				}
				/*if the item is a meat product, refer it to the meat class and
				 * add the item data into the inventory*/
				else {
					inventory.add(new Meat(line));
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		/*close the scanner/file reader, regardless if there's an exception*/
		finally {
			input.close();
		}
	}

	/**
	 * If the order is listed in the inventory, subtract the stock quantity
	 * while the item is ordered. If stock reaches zero, add the out of stock
	 * item to the reorder list, remove the item from the inventory, and
	 * throw an exception.
	 * 
	 * @throws GroceryException if there the ordered item cannot be found in
	 * the inventory or/and the item stock reaches zero while being ordered
	 * 
	 */
	public void processOrder(GroceryOrder<GroceryItem> order) throws GroceryException{
		/*while the item is being ordered, check if the item is in the inventory and is
		 * in stock. if order is not in stock or not in the inventory, throw an exception.
		 * if not, reduce the item quantity in inventory while ordering*/
		for(int i = 0; i < order.size(); i++) {
			/*to be used later to check if the order is in the inventory*/
			String orderName = order.get(i).getName();
			/*to be used later to check if the item is found in the inventory*/
			GroceryItem foundItem = findItemByName(orderName);
			/*if the order is not in the inventory, throw an error and continue the
			 * order list*/
			if(foundItem == null) {
				try {
					throw new GroceryException("Cannot find item in inventory: " + orderName);
				}catch(GroceryException e) {
					System.out.println(e.getMessage());
				}
				continue;
			}
			/*the item quantity in the inventory*/
			int foundQuantity = foundItem.getQuantity();
			/*the item quantity needed to be ordered from the inventory*/
			int orderQuantity = order.get(i).getQuantity();
			/*if the item is out of stock while being ordered in the try block, catch
			 * the thrown GroceryException*/
			try {
				/*if the quantity in the inventory is not out of stock while being ordered,
				 * reduce the item quantity in the inventory by the ordered quantity*/
				if(orderQuantity < foundQuantity){
					foundItem.setQuantity(foundQuantity - orderQuantity);
				}
				/*if the quantity in the inventory is out of stock while being ordered,
				 * add the out of stock item to the reorder list, remove the item in the
				 * inventory, and throw an error that the item "is out of stock"*/
				else{
					reorderList.add(foundItem.getName());
					foundItem.setQuantity(0);
					inventory.remove(foundItem);
					throw new GroceryException("Out of " + foundItem.getName());
				}
			}catch(GroceryException ex) {
				System.out.println(ex.getMessage());
			}

		}

	}

	/**
	 * Returns the target item in the inventory. If item is not
	 * found in the inventory, return null
	 */
	public GroceryItem findItemByName(String name){
		for(GroceryItem list: inventory){
			if(list.getName().equals(name)){
				return list;
			}
		}
		return null;
	}

	/**
	 * Reorganises the inventory by the items' names (A-Z) through
	 * using the bubble sort and compareTo method in the GroceryItem
	 * class
	 */
	public void sortInventoryByName(){
		for(int i = 0; i < inventory.size()-1; i++){
			for(int j = 0 ; j < inventory.size()-i-1; j++){
				/*if the current name being compared to its neighbouring/next name is less
				 * than zero, begin swapping*/
				if(inventory.get(j).getName().compareTo(inventory.get(j+1).getName()) > 0){
					/*create a temporary object of the current item to replace the position
					 * of the neighbouring/next item*/
					GroceryItem temp = inventory.get(j);
					/*sets item in the current position to the item from its neighbouring/
					 * next position*/
					inventory.set(j, inventory.get(j+1));
					/*sets item in the neighbouring/next position to the item from the
					 * previous position*/
					inventory.set(j+1,temp);
				}
			}
		}
	}

	/**
	 * Reorganises the inventory by the items' price (lowest to highest)
	 * through using the selection sort
	 */
	public void sortInventoryByPrice(){
		for(int i = 0; i < inventory.size() - 1; i++){
			/*to store the current minimum price during the loop execution*/
			int minPrice = i;
			for(int j = i + 1; j < inventory.size(); j++){
				/*if the price of the next item is less than the price of the current item,
				 * the minimum price is switched to the position of the next item*/
				if(inventory.get(j).getPrice() < inventory.get(minPrice).getPrice()){
					minPrice = j;
				}
			}
			/*create a temporary object of the minimum price to replace the position
			 * of the incrementing item*/
			GroceryItem temp = inventory.get(minPrice);
			/*sets item in the position with the minimum price to the item from the
			 * incrementing position*/
			inventory.set(minPrice, inventory.get(i));
			/*sets item in the incrementing position to the item with the minimum price*/
			inventory.set(i, temp);
		}
	}

	/**
	 * Prints the inventory with all the item specific data
	 */
	public void displayInventory(){
		for(GroceryItem list: inventory){
			System.out.println(list.toString());
		}
	}

	/**
	 * Prints the list of items that need to be reordered
	 */
	public void displayReorders(){
		for(String list: reorderList){
			System.out.println(list.toString());
		}
	}

}
