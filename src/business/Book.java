package business;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String isbn;
	private List<Author> authors;
	private boolean availability;
	private int numberOfCopy;
	public void setNumberOfCopy(int numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}

	private int maxCheckoutLength;
	private String id;

	public Book(String title, String isbn, List<Author> authors, boolean availability, int numberOfCopy,
			int maxCheckoutLength, String id) {
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.availability = availability;
		this.numberOfCopy = numberOfCopy;
		this.maxCheckoutLength = maxCheckoutLength;
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public boolean isAvailability() {
		return availability;
	}

	public int getNumberOfCopy() {
		return numberOfCopy;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public String getId() {
		return id;
	}
	
	public String toString() {
		String string = "";
		for (Author author : authors) {
			string += author.getFirstName() +", "+ author.getLastName();
		}
		return title + "'s author is " + string;
	}
}
