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
	public void saveUserMap(User user);

	public HashMap<String, User> readUserMap();
	
	public AccessLevel userLogin(String username, String password);
	
	// member
	public HashMap<String, Member> readMemberMap();
	
	public List<Member> readMember();

	public boolean updateMembers(Member member, String id);

	public void saveNewMember(Member member);
	
	public Member srcMember(String id);

	// book
	public void saveNewBook(Book book);
	
	public boolean addCopy(String isbn, int number);
	
	public Book	searchBook(String isbn);
	
	public HashMap<String, Book> readBooksMap();
	
	public List<Book> readBookList();
	
	public boolean returnBook(String bookId);

	// checkout Records
	public boolean saveCheckoutRecord(CheckoutRecord record);

	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	
	public List<CheckoutRecord> searchMember(String id);
}
