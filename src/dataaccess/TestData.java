package dataaccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.AccessLevel;
import business.Address;
import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.Member;
import business.User;

public class TestData {

	public static void main(String[] args) {
		DataAccessFacade daf = new DataAccessFacade();
		
		// User
		User user1 = new User("john", "123", AccessLevel.LIBRARIAN);
		User user2 = new User("mike", "123", AccessLevel.ADMINISTRATOR);
		User user3 = new User("bob", "123", AccessLevel.BOTH);
		daf.saveUserMap(user1);
		daf.saveUserMap(user2);
		daf.saveUserMap(user3);
		HashMap<String, User> users = (HashMap<String, User>) daf.readUserMap();
		for (User entries : users.values()) {
			System.out.println(entries.toString());
		}

		// Member
		List<Member> members = new ArrayList<Member>();
		Member member1 = new Member("Jepeng", "Gao", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"12345567", "100");
		Member member2 = new Member("Ali", "Mohammed", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"12345567", "101");
		Member member3 = new Member("Asad", "Ali", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"12345567", "102");
		Member member4 = new Member("Zayed", "Hussain", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"12345567", "103");
		Member member5 = new Member("Saju", "Ahmad", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"12345567", "104");
		members.add(member1);
		members.add(member2);
		members.add(member3);
		members.add(member4);
		members.add(member5);
		for (Member member : members) {
			daf.saveNewMember(member);
		}

		HashMap<String, Member> result = (HashMap<String, Member>) daf.readMemberMap();
		for (Member entry : result.values()) {
			System.out.println(entry.toString());
		}

//		member = new Member("Honhang", "Lu", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"), "12345567", "100");
//		System.out.println(daf.updateMembers(member));

		// Book
		List<Author> book1Author = new ArrayList<Author>();
		List<Author> book2Author = new ArrayList<Author>();
		List<Author> book3Author = new ArrayList<Author>();
		List<Author> book4Author = new ArrayList<Author>();
		List<Author> book5Author = new ArrayList<Author>();
		List<Author> book6Author = new ArrayList<Author>();
		List<Author> book7Author = new ArrayList<Author>();

		book1Author.add(new Author("Jemes", "Bond", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		book2Author.add(new Author("Steve", "Jobs", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		book3Author.add(new Author("Elan", "Musk", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		book4Author.add(new Author("Anna", "Bel", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		book5Author.add(new Author("Will", "Smith", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		book6Author.add(new Author("Hugh", "Klien", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		book7Author.add(new Author("Tom", "Brady", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		Book book1 = new Book("Harry Potter 1", "23-11451", book1Author, true, 3, 21, "500");
		Book book2 = new Book("Harry Potter 2", "23-11452", book2Author, true, 3, 21, "501");
		Book book3 = new Book("FPP Book", "23-11453", book3Author, true, 1, 21, "502");
		Book book4 = new Book("MPP Book", "23-11454", book4Author, true, 1, 21, "503");
		Book book5 = new Book("National Geography", "23-11455", book5Author, true, 2, 7, "505");
		Book book6 = new Book("Peoples Magazin", "23-11456", book6Author, true, 2, 7, "506");
		Book book7 = new Book("Home Jurnal", "23-11457", book7Author, true, 2, -1, "507");
		daf.saveNewBook(book1);
		daf.saveNewBook(book2);
		daf.saveNewBook(book3);
		daf.saveNewBook(book4);
		daf.saveNewBook(book5);
		daf.saveNewBook(book6);
		daf.saveNewBook(book7);

		HashMap<String, Book> result3 = (HashMap<String, Book>) daf.readBooksMap();
		for (Book entry : result3.values()) {
			System.out.println(entry.toString());
		}

		// checkout record
		CheckoutRecord checkoutRecord1 = new CheckoutRecord(member1, book1);
		CheckoutRecord checkoutRecord2 = new CheckoutRecord(member1, book5);
		CheckoutRecord checkoutRecord3 = new CheckoutRecord(member2, book2);
		CheckoutRecord checkoutRecord4 = new CheckoutRecord(member2, book6);
		CheckoutRecord checkoutRecord5 = new CheckoutRecord(member3, book3);
		CheckoutRecord checkoutRecord6 = new CheckoutRecord(member3, book7);
		CheckoutRecord checkoutRecord7 = new CheckoutRecord(member4, book4);
		CheckoutRecord checkoutRecord8 = new CheckoutRecord(member4, book7);
		daf.saveCheckoutRecord(checkoutRecord1);
		daf.saveCheckoutRecord(checkoutRecord2);
		daf.saveCheckoutRecord(checkoutRecord3);
		daf.saveCheckoutRecord(checkoutRecord4);
		daf.saveCheckoutRecord(checkoutRecord5);
		daf.saveCheckoutRecord(checkoutRecord6);
		daf.saveCheckoutRecord(checkoutRecord7);
		daf.saveCheckoutRecord(checkoutRecord8);

		HashMap<String, CheckoutRecord> result4 = (HashMap<String, CheckoutRecord>) daf.readCheckoutRecordMap();
		for (CheckoutRecord entries : result4.values()) {
			System.out.println(entries.toString());
		}

	}
}
