package shoppingCart;

import java.util.Comparator;

//new comparator to compare prices
public class PriceComparator<T> implements Comparator<Item> {

	public int compare(Item item1, Item item2) {
		//get the price of the two items
		double price1 = item1.getPrice();
		double price2 = item2.getPrice();
		//compareTo only works with strings
		//change the price to string
		return Double.valueOf(price1).compareTo(Double.valueOf(price2));
	}
}
