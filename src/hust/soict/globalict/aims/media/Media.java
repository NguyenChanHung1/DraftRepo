package hust.soict.globalict.aims.media;

import java.util.Comparator;

public abstract class Media {

	protected int id;
	protected String title;
	protected String category;
	protected float cost;
	
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
	
	public Media() {
		
	}
	
	public Media(String title) {
		super();
		this.title = title;
	}
	
	public Media(String title, String category, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	
	
	public Media(int id, String title, String category, float cost) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	
	public boolean equals(Object o) {
		Media media = (Media) o;
		if(media.title == this.title)
			return true;
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public String toString() {
		return "Media: "+this.id+"\nTitle: "+this.title+"\nCategory: "+this.category+"\nCost: $"+this.cost+"\n\n\n";
	}
}
