/**
 * Name: Nuranissa Sofia Abdul Wahid
 * CSS 143 A
 * Assignment: GroceryOrganiser, final assignment. To stock
 * inventory, order items in the inventory, and reorder out
 * of stock items in the inventory by using multiple classes
 * 
 */
package Grocery;

/**
 * @author Nuranissa Sofia Abdul Wahid
 * 
 * This subclass inherits from GroceryItem. This class is specifically
 * for items labelled as dairy. It sets the name, quantity, price,
 * and product type (dairy). It prints the dairy data.
 */
public class Dairy extends GroceryItem{

	/**
	 * To take the refrigeration temperature of the dairy item later
	 */
	private int refridgeTemp;

	/**
	 * Constructor that takes in the input item data, specifically, the
	 * String name, double quantity, and int refrigeration temperature
	 * of the item. The name, quantity, and price are referred to the
	 * constructor in GroceryItem
	 */
	public Dairy(String name, int quantity, double price, int refrigerationTemperature){
		super(name, price, quantity);
		this.refridgeTemp = refrigerationTemperature;
	}

	/**
	 * Constructor that takes in an input String line and parses the line
	 * to set the item's data (name, quantity, price, and refrigeration
	 * temperature)
	 */
	public Dairy(String inputLine){
		String[] parts = inputLine.split(" ", 5);
		super.setName(parts[1]);
		super.setQuantity(Integer.parseInt(parts[2]));
		super.setPrice(Double.parseDouble(parts[3]));
		this.refridgeTemp = Integer.parseInt(parts[4]);
	}

	/**
	 * Sets the initial refrigeration temperature to the inputed refrigeration
	 * temperature
	 * */
	public void setRefrigerationTemperature(int temp){
		this.refridgeTemp = temp;
	}

	/**
	 * Returns the refrigeration temperature
	 */
	public int getRefridgerationTemperature(){
		return refridgeTemp;
	}
	
	/**
	 * Returns the item name, quantity, and price from GroceryItem and the
	 * refrigeration temperature
	 * */
	@Override
	public String toString(){
		return super.toString() + " Temperature: " + refridgeTemp;
	}
}
