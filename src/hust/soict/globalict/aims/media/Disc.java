package hust.soict.globalict.aims.media;

public class Disc extends Media{

	protected String director;
	protected int length;
	
	public Disc() {
		super();
	}
	
	public Disc(String title) {
		super(title);
	}
	
	public Disc(String title, String category, float cost) {
		super(title, category, cost);
	}
	
	public Disc(String title, String category, String director, float cost) {
		super(title, category, cost);
		this.director = director;
	}
	
	public Disc(String title, String category, String director, int length, float cost) {
		super(title, category, cost);
		this.director = director;
		this.length = length;
	}
	
	public Disc(String director, int length) {
		super();
		this.director = director;
		this.length = length;
	}

	public String getDirector() {
		return director;
	}

	public int getLength() {
		return length;
	}

	public String toString() {
		return "Disc: "+this.id+"\nTitle: "+this.title+"\nCategory: "+this.category+
				"\nDirector: "+this.director+"\nLength: "+this.length+"\nCost: $"+this.cost+"\n\n\n";
	}
}
