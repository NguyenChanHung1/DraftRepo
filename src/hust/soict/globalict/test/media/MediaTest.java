package hust.soict.globalict.test.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Track;

public class MediaTest {

	public static void main(String[] args) {
		CompactDisc cd = new CompactDisc("Where The Light Goes", "Rock", "Atlantic Records", 14.49f, "Matchbox Twenty");
		Track track1 = new Track("No Other Love", 4);
		Track track2 = new Track("I Know Better", 4);
		Track track3 = new Track("Friends", 5);
		Track track4 = new Track("Where The Light Goes", 4);
		cd.addTrack(track1); 
		cd.addTrack(track2); 
		cd.addTrack(track3); 
		cd.addTrack(track4);
		
		DigitalVideoDisc dvd = new DigitalVideoDisc("Yellowstone: Season 5", "Movie Series", "Kevin Costner", 45, 19.96f);
		
		Book book = new Book(3, "Fourth Wing", "Fantasy", 16.99f);
		book.addAuthor("Rebecca Yarros");
		book.addAuthor("Melinda Kinsey");
		book.addAuthor("Christine Ruth");
		
		List<Media> mediae = new ArrayList<Media>();
		
		mediae.add(cd);
		mediae.add(dvd);
		mediae.add(book);
		
		for(Media m : mediae) {
			System.out.println(m.toString());
		}
		
		System.out.println(mediae);
		Collections.sort(mediae, Media.COMPARE_BY_COST_TITLE);
		System.out.println(mediae);
		Collections.sort(mediae, Media.COMPARE_BY_TITLE_COST);
		System.out.println(mediae);

	}

}
