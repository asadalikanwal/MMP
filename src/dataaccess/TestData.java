package dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import business.AccessLevel;
import business.Address;
import business.Author;
import business.Book;
import business.CheckoutRecord;
//import business.CheckoutRecordEntry;
//import business.LibraryMember;
import business.Member;
import business.User;
import dataaccess.DataAccessFacade.DbType;


public class TestData {
//	List<LibraryMember> members = new ArrayList<LibraryMember>();
//	@SuppressWarnings("serial")
//
//
//	List<Address> addresses = new ArrayList<Address>() {
//		{
//			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
//			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
//			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
//			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
//			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
//			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
//			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
//			add(new Address("501 Central", "Mountain View", "CA", "94707"));
//		}
//	};
//
//	//This is a comment
//	@SuppressWarnings("serial")
//	public List<Author> allAuthors = new ArrayList<Author>() {
//		{
//			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he.", 1));
//			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she.", 2));
//			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts.", 3));
//			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books.", 4));
//			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style.", 5));
//		}
//	};
//
//
//
//	@SuppressWarnings("serial")
//	List<Book> allBooks = new ArrayList<Book>() {
//		{
//			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
//			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
//			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
//			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));
//
//			add(new Book("34-05755", "Times", 7, Arrays.asList(allAuthors.get(4))));
//		    add(new Book("35-00033", "National Geographic", 7, Arrays.asList(allAuthors.get(1))));
//		    add(new Book("36-00087", "Discover Planet", 7, Arrays.asList(allAuthors.get(0))));
//
//		}
//	};
//
//
//
//	@SuppressWarnings("serial")
//	List<User> allUsers = new ArrayList<User>() {
//		{
//			add(new User("librarian", "123", Auth.LIBRARIAN));
//			add(new User("admin", "123", Auth.ADMIN));
//			add(new User("both", "123", Auth.BOTH));
//		}
//	};
//
//	public static void main(String[] args) {
//		TestData td = new TestData();
//		td.bookData();
//		td.libraryMemberData();
//		td.userData();
//		DataAccess da = new DataAccessFacade();
//		System.out.println(da.readBooksMap());
//		System.out.println(da.readUserMap());
//	}
//	///create books
//	public void bookData() {
//	    allBooks.get(0).addCopy();
//	    allBooks.get(0).addCopy();
//	    allBooks.get(0).addCopy();
//	    allBooks.get(1).addCopy();
//	    allBooks.get(2).addCopy();
//	    allBooks.get(2).addCopy();
//	    allBooks.get(2).addCopy();
//	    allBooks.get(3).addCopy();
//	    allBooks.get(4).addCopy();
//	    allBooks.get(4).addCopy();
//	    allBooks.get(5).addCopy();
//	    allBooks.get(5).addCopy();
//	    allBooks.get(6).addCopy();
//	    allBooks.get(6).addCopy();
//		DataAccessFacade.loadBookMap(allBooks);
//	}
//
//	public void userData() {
//		DataAccessFacade.loadUserMap(allUsers);
//	}
//
//
//
//	//create library members
//	public void libraryMemberData() {
//		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
//		members.add(libraryMember);
//		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
//		members.add(libraryMember);
//
//		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
//		members.add(libraryMember);
//
//		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
//		members.add(libraryMember);
//
//		DataAccessFacade.loadMemberMap(members);
//	}
	public static void main(String[] args) {
		// Member
		List<Member> members = new ArrayList<Member>();
		Member member = new Member("Jepeng", "Lu", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"), "12345567", "100");
		members.add(member);
		Member member2 = new Member("Ali", "sdf", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"), "12345567", "101");
		members.add(member2);
		DataAccessFacade daf = new DataAccessFacade();
		daf.saveNewMember(member);
		daf.saveNewMember(member2);
		
		HashMap<String, Member> result = (HashMap<String, Member>)daf.readMemberMap();
		for (Member entry : result.values()) {
			System.out.println(entry.toString());
		}
		
		// Book
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author("J", "K", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"), "371-455-8765", "She is good", "Osker winner!!"));
		authors.add(new Author("J", "Ali", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"), "371-455-8765", "She is good", "Osker winner!!"));
		Book book = new Book("Harry Potter", "12345", authors, true, 7, 7, "500");
		Book book1 = new Book("Harry Potter", "12345", authors, true, 7, 7, "501");
		Book book2 = new Book("Java Progrom", "12345", authors, true, 7, 7, "502");
		Book book3 = new Book("Java Progrom version 2", "12345", authors, true, 7, 7, "503");
		daf.saveNewBook(book);
		daf.saveNewBook(book1);
		daf.saveNewBook(book2);
		daf.saveNewBook(book3);
		HashMap<String, Book> result3 = (HashMap<String, Book>)daf.readBooksMap();
		for (Book entry : result3.values()) {
			System.out.println(entry.toString());
		}
		
		// User
		User user = new User("john", "john123", AccessLevel.ADMINISTRATOR);
		daf.saveUserMap(user);
		HashMap<String, User> result2 = (HashMap<String, User>)daf.readUserMap();
		for(User entries: result2.values()) {
			System.out.println(entries.toString());
		}
		
		// checkout record
	}

}
