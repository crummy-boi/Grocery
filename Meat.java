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
 * for items labelled as meat. It sets the name, quantity, price,
 * and product type (meat). It prints the meat data.
 */
public class Meat extends GroceryItem{

	/*to take if the meat item is grounded/not grounded later*/
	private boolean isGround;

	/**
	 * Constructor that takes in the input item data, specifically, the
	 * String name, double quantity, and boolean is grounded label
	 * of the item. The name, quantity, and price are referred to the
	 * constructor in GroceryItem
	 */
	public Meat(String name, int quantity, double price, boolean isGround){
		super(name, price, quantity);
		this.isGround = isGround;
	}

	/**
	 * Constructor that takes in an input String line and parses the line
	 * to set the item's data (name, quantity, price, and is grounded/ not
	 * grounded label)
	 */
	public Meat(String inputLine){
		String[] parts = inputLine.split(" ", 5);
		super.setName(parts[1]);
		super.setQuantity(Integer.parseInt(parts[2]));
		super.setPrice(Double.parseDouble(parts[3]));
		this.isGround = Boolean.parseBoolean(parts[4]);
	}

	/**
	 * Sets the initial is grounded/not grounded label to the inputed
	 * is grounded/not grounded label
	 * */
	public void setIsGround(boolean ground){
		this.isGround = ground;
	}

	/**
	 * Returns if the item is grounded/not grounded
	 */
	public boolean getIsGround(){
		return isGround;
	}
	
	/**
	 * Returns the item name, quantity, and price from GroceryItem and the
	 * is grounded/not grounded label
	 * */
	@Override
	public String toString(){
		return super.toString() + " Ground: " + isGround;
	}
}
