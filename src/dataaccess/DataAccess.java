package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.AccessLevel;
import business.Book;
import business.CheckoutRecord;
import business.Member;
import business.User;

public interface DataAccess {
	
	// user
	public HashMap<String, User> getUsers();
	
	public void saveUserMap(User user);

	public HashMap<String, User> readUserMap();
	
	public AccessLevel userLogin(String username, String password);
	
	// member
	public HashMap<String, Member> getMembers();
	
	public HashMap<String, Member> readMemberMap();

	public boolean updateMembers(Member member);

	public void saveNewMember(Member member);
	
	public List<CheckoutRecord> searchMember(String id);

	// book
	public HashMap<String, Book> getBooks();
	
	public void saveNewBook(Book book);
	
	public boolean addCopy(String isbn, int number);
	
	public Book	searchBook(String isbn);
	
	public HashMap<String, Book> readBooksMap();
	
	public boolean returnBook(String bookId);

	// checkout Records
	public HashMap<String, CheckoutRecord> getCheckoutRecords();
	
	public boolean saveCheckoutRecord(CheckoutRecord record);

	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
}
