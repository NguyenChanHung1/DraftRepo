package hust.soict.globalict.aims.media;

public class Track implements Playable{

	private String title;
	private int length;
	
	public Track() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Track(String title, int length) {
		super();
		this.title = title;
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}
	
	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}
	
	public boolean equals(Object o) {
		Track track = (Track) o;
		if(track.title == this.title && track.length == this.length)
			return true;
		return false;
	}
}
