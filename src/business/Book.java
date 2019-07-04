package business;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import sun.awt.www.content.audio.x_aiff;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String isbn;
	private List<Author> authors;
	private boolean availability;
	private int numberOfCopy;
	private int totalNumOfCopy;
	private int maxCheckoutLength;
	private String id;

	public Book(String title, String isbn, List<Author> authors, boolean availability, int numberOfCopy,
			int maxCheckoutLength) {
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.availability = availability;
		this.numberOfCopy = numberOfCopy;
		this.totalNumOfCopy = numberOfCopy;
		this.maxCheckoutLength = maxCheckoutLength;
		this.id = isbn + "-" + numberOfCopy;
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

	public String getAuthorsList() {
		List<Author> authorList = getAuthors();
		String authors = "";
		for (Author author : authorList) {
			authors += author.getFirstName() + " " + author.getLastName() + ", ";
		}
		if (!authors.isEmpty())
			authors = authors.substring(0, authors.length() - 1);
		return authors;
	}

	public boolean isAvailability() {
		return availability;
	}

	public int getNumberOfCopy() {
		return numberOfCopy;
	}

	public int getTotalNumOfCopy() {
		return totalNumOfCopy;
	}

	public void setTotalNumOfCopy(int totalNumOfCopy) {
		this.totalNumOfCopy = totalNumOfCopy;
	}

	public void setNumberOfCopy(int numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		String auth = "";
		for (Author author : authors) {
			auth += author.getFirstName() + " " + author.getLastName() + ",";
		}
		auth = auth.substring(0, auth.length() - 1);
		return sb.append(title).append(":").append(auth).append(":").append(isbn).append(":").append(numberOfCopy)
				.append(":").append(totalNumOfCopy).append(":").append(maxCheckoutLength).append(":").append(id)
				.toString();
	}
}
