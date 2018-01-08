package shoppingCart;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCart {
	//create local variables for name and date
	private String custName;
	private String date;
	//create a new static inventory list
	//static is used so that multiple carts can use the same inventory
	private static ArrayList<Inventory> invList = new ArrayList<Inventory>();
	//declare a cart arrayList
	public ArrayList<Item> cart;
	//create a decimal formatter to two decimal places
	DecimalFormat precis = new DecimalFormat("0.00");
	
	//constructor
	public ShoppingCart(String name, String date) throws IOException, ClassNotFoundException {
		//store name and date
		this.custName = name;
		this.date = date;
		//create a new cart
		cart = new ArrayList<Item>();
		//check if the inventory list is empty
		//if it is we need to update it from our inventory.txt
		if(invList.isEmpty()) {
			//Deserialize the ArrayList
			//create a file input stream object
			FileInputStream fis = new FileInputStream("inventory.txt");
			//create a object input stream object
			ObjectInputStream ois = new ObjectInputStream(fis);
			//store the deserialized array in the static list 
			invList = (ArrayList<Inventory>) ois.readObject();
			//close the object input stream
			ois.close();
		}
	}
	
	//add an item to the cart
	//take in the items name and quant
	public void addItem(String item, int quant) {
		//does the item exist in the inventory?
		int index = searchInventory(item);
		//if it exists index returns where it is in the list
		if(index>=0) {
			//store the info from invList
			Inventory inv = invList.get(index);
			//does the item already exist in the cart?
			//eg are we adding more apples to what was 
			//already in the cart
			int index2 = searchCart(item);
			//if it exists index2 stores the position 
			//of where it is in cart
			if(index2>=0){
				//store the info from cart array
				Item itemCart = cart.get(index2);
				//check if we have enough left in our inventory
				if(inv.getQuantity() < quant) {
					//check if we have no stock left
					if(inv.getQuantity()==0) {
						//tell this to the customer
						System.out.println("Sorry, we have no stock left of the item you requested.");
					} else {
						//tell the customer we added what we can
						System.out.println("Sorry, we do not have enough stock.\nYou requested an extra " 
								+ quant + " " + item + "s but we only have " 
								+ (inv.getQuantity())
								+ " left.\nWe have added this amount to your cart.");
						//add quantity onto what the item already had
						itemCart.addQuant(inv.getQuantity());
						//set the new price for the increase in stock
						itemCart.setPrice(inv.getPrice() * itemCart.getQuant());
						//remove stock from our inventory
						inv.removeQuantity(inv.getQuantity());
					}
				} else {
					//otherwise add exactly what the customer wanted
					//tell them it's been added
					System.out.println("We have added an extra " + quant 
							+ " " + item + "s to your cart.");
					//add the extra quantity to the item in cart
					itemCart.addQuant(quant);
					//set the new price of the item in cart
					itemCart.setPrice(inv.getPrice() * itemCart.getQuant());
					//remove quantity from our inventory
					inv.removeQuantity(quant);
				}
			} else {
				//if index2 is less than 0 than
				//the item needs to be added to cart
				//check if we have enough stock in the inventory
				if(inv.getQuantity() < quant) {
					if(inv.getQuantity()==0) {
						//tell the customer we have no stock
						System.out.println("Sorry we have no stock of the required item left.");
					} else {
						//otherwise we don't have enough stock to 
						//fully complete their order
						//tell them this
						System.out.println("Sorry, we do not have enough stock.\nYou requested " 
								+ quant + " " + item 
								+ "s but we only have " + inv.getQuantity()
								+ ".\nWe have added this amount to your cart.");
						//add the new item to cart
						//the quantity for this is the 
						//amount left in inventory
						this.cart.add(new Item(item, inv.getQuantity()));
						//get the latest addition in cart
						//this is done by cart.size()-1
						//set the new price for this
						cart.get(cart.size()-1).setPrice(inv.getPrice() * inv.getQuantity());
						//remove quantity from the inventory
						inv.removeQuantity(inv.getQuantity());
					}
				} else {
					//Otherwise we have enough stock
					//to complete their order
					//tell them this
					System.out.println("We have added " + quant 
							+ " " + item + "s to your cart.");
					//add the new item to cart
					this.cart.add(new Item(item, quant));
					//get the latest addition in cart
					//this is done by cart.size()-1
					//set the new price for this
					cart.get(cart.size()-1).setPrice(inv.getPrice() * quant);
					//remove quantity form the inventory
					inv.removeQuantity(quant);
				}
			}
		} else {
			//Otherwise we don't have the required item
			//tell customer their request did not match
			//our inventory list
			System.out.println("\nThe item you requested:\n" + item 
					+ "\nwas not found in our inventory list." 
					+ "\nWe may have run out of stock, sorry.");
		}
	}

	//remove item method
	public void removeItem(String name, int quant) {
		//see if the item exists in the inventory
		int index = searchInventory(name);
		//if it does index is set to where it is in the list
		//enter if loop if it exists
		if(index>=0) {
			//store information from inventory list
			Inventory inv = invList.get(index);
			//see if the item exists in the cart
			int index2 = searchCart(name);
			//if it does index2 will be a positive integer
			//enter if loop if item exists in cart
			if(index2>=0) {
				//store info from cart
				Item it = cart.get(index2);
				//check if the quantity in the cart is 
				//greater than the quantity that the customer
				//has tried to remove
				//also check for equality
				if(it.getQuant() >= quant) {
					if(it.getQuant()==quant) {
						//check if the customer wants to remove
						//all of the item from cart
						System.out.println("All " + name 
								+ "s have been removed from cart.");
						//remove the item
						cart.remove(it);
					} else {
						//Otherwise remove the amount they want removed
						System.out.println(quant + " " + name 
								+ "s have been removed from your cart.");
						//remove quantity from item
						it.removeQuant(quant);
						//update the price of the item
						it.setPrice(inv.getPrice() * it.getQuant());
						//add quantity to the inventory
						inv.addQuantity(quant);
					}
				} else {
					//Otherwise the customer has tried to remove
					//more stock than what was in the cart
					//tell them this
					System.out.println("You have tried to remove more " 
							+ name + "s than were in your cart." 
							+ "\nYou tried to remove " + quant 
							+ " but only had " + it.getQuant() 
							+ " in your cart. We have removed this amount for you.");
					//remove what was in the cart for them
					cart.remove(it);
				}
			} else {
				//Otherwise they tried to remove an item from the 
				//cart which wasn't there. Tell them this
				System.out.println("You have tried to remove an item "
						+ "from your cart which doesn't exist in the cart." 
						+ "\nThe item was " + name 
						+ ".\nPlease check this and try again.");
			}
		} else {
			//Otherwise they tried to remove an item from
			//the cart which wasn't in our inventory
			//Tell them this
			System.out.println("You have tried to remove an item "
					+ "which didn't match our inventory list." 
					+ "\nThe item was " + name 
					+ ".\nPlease check this and try again.");
		}
	}
	
	//method to search the cart list
	private int searchCart(String item) {
		//sort the cart alphabetically
		Collections.sort(cart);
		//Perform a binary search
		int index = Collections.binarySearch(cart, new Item(item, 0));
		//return where the item is
		return index;
	}
	
	//method to search the inv list
	public int searchInventory(String item) {
		//sort the inv alphabetically
		Collections.sort(invList);
		//Perform a binary search
		int index = Collections.binarySearch(invList, new Inventory("", item, 0, 0, 0));
		//return where the item is
		return index;
	}
	
	//print out details of cart
	public String viewCart() {
		//print name of customer
		//and date the cart was created
		String out = "\n" + date + "  Name: " + custName + "\n";
		//create a variable to hold the total price
		double total = 0;
		//check if the cart has items
		if(cart.size() == 0) {
			//if it doesn't tell the customer 
			//their cart is empty
			out += "Your shopping cart is currently empty.\n";
		} else {
			//if it does cycle through cart
			//tell the customer what is in their cart
			//update total
			for(int i = 0; i < cart.size(); i++) {
				//update out with info from cart
				out += "\n" + cart.get(i);
				//update total by getting price
				total += cart.get(i).getPrice();
			}
			//set up where total should go
			//add this to output
			out += "\n" + String.format("%-12s%-8s%-5s", "", 
					"Total: €", precis.format(total));
		}
		//return string out
		return out;
	}
}
