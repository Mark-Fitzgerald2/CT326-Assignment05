package shoppingCart;
//Mark Fitzgerald 15456198
import java.io.IOException;
import java.util.Collections;

public class Main_ShoppingCart {
	
	public static void main(String args[]) throws ClassNotFoundException, IOException {
		//call the main inventory so this prints out the inventory
		Main_Inventory.main(args);
		//Create two new shopping carts
		ShoppingCart cart1 = new ShoppingCart("Mark", "17/10/2017");
		ShoppingCart cart2 = new ShoppingCart("Daniel", "15/10/2017");
		
		//print the cart before we have added anything
		System.out.println(cart1.viewCart());
		//add all the required items
		cart1.addItem("Apple", 2);
		cart1.addItem("Orange", 5);
		cart1.addItem("Milk", 2);
		cart1.addItem("Blue Cheese", 4);
		cart1.addItem("Candy", 25);
		//remove 5 candy
		cart1.removeItem("Candy", 5);
		//print out the cart after these additions
		System.out.println(cart1.viewCart());

		//print the second cart
		System.out.println("\n" + cart2.viewCart());
		//add all necessary items
		cart2.addItem("Apple", 2);
		cart2.addItem("Orange", 5);
		cart2.addItem("Milk", 2);
		cart2.addItem("Blue Cheese", 4);
		cart2.addItem("Cheddar", 3);
		cart2.addItem("Beef", 6);
		cart2.addItem("Candy", 20);
		cart2.addItem("Chocolate", 10);
		cart2.addItem("Chicken", 2);
		//remove chocolates and one blue cheese
		cart2.removeItem("Chocolate", 5);
		cart2.removeItem("Blue Cheese", 1);
		//print out the second shopping cart
		System.out.println(cart2.viewCart());
		
		//shuffle cart2
		Collections.shuffle(cart2.cart);
		//print out the shuffled cart
		System.out.println("\nShuffled Cart:");
		System.out.println(cart2.viewCart());
		
		//sort cart2 according price
		//a new class was created as compareTo method was already used in Item
		//the compareTo method in Item compared name
		//therefore we needed to set up a new one for price
		Collections.sort(cart2.cart, new PriceComparator<Item>());
		//print out the sorted cart
		System.out.println("\nSorted Cart:");
		System.out.println(cart2.viewCart());
		
	}

}
