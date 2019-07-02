package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private Member member;
	private Book book;

	public CheckoutRecord(Member member, Book book) {
		this.member = member;
		this.book = book;
	}

	public Member getMember() {
		return member;
	}
	
	public String getMemberId() {
		return member.getMemberId();
	}
	public Book getBook() {
		return book;
	}
}
