package shoppingCart;
//Mark Fitzgerald 15456198
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main_Inventory {
	
	public static void main(String args[]) throws IOException {
		
		//create a new inventory arrayList
		ArrayList<Inventory> inv = new ArrayList<Inventory>();
		//add all the stock to this arrayList
		inv.add(new Inventory("1000", "Apple", 30, 2.50, 1.25));
		inv.add(new Inventory("1001", "Orange", 40, 2, 1.00));
		inv.add(new Inventory("2001", "Milk", 10, 2.39, 1.50));
		inv.add(new Inventory("2002", "Orange Juice", 20, 1.99, 1.25));
		inv.add(new Inventory("3001", "Blue Cheese", 10, 2.25, 1.50));
		inv.add(new Inventory("3002", "Cheddar", 20, 2.79, 1.60));
		inv.add(new Inventory("4001", "Chocolate", 40, 2.99, 1.70));
		inv.add(new Inventory("4002", "Candy", 30, 0.99, 0.50));
		inv.add(new Inventory("5001", "Beef", 10, 5.00, 3.00));
		inv.add(new Inventory("5002", "Chicken", 10, 4.00, 2.00));
		
		//Serialize the ArrayList
		//create a file output stream object
		FileOutputStream fos = new FileOutputStream("inventory.txt");
		//create an object output stream object
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		//write the arrayList details to it
		oos.writeObject(inv);
		//flush in case the computer fails
		oos.flush();
		//close the object output stream
		oos.close();
		
		//print out the details of the list
		System.out.println("Inventory List:");
		//set up the columns of the list
		System.out.println(String.format("%-7s%-15s%-10s%-7s", "SKU", "Item name", "Quantity", "Price"));
		//cycle through the arrayList to print the inventory stock
		for(int i = 0; i < inv.size(); i++) {
			System.out.println(inv.get(i));
		}

	}
	
}
