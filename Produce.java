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
 * @author Nuranissa Sofia Abdul Wahid
 * 
 * This subclass inherits from GroceryItem. This class is specifically
 * for items labelled as produce. It sets the name, quantity, price,
 * and product type (produce). It prints the produce data.
 */
public class Produce extends GroceryItem{

	/*to take if the produce item is organic/non-organic later*/
	private boolean isOrganic;

	/**
	 * Constructor that takes in the input item data, specifically, the
	 * String name, double quantity, and boolean is organic label
	 * of the item. The name, quantity, and price are referred to the
	 * constructor in GroceryItem
	 */
	public Produce(String name, int quantity, double price, boolean isOrganic){
		super(name, price, quantity);
		this.isOrganic = isOrganic;
	}

	/**
	 * Constructor that takes in an input String line and parses the line
	 * to set the item's data (name, quantity, price, and is organic/ non-
	 * organic label)
	 */
	public Produce(String inputLine){
		String[] parts = inputLine.split(" ", 5);
		super.setName(parts[1]);
		super.setQuantity(Integer.parseInt(parts[2]));
		super.setPrice(Double.parseDouble(parts[3]));
		this.isOrganic = Boolean.parseBoolean(parts[4]);
	}

	/**
	 * Sets the initial is organic/non-organic label to the inputed
	 * is organic/non-organic label
	 * */
	public void setIsOrganic(boolean organic){
		this.isOrganic = organic;
	}

	/**
	 * Returns if the item is organic/non-organic
	 */
	public boolean getIsOrganic(){
		return isOrganic;
	}
	
	/**
	 * Returns the item name, quantity, and price from GroceryItem and the
	 * is organic/non-organic label
	 * */
	@Override
	public String toString(){
		return super.toString() + " Organic: " + isOrganic;
	}

}
