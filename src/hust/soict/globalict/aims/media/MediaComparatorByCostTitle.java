package hust.soict.globalict.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media>{

	public MediaComparatorByCostTitle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Media m1, Media m2) {
		if(m1.cost >= m2.cost)
			return -1;
		else 
			return 1;
	}
}
