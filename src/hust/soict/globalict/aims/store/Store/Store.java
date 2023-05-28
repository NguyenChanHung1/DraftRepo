package hust.soict.globalict.aims.store.Store;
import java.util.ArrayList;

import hust.soict.globalict.aims.media.Media;

public class Store {
	ArrayList<Media> itemsInStore;
	
	public Store() {
		itemsInStore = new ArrayList<Media> ();
	}
	
	public void addMedia(Media media) {
		itemsInStore.add(media);
	}
	
	public void removeMedia(Media media) {
		itemsInStore.remove(media);
	}
	
	public ArrayList<Media> getItemsInStore(){
		return itemsInStore;
	}
	
	public void printStore() {
		for (int i = 0; i < itemsInStore.size(); i++) {
			System.out.println((i+1)+". Media - "+itemsInStore.get(i).getId()+
					" - "+itemsInStore.get(i).getTitle()+
					" - "+itemsInStore.get(i).getCategory()+
					" - "+itemsInStore.get(i).getCost()+"$ ");
		}
	}
}
