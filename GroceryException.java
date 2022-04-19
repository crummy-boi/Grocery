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
 * This is an exception class that inherits the RuntimeException
 * and is specifically for most exception occurrences in the classes
 * of Grocery
 *
 */
public class GroceryException extends RuntimeException{
	/**
	 * An identifier of the class
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * Default constructor that prints a plain error message
	 */
	public GroceryException(){
		super("An error has occured");
	}

	/**
	 * Takes the custom message as error message
	 */
	public GroceryException(String msg){
		super(msg);
	}

}
