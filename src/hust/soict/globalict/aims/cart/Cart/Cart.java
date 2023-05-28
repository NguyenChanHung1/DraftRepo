package hust.soict.globalict.aims.cart.Cart;
import java.util.ArrayList;

import hust.soict.globalict.aims.media.Media;

public class Cart {
	private static final int MAX_NUMBERS_ORDERED = 20;
	private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
		
	public Cart() {
		super();
	}
	
	public ArrayList<Media> getItemsOrdered() {
		return itemsOrdered;
	}
	
	public boolean isInCart(Media media) {
		return itemsOrdered.contains(media);
	}
	
	public void addMedia(Media media) {
		if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
			System.out.println("Cart reaches maximum quantity of products!");
			return;
		}
		else {
			if(isInCart(media) == true) {
				System.out.println("The media already existed in the cart!");
			}
			else {
				itemsOrdered.add(media);
				System.out.println("Product has been added successfully!");
			}			
		}
	}
	
	public void removeMedia(Media media) {
		if (itemsOrdered.size() <= 0) {
			System.out.println("Cart empty, cannot perform products removal!");
			return;
		}
		else {
			if(isInCart(media) == true) {
				System.out.println("The media is existing in the cart!");
				itemsOrdered.remove(media);
				System.out.println("Product has been deleted successfully!");
			}
			else {
				System.out.println("No product found!");
			}	
		}
	}
	
	public float totalCost() {
		float total_cost = 0;
		for (int i=0; i<itemsOrdered.size(); i++) {
			total_cost += itemsOrdered.get(i).getCost();
		}
		return total_cost;
	}
	
	public void addMedia( Media[] mediaList) {
		if (mediaList.length >= MAX_NUMBERS_ORDERED - itemsOrdered.size()) {
			System.out.println("Cart reaches maximum quantity of products!");
			return;
		}
		else {
			for(Media media : mediaList) {
				if(isInCart(media) == false) {
					itemsOrdered.add(media);
				}
			}
			System.out.println("Product list has been added successfully!");
		}
	}
	
	public void addMedia(Media media1, Media media2) {
		if (MAX_NUMBERS_ORDERED - itemsOrdered.size() < 2) {
			System.out.println("Cart reaches maximum quantity of products!");
			return;
		}
		else {
			if(isInCart(media1)) {
				itemsOrdered.add(media1);
			}
			if(isInCart(media2)) {
				itemsOrdered.add(media2);
			}
			
			System.out.println("Products have been added successfully!");
		}
	}
	
	public void print() {
		System.out.println("***********************CART***********************");
		System.out.println("Ordered Items:");
		for (int i = 0; i < itemsOrdered.size(); i++) {
			System.out.println((i+1) + ". Media - "+itemsOrdered.get(i).getId()+
					" - "+itemsOrdered.get(i).getTitle()+
					" - "+itemsOrdered.get(i).getCategory()+
					" - "+itemsOrdered.get(i).getCost()+"$ ");
		}
		System.out.println("Total cost: "+totalCost());
		System.out.println("***************************************************");
	}
	
	public void searchById(int id) {
		int idx = id - 1;
		if (idx < 0 || idx >= itemsOrdered.size()) {
			System.out.println("No match found!");
		}
		else {
			System.out.println(id + ". Media - "+itemsOrdered.get(idx).getId()+
					" - "+itemsOrdered.get(idx).getTitle()+
					" - "+itemsOrdered.get(idx).getCategory()+
					" - "+itemsOrdered.get(idx).getCost()+"$ ");
		}
	}
	
	public void searchByTitle(String title) {
		boolean found = false;
		for (int i=0; i<itemsOrdered.size(); i++) {
			if (itemsOrdered.get(i).getTitle() == title) {
				System.out.println((i+1)+". Media - "+itemsOrdered.get(i).getId()+
						" - "+itemsOrdered.get(i).getTitle()+
						" - "+itemsOrdered.get(i).getCategory()+
						" - "+itemsOrdered.get(i).getCost()+"$ ");
				found = true;
			}
		}
		if (found == false) System.out.println("No match found!"); 	
	}
}
