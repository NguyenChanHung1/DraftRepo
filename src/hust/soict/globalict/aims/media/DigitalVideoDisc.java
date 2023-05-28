package hust.soict.globalict.aims.media;

public class DigitalVideoDisc extends Disc implements Playable{

	public DigitalVideoDisc() {
	}
	
	public DigitalVideoDisc(String title) {
		super(title);
	}

	public DigitalVideoDisc(String title, String category, float cost) {
		super(title, category, cost);
	}

	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super(title, category, director, cost);
	}

	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title, category, director, length, cost);
	}
	
	public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
		super(title, category, director, length, cost);
		this.id = id;
	}
	
	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}
	
	public String toString() {
		return "Digital Video Disc: "+this.id+"\nTitle: "+this.title+"\nCategory: "+this.category+
				"\nDirector: "+this.director+"\nLength: "+this.length+"\nCost: $"+this.cost+"\n\n\n";
	}
}
