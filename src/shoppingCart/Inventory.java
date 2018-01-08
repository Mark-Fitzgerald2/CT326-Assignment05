package shoppingCart;
//Mark Fitzgerald 15456198
import java.io.Serializable;

public class Inventory implements Serializable, Comparable<Inventory> {
	//used for serializable
	private static final long serialVersionUID = 1L;
	//create local variables for sku and cost
	private String sku;
	private double cost;
	//create local variables for name, quant and price
	private String name;
	private int quant;
	private double price;

	//constructor
	public Inventory(String sku, String name, int quantity, double price, double cost) {
		//set the values locally
		this.sku = sku;
		this.cost = cost;
		this.name = name;
		this.quant = quantity;
		this.price = price;
		//create a new item with name and quantity
		Item item = new Item(name, quantity);
	}
	
	//toString
	public String toString() {
		//print out the inventory information
		return String.format("%-7s%-18s%-7s%-7s", sku, name, quant, price);
	}

	//compare the inventory objects by name
	@Override
	public int compareTo(Inventory inv) {
		//check one name against another
		//return the result to decide whether they should swap
		return name.compareTo(inv.getName());
	}

	public String getName() {
		//return the name 
		return this.name;
	}
	
	public double getPrice() {
		//return the price
		return this.price;
	}

	public int getQuantity() {
		//return the quantity
		return this.quant;
	}
	
	public void removeQuantity(int removed) {
		//remove quantity from the inventory
		this.quant -= removed;
	}
	
	public void addQuantity(int added) {
		//add stock to the inventory
		this.quant += added;
	}

}
