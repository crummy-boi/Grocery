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
 * This abstract class sets the name, stock, and price of the input
 * item data. It also returns and prints the name, quantity,
 * and price of the item. It implements the Comparable<Object>
 * to compare the names of two items.
 * 
 */
public abstract class GroceryItem implements Comparable<Object>{

	/*to be used later to take the name of the item and compare to
	 * another item's name*/
	private String nameItem;
	/*to be used later to take the quantity of the item*/
	private int quantityItem;
	/*to be used later to take the price of the item*/
	private double priceItem;

	/**
	 * Constructor that sets the input item name, price, and quantity to
	 * their respective variables
	 */
	public GroceryItem(String name, double price, int quantity){
		this.nameItem = name;
		this.priceItem = price;
		this.quantityItem = quantity;
	}

	/**
	 * Default constructor that sets the name, price, and quantity to zero
	 * or nothing
	 */
	public GroceryItem(){
		this.nameItem = "No item";
		this.priceItem = 0.0;
		this.quantityItem = 0;
	}

	/**
	 * sets the initial item name to the input item name
	 * */
	public void setName(String name){
		this.nameItem = name;
	}

	/**
	 * returns the item name
	 * */
	public String getName(){
		return nameItem;
	}

	/**
	 * sets the initial price of the item to the input price
	 * */
	public void setPrice(double price){
		this.priceItem = price;
	}

	/**
	 * returns the price of the item
	 * */
	public double getPrice(){
		return priceItem;
	}

	/**
	 * sets the initial quantity of the item to the input quantity
	 * */
	public void setQuantity(int n){
		this.quantityItem = n;
	}

	/**
	 * returns the quantity of the item
	 * */
	public int getQuantity(){
		return quantityItem;
	}

	/**
	 * returns the name, quantity, and price of the item in neatly arranged
	 * columns
	 * 
	 * @throws GroceryException if the item name, quantity, or price exceeds
	 * their provided space in their column entries
	 * 
	 * */
	@Override
	public String toString(){
		/*to be used later to add and return the String information of the item*/
		String output = "";
		/*the space/indent provided for the name entry*/
		String nameSpace = "                          ";
		/*the name of the item in a string*/
		String nameOutput = "Name: " + nameItem;
		/*if the name of the item exceeds the space provided for it, throw an error
		 * that it exceeds the entry*/
		if(nameSpace.length() < nameOutput.length()) {
			throw new GroceryException(nameItem + " exceeds name entry");
		}
		/*if name does not exceed the space, subtract the provided space to fit the
		 * name into the entry and set a neat column for quantity*/
		nameOutput += nameSpace.substring(0, nameSpace.length() - nameOutput.length());

		/*the space/indent provided for the quantity entry*/
		String quantitySpace = "                  ";
		/*the quantity of the item in a string*/
		String quantityOutput = "Quantity: " + quantityItem;
		/*if the quantity of the item exceeds the space provided for it, throw an error
		 * that it exceeds the entry*/
		if(quantitySpace.length() < quantityOutput.length()) {
			throw new GroceryException(nameItem + " quantity exceeds quantity entry");
		}
		/*if quantity does not exceed the space, subtract the provided space to fit the
		 * quantity into the entry and set a neat column for price*/
		quantityOutput += quantitySpace.substring(0, quantitySpace.length() - quantityOutput.length());

		/*the space/indent provided for the price entry*/
		String priceSpace = "                 ";
		/*the price of the item in a string*/
		String priceOutput = "Price: " + priceItem;
		/*if the price of the item exceeds the space provided for it, throw an error
		 * that it exceeds the entry*/
		if(priceSpace.length() < priceOutput.length()) {
			throw new GroceryException(nameItem + " price exceeds price entry");
		}
		/*if price does not exceed the space, subtract the provided space to fit the
		 * price into the entry and set a neat column for the extra dairy, produce,
		 * or meat data*/
		priceOutput += priceSpace.substring(0, priceSpace.length() - priceOutput.length());

		/*adds the strings of name, quantity, and price together*/
		output += nameOutput + quantityOutput + priceOutput;
		/*returns the strings of item name, quantity, and price*/
		return output;
	}

	/**
	 * returns -1, 1, or 0 based on the "alphabetical"/lexicographical
	 * comparisons of the names of two items (the current item and the
	 * input item)
	 * 
	 * @throws new ClassCastException if the instance of the input
	 * object does not match with GroceryItem
	 * */
	@Override
	public int compareTo(Object o){
		/*if the input object is not an instance of GroceryItem,
		 * throw a ClassCastException*/
		if(!(o instanceof GroceryItem)) {
			throw new ClassCastException();
		}
		GroceryItem that = (GroceryItem) o;
		/*check if strings in both item names match or not. if
		 * the first item name is lesser than the second item name,
		 * return -1. if the first item name is greater than the
		 * passed item name, return 1. Otherwise, return 0*/
		if (nameItem.compareTo(that.getName()) < 0){
			return -1;
		}
		if (nameItem.compareTo(that.getName()) > 0){
			return 1;
		}
		return 0;
	}

}