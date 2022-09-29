package tumtraining.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022.02 Informatik Q-7: Secondhand books
 * 
 * Example input:
 * 
 * <pre>
 * String[] args = new String[] { "Klassiker;Faust. Eine Tragödie;Johann Wolfgang von Goethe;10.00",
 * 		"Informatik;Weapons of Math Destruction;Cathy ONeil;18.00" };
 * </pre>
 * 
 * @author supo
 * 
 */
public class Library {

	public static void main(String[] args) {

		String[] books = new String[]{
				"Klassiker;Faust. Eine Tragödie;Johann Wolfgang von Goethe;10.00",
				"Informatik;Weapons of Math;Alexander Pretschner;18.00",
				"Informatik;Weapons of Math Destruction;Cathy ONeil;12.00",
				"Klassiker;Math Destruction;Cathy ONeil;18.00",
				"Informatik;book1;Alexander Pretschner;22.00",
				"Klassiker;Weapons of Math Destruction;Cathy ONeil;38.00",
				"Klassiker;Weapons of Math Destruction;Cathy ONeil;10.00",
				"Informatik;Another Book;Alexander Pretschner;15.00"};
		
		Map<String, ArrayList<Book>> genreMap = new HashMap<String, ArrayList<Book>>();
		
		for (String book : books) {
			String[] attrs = book.split(";");
			// String[] attr = new String[] {"Klassiker","Faust. Eine Tragödie","Johann Wolfgang von Goethe","10.00"};
			Book b = new Book(attrs[0], attrs[1], attrs[2], Double.parseDouble(attrs[3]));
			
			if (!genreMap.containsKey(b.genre)) {
				genreMap.put(b.genre, new ArrayList<Book>());
			}
			
			genreMap.get(b.genre).add(b);
		}
		
		ArrayList<Book> lst = genreMap.get("Informatik");
		lst.sort(null);
		
		for (Book b : lst) {
			if ("Alexander Pretschner".equals(b.author))
			System.out.println(b.title);
		}
		
	}

}

class Book implements Comparable<Book>{
	String genre;
	String title;
	String author;
	double price;
	
	public Book(String genre, String title, String author, double price) {
		super();
		this.genre = genre;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	@Override
	public int compareTo(Book o) {
		return this.title.compareToIgnoreCase(o.title);
	}
}
