package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private Member member;
	private Book book;
	private LocalDate dueDate;
	private boolean returnStatus;
	private String recordId;

	public CheckoutRecord(Member member, Book book) {
		this.member = member;
		this.book = book;
		LocalDate todayDate = LocalDate.now();
		this.dueDate = todayDate.plusDays(book.getMaxCheckoutLength());
		returnStatus = false;
		recordId = member.getMemberId() + book.getId();
	}

	public Member getMember() {
		return member;
	}

	public String getRecordId() {
		return recordId;
	}

	public Book getBook() {
		return book;
	}

	public boolean isReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(boolean returnStatus) {
		this.returnStatus = returnStatus;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getMemberID() {
		return member.getMemberId();
	}

	public String getMemberName() {
		return member.getFullName();
	}

	public String getBookTitle() {
		return book.getTitle();
	}

	public String getBookISBN() {
		return book.getIsbn();
	}

	public String getBookId() {
		return book.getId();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(getMember()).append(":").append(getBook()).append(":").append(getDueDate()).append(":")
				.append(returnStatus).append(":").append(recordId).toString();
	}
}
