package hust.soict.globalict.aims.Aims;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

import hust.soict.globalict.aims.cart.Cart.Cart;
import hust.soict.globalict.aims.media.*;
import hust.soict.globalict.aims.store.Store.Store;

public class Aims {
	
	private static Store store = new Store();
	private static Cart cart = new Cart();
	private static Scanner scanner;
	
	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
	}
	
	public static void displayStore() {
		store.printStore();
	}
	
	public static void storeMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. See a media’s details");
		System.out.println("2. Add a media to cart");
		System.out.println("3. Play a media");
		System.out.println("4. See current cart");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
	}
	
	public static Media findMediaByTitleInStore(String title) {
		ArrayList<Media> mediasInStore = store.getItemsInStore();
		for(Media media : mediasInStore) {
			if(media.getTitle().compareTo(title) == 0)
				return media;
		}
		return null;
	}
	
	public static Media findMediaByTitleInCart(String title) {
		ArrayList<Media> mediasInCart = cart.getItemsOrdered();
		for(Media media : mediasInCart) {
			if(media.getTitle().compareTo(title) == 0)
				return media;
		}
		return null;
	}
	
	public static Media seeMediaDetails() {
		System.out.println("Please enter a title: ");
		scanner = new Scanner(System.in);
		String title = scanner.nextLine();
		Media mediaFounded = findMediaByTitleInStore(title);
		if(mediaFounded == null) 
			System.out.println("Cannot find the media with title " + title);
		else
			mediaFounded.toString();
		scanner.close();
		try {
			return mediaFounded;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public static void mediaDetailsMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add to cart");
		System.out.println("2. Play");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}
	
	public static void addToCart(Media addedMedia) {
		cart.addMedia(addedMedia);
	}
	
	public static void play(Media playedMedia) {
		String type = (String)(Object)playedMedia.getClass().getSimpleName();
		if(type == "DigitalVideoDisc") {
			DigitalVideoDisc playedDVD = (DigitalVideoDisc) playedMedia;
			playedDVD.play();
		}
		else if(type == "CompactDisc") {
			CompactDisc playedCD = (CompactDisc) playedMedia;
			playedCD.play();
		}
		else {
			System.out.println("Books couldn't be played!");
		}
	}
	
	public static int countDVDInCart() {
		int count = 0;
		ArrayList<Media> mediasInStore = store.getItemsInStore();
		for(Media media : mediasInStore) {
			if(cart.isInCart(media) == true)
				count++;
		}
		return count;
	}
	
	public static void addAMediaToCart() {
		System.out.println("Please enter a title: ");
		scanner = new Scanner(System.in);
		String title = scanner.nextLine();
		Media mediaFounded = findMediaByTitleInStore(title);
		if(mediaFounded == null) 
			System.out.println("Cannot find the media with title " + title);
		else {
			addToCart(mediaFounded);
			String type = (String)(Object)mediaFounded.getClass().getSimpleName();
			if(type == "DigitalVideoDisc")
				System.out.println("Number of DVDs added to the cart: " + countDVDInCart());
		}
		scanner.close();
	}
	
	public static void playAMedia(String where) {
		System.out.println("Please enter a title: ");
		scanner = new Scanner(System.in);
		String title = scanner.nextLine();
		
		
		Media playedMedia = findMediaByTitleInStore(title);
		if(where == "cart")
			playedMedia = findMediaByTitleInCart(title);
		if(playedMedia == null){
			System.out.println("Cannot find the media with title "+title);
			scanner.close();
			return;
		}
		String type = (String)(Object)playedMedia.getClass().getSimpleName();
		if(type == "DigitalVideoDisc") {
			DigitalVideoDisc playedDVD = (DigitalVideoDisc) playedMedia;
			playedDVD.play();
		}
		else if(type == "CompactDisc") {
			CompactDisc playedCD = (CompactDisc) playedMedia;
			playedCD.play();
		}
		else {
			System.out.println("Books couldn't be played!");
		}
		scanner.close();
	}
	
	public static void UpdateStore() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add a media in store");
		System.out.println("2. Remove a media in store");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}
	
	public static void UpdateBookQuery() {
		scanner = new Scanner(System.in);
		System.out.println("Please enter id: ");
		int id = scanner.nextInt();
		System.out.println("Please enter title: ");
		String title = scanner.nextLine();
		System.out.println("Please enter category: ");
		String category = scanner.nextLine();
		System.out.println("Please enter cost: ");
		float cost = scanner.nextFloat();
			
		Book newBook = new Book(id, title, category, cost);
		System.out.println("How many authors?: ");
		int num_authors = scanner.nextInt();
		for(int i=0; i<num_authors; i++) {
			String author = scanner.nextLine();
			newBook.addAuthor(author);	
		}
		store.addMedia(newBook);
			
		
		scanner.close();
	}
	
	public static void UpdateDigitalVideoDiscQuery() {
		scanner = new Scanner(System.in);
		System.out.println("Please enter id: ");
		int id = scanner.nextInt();
		System.out.println("Please enter title: ");
		String title = scanner.nextLine();
		System.out.println("Please enter category: ");
		String category = scanner.nextLine();
		System.out.println("Please enter cost: ");
		float cost = scanner.nextFloat();
		
		System.out.println("Please enter director: ");
		String director = scanner.nextLine();
		System.out.println("Please enter length: ");
		int length = scanner.nextInt();
		DigitalVideoDisc newDVD = new DigitalVideoDisc(id, title, category, director, length, cost);
		store.addMedia(newDVD);
		scanner.close();
	}
	
	public static void UpdateCompactDiscQuery() {
		scanner = new Scanner(System.in);
		System.out.println("Please enter id: ");
		int id = scanner.nextInt();
		System.out.println("Please enter title: ");
		String title = scanner.nextLine();
		System.out.println("Please enter category: ");
		String category = scanner.nextLine();
		System.out.println("Please enter cost: ");
		float cost = scanner.nextFloat();
		
		System.out.println("Please enter director: ");
		String director = scanner.nextLine();
		System.out.println("Please enter artist: ");
		String artist = scanner.nextLine();
		CompactDisc newCD = new CompactDisc(id, title, category, director, cost, artist);
		System.out.println("How many tracks?: ");
		int num_tracks = scanner.nextInt();
		for(int i=0; i<num_tracks; i++) {
			System.out.println("Please enter track title: ");
			String track_title = scanner.nextLine();
			System.out.println("Please enter artist: ");
			int track_length = scanner.nextInt();
			Track newTrack = new Track(track_title, track_length);
			newCD.addTrack(newTrack);
		}
		store.addMedia(newCD);
		scanner.close();
	}
	
	public static int UpdateItemsInStore() {
		scanner = new Scanner(System.in);
		int opt = scanner.nextInt();
		if(opt == 1) {
			System.out.println("Choose type of media you want to add:\n"
					+ "1-Book, 2-CompactDisc, 3-DigitalVideoDisc\nEnter a number: (1-2-3) ");
			int type = scanner.nextInt();
			switch(type) {
				case 1:
					UpdateBookQuery();
					break;
				case 2:
					UpdateCompactDiscQuery();
					break;
				case 3:
					UpdateDigitalVideoDiscQuery();
					break;
				default:
					break;
			}
		}
		else {
			removeAMediaFromStore();
		}
		System.out.println("Do you want to continue updating?:(0-No, 1-Yes) ");
		int c = scanner.nextInt();
		scanner.close();
		return c;
	}
	
	public static void seeCurrentCart() {
		cart.print();
	}
	
	public static void cartMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter medias in cart");
		System.out.println("2. Sort medias in cart");
		System.out.println("3. Remove media from cart");
		System.out.println("4. Play a media");
		System.out.println("5. Place order");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4-5");
	}
	
	public static void filterMediasInCart() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter by id");
		System.out.println("2. Filter by title");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
		scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		if(option == 1) {
			int id = scanner.nextInt();
			cart.searchById(id);
		}
		else if(option == 2) {
			String title = scanner.nextLine();
			cart.searchByTitle(title);
		}
		scanner.close();
	}
	
	public static void sortMediasInCart() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Sort by title");
		System.out.println("2. Sort by cost");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		if(option == 1) {
			Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_TITLE_COST);
		}
		else if(option == 2) {
			Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_COST_TITLE);
		}
		scanner.close();
	}
	
	public static void removeAMediaFromCart() {
		System.out.println("Please enter a title: ");
		scanner = new Scanner(System.in);
		String title = scanner.nextLine();
		Media removedMedia = findMediaByTitleInCart(title);
		if(removedMedia == null) {
			System.out.println("Cannot find the media with title: "+title);
			scanner.close();
			return;
		}
		cart.removeMedia(removedMedia);
		scanner.close();
	}
	
	public static void removeAMediaFromStore() {
		System.out.println("Please enter a title: ");
		scanner = new Scanner(System.in);
		String title = scanner.nextLine();
		Media removedMedia = findMediaByTitleInStore(title);
		if(removedMedia == null) {
			System.out.println("Cannot find the media with title: "+title);
			scanner.close();
			return;
		}
		store.removeMedia(removedMedia);
		scanner.close();
	}

	public static void placeOrder() {
		cart.getItemsOrdered().clear();
		System.out.println("An order is created. The current cart is empty.");
	}
	
	public static void run() {
		int option = -1;
		scanner = new Scanner(System.in);
		while (true) {
			showMenu();
			option = scanner.nextInt();
			if(option == 0)
				break;
			else if (option == 1) 
				while(true) {
					displayStore();
					storeMenu();
					option = scanner.nextInt();
					if(option == 0)
						break;

					switch(option) {
						case 1:
							Media mediaDetail = seeMediaDetails();
							if(mediaDetail == null)
								break;
							mediaDetailsMenu();
							if(!scanner.hasNext()) {
								int opt = scanner.nextInt();
								if(opt == 1)
									addToCart(mediaDetail);
								if(opt == 2)
									play(mediaDetail);
							}
							break;
						case 2:
							addAMediaToCart();
							break;
						case 3:
							playAMedia("store");
							break;
						case 4:
							seeCurrentCart();
							break;
						default:
							break;
					}
				}
			else if(option == 2)
				while(true) {
					UpdateStore();
					int opt3 = scanner.nextInt();
					if(opt3 == 0)
						break;
					int cont = UpdateItemsInStore();
					if(cont == 0)
						break;
				}
			else if(option == 3) 
				while(true) {
					seeCurrentCart();
					cartMenu();
					int opt2 = scanner.nextInt();
					switch (opt2) {
						case 1: 
							filterMediasInCart();
							break;
						case 2:
							sortMediasInCart();
							break;
						case 3:
							removeAMediaFromCart();
							break;
						case 4:
							playAMedia("cart");
							break;
						case 5:
							placeOrder();
							break;
						default:
							break;
					}
				}
			
				
			
		}
		scanner.close();
	}
	
	public static void main(String[] args) {
		run();
	}

}
