package business;

import java.io.Serializable;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Book book;

	public BookCopy(String id, Book book) {
		this.id = id;
		this.book = book;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}
}
