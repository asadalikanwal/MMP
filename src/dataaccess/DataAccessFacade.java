package dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.AccessLevel;
import business.Book;
import business.CheckoutRecord;
import business.Member;
import business.User;

public class DataAccessFacade implements DataAccess {
	public enum DbType {
		BOOKS, MEMBERS, USERS, CHECKOUTRECORD
	}

	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "//src//dataaccess//storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	private HashMap<String, Book> books;
	private HashMap<String, CheckoutRecord> checkoutRecords;
	private HashMap<String, User> users;
	private HashMap<String, Member> members;
	private int defaulId = 100;
	
	public DataAccessFacade() {
		getBooks();
		getUsers();
		getMembers();
		getCheckoutRecords();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Book> getBooks() {
		if (books == null) {
			books = (HashMap<String, Book>) readFromStorage(DbType.BOOKS);
		}
		return books;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, User> getUsers() {
		if (users == null) {
			users = (HashMap<String, User>) readFromStorage(DbType.USERS);
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Member> getMembers() {
		if (members == null) {
			members = (HashMap<String, Member>) readFromStorage(DbType.MEMBERS);
		}
		return members;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, CheckoutRecord> getCheckoutRecords() {
		if (checkoutRecords == null) {
			checkoutRecords = (HashMap<String, CheckoutRecord>) readFromStorage(DbType.CHECKOUTRECORD);
		}
		return checkoutRecords;
	}

	static void writeToStorage(DbType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Object temp = readFromStorage(type);
			if (type == DbType.MEMBERS) {
				@SuppressWarnings("unchecked")
				HashMap<String, Member> map = (temp != null) ? (HashMap<String, Member>)temp : new HashMap<String, Member>();
				Member member = (Member) ob;
				map.put(member.getMemberId(), member);
				ob = map;
			} else if (type == DbType.BOOKS) {
				@SuppressWarnings("unchecked")
				HashMap<String, Book> map = (temp != null) ? (HashMap<String, Book>)temp : new HashMap<String, Book>();
				Book book = (Book) ob;
				map.put(book.getId(), book);
				ob = map;
			} else if (type == DbType.USERS) {
				@SuppressWarnings("unchecked")
				HashMap<String, User> map = (temp != null) ? (HashMap<String, User>)temp : new HashMap<String, User>();
				User user = (User) ob;
				map.put(user.getUsername(), user);
				ob = map;
			} else if (type == DbType.CHECKOUTRECORD) {
				@SuppressWarnings("unchecked")
				HashMap<String, CheckoutRecord> map = (temp != null) ? (HashMap<String, CheckoutRecord>)temp : new HashMap<String, CheckoutRecord>();
				CheckoutRecord record = (CheckoutRecord) ob;
				map.put(record.getRecordId(), record);
				ob = map;
			}
			
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	static Object readFromStorage(DbType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch (Exception e) {
			System.out.println("creating new file " + type.toString());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	// book
	@Override
	public void saveNewBook(Book book) {
		writeToStorage(DbType.BOOKS, book);
	}

	@Override
	public HashMap<String, Book> readBooksMap() {
		return getBooks();
	}

	@Override
	public boolean addCopy(String isbn, int number) {
		Book book = searchBook(isbn);
		if (book != null) {
			book.setNumberOfCopy(book.getNumberOfCopy() + number);
			book.setTotalNumOfCopy(book.getTotalNumOfCopy() + number);
			saveNewBook(book);
			return true;
		}
		return false;
	}

	@Override
	public Book	searchBook(String isbn) {
		HashMap<String, Book> books = getBooks();
		for (Book book : books.values()) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}

	// user
	@Override
	public HashMap<String, User> readUserMap() {
		return getUsers();
	}

	@Override
	public void saveUserMap(User user) {
		writeToStorage(DbType.USERS, user);
	}

	@Override
	public AccessLevel userLogin(String username, String password) {
		HashMap<String, User> users = readUserMap();
		User user = users.get(username);
		if (user != null && user.getPassword().equals(password)) {
			return user.getAccessLevel();
		}
		return AccessLevel.NONE;
	}

	// member
	@Override
	public HashMap<String, Member> readMemberMap() {
		return getMembers();
	}

	@Override
	public void saveNewMember(Member member) {
		member.setMemberId(String.valueOf(defaulId++));
		writeToStorage(DbType.MEMBERS, member);
		getMembers();
	}

	@Override
	public boolean updateMembers(Member m) {
		HashMap<String, Member> updateMap = getMembers();
		if (updateMap != null) {
			if (updateMap.containsKey(m.getMemberId())) {
				saveNewMember(m);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<CheckoutRecord> searchMember(String id) {
		HashMap<String, CheckoutRecord> checkoutRecord = getCheckoutRecords();
		List<CheckoutRecord> chkRecords = new ArrayList<CheckoutRecord>();
		for (CheckoutRecord record : checkoutRecord.values()) {
			if (record.getMember().getMemberId().equals(id)) {
				chkRecords.add(record);
			}
		}
		return chkRecords;
	}

	// checkout
	@Override
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		return getCheckoutRecords();
	}

	@Override
	public boolean saveCheckoutRecord(CheckoutRecord record) {
		Book tempBook = record.getBook();
		if (tempBook.getNumberOfCopy() > 0 && record.isReturnStatus() == false) {
			tempBook.setNumberOfCopy(tempBook.getNumberOfCopy() - 1);
			saveNewBook(tempBook);
			writeToStorage(DbType.CHECKOUTRECORD, record);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean returnBook(String id) {
		HashMap<String, CheckoutRecord> bookMap = getCheckoutRecords();
		for (CheckoutRecord record : bookMap.values()) {
			if (record.getBook().getId().equals(id)) {
				LocalDate todayDate = LocalDate.now();
				if (todayDate.compareTo(record.getDueDate()) <= 0) {
					// on time
					record.setReturnStatus(true);
					Book tempBook = record.getBook();
					tempBook.setNumberOfCopy(tempBook.getNumberOfCopy() + 1);
					saveNewBook(tempBook);
					saveCheckoutRecord(record);
				} else {
					// calculate fine
					//TODO
					System.out.println("Book is overdue, cannot checkout without fine!");
				}
			}
		}
		return false;
	}

}
