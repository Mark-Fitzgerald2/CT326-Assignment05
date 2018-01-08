package shoppingCart;
//Mark Fitzgerald 15456198
import java.text.DecimalFormat;

public class Item implements Comparable<Item> {
	//create local variables for name, price and quant
	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	//create a formatter to two decimal places
	DecimalFormat precis = new DecimalFormat("0.00");
	
	//constructor
	public Item(String name, int quantity) {
		//store name and quant
		itemName = name;
		itemQuantity = quantity;
	}
	
	public String toString() {
		//print out the details of the item
		return String.format("%-4s%-15s%-1s%-5s", itemQuantity, itemName, "€", precis.format(itemPrice));
	}
	
	public String getName() {
		//return the name of the item
		return itemName;
	}

	@Override
	public int compareTo(Item item) {
		//compare two items by name
		//return the result of the comparison
		//this is to allow a decision of where they should be placed
		return itemName.compareTo(item.getName());
	}
	
	public void addQuant(int quant) {
		//add quantity to the item
		this.itemQuantity += quant;
	}
	
	public void removeQuant(int quant) {
		//remove quantity from the item
		this.itemQuantity -= quant;
	}
	
	public int getQuant() {
		//return the quantity
		return itemQuantity;
	}

	public void setPrice(double price) {
		//set the price of the item
		itemPrice = price;
	}
	public double getPrice() {
		//return the price of the item
		return itemPrice;
	}
}
