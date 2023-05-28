package hust.soict.globalict.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable{
	
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	
	public CompactDisc() {
		// TODO Auto-generated constructor stub
		super();
	}

	public CompactDisc(String artist) {
		super();
		this.artist = artist;
	}
	
	public CompactDisc(String title, String category, String director, float cost) {
		super(title, category, director, cost);
	}
	
	public CompactDisc(String title, String category, String director, float cost, String artist) {
		super(title, category, director, cost);
		this.artist = artist;
	}
	
	public CompactDisc(Integer id, String title, String category, String director, float cost, String artist) {
		super(title, category, director, cost);
		this.artist = artist;
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}
	
	public void addTrack(Track track) {
		boolean existed = this.tracks.contains(track);
		if(existed == true) {
			System.out.println("The input track is already in the list of tracks!");
		}
		else {
			this.tracks.add(track);
			System.out.println("The track is added successfully!");
		}
	}
	
	public void removeTrack(Track track) {
		boolean existed = this.tracks.contains(track);
		if(existed == true) {
			this.tracks.remove(track);
			System.out.println("The input track existed in the list of tracks.\nTrack deleted successfully!");
		}
		else {
			System.out.println("The track doesn't exist in the list!");
		}
	}
	
	public int getLength() {
		int total_length = 0;
		for(Track tr : this.tracks) 
			total_length += tr.getLength();
		return total_length;
	}
	
	public void play() {
		System.out.println("Now playing CD: " + this.getTitle());
		for(Track tr : this.tracks) {
			tr.play();
		}
	}
	
	public String tracksToString() {
		String result = "Track List:\n";
		for(Track tr : tracks) {
			result += "\t";
			result = result + "Track title: " + tr.getTitle() + "  Length: " + tr.getLength() + "\n";
		}
		return result;
	}
	
	public String toString() {
		return "Compact Disc: "+this.id+"\nTitle: "+this.title+"\nCategory: "+this.category+
				"\nDirector: "+this.director+"\nLength: "+this.length+"\n"+tracksToString()+
				"Cost: $"+this.cost+"\n\n\n";
	}
}
