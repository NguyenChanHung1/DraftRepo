package hust.soict.globalict.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{

	private List<String> authors = new ArrayList<String>();
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String title, String category, float cost) {
		super(id, title, category, cost);
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void addAuthor(String authorName) {
		boolean existed = false;
		for(String item : this.authors) {
			if(authorName.equals(item) == true) 
				existed = true;
		}
		if(existed == false) 
			this.authors.add(authorName);
		else 
			System.out.println("Author is already added!");
		
	}
	
	public void removeAuthor(String authorName) {
		boolean existed = this.authors.remove(authorName);
		if(existed == false)
			System.out.println("No such author associated with the book!");
	}
	
	public String authorsToString() {
		String result = "Authors: ";
		for(String au : authors) {
			result = result + au + " - ";
		}
		return result + "\n";
	}
	
	public String toString() {
		return "Book: "+this.id+"\nTitle: "+this.title+"\nCategory: "+this.category+"\n"+
				authorsToString()+"Cost: $"+this.cost+"\n\n\n";
	}
}
