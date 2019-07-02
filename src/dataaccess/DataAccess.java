package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckoutRecord;
import business.Member;
import business.User;

public interface DataAccess {

	// member
	public HashMap<String, Member> readMemberMap();

	public boolean updateMembers(Member member);

	public void saveNewMember(Member member);

	// book
	public void saveNewBook(Book book);

	public HashMap<String, Book> readBooksMap();
	
	public boolean returnBook(String bookId);

	// user
	public void saveUserMap(User user);

	public HashMap<String, User> readUserMap();

	// checkout Records
	public boolean saveCheckoutRecord(CheckoutRecord record);

	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
}
