package hust.soict.globalict.aims.screen;


import hust.soict.globalict.aims.cart.Cart.Cart;
import hust.soict.globalict.aims.store.Store.Store;

public class AimsScreen{

	protected Cart cart = new Cart();
	
	protected Store store = new Store();
	
	public AimsScreen() {
		
	}
	
	public AimsScreen(Cart cart, Store store) {
		this.cart = cart;
		this.store = store;
	}
	
	public Cart getCart() {
		return this.cart;
	}
	
	public Store getStore() {
		return this.store;
	}

	
	
}
