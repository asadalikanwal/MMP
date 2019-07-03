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
import business.Alerts;
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

	static void writeToStorage(DbType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Object temp = readFromStorage(type);
			if (temp != null) {
				if (type == DbType.MEMBERS) {
					HashMap<String, Member> map = (HashMap<String, Member>) temp;
					Member member = (Member) ob;
					map.put(member.getMemberId(), member);
					ob = map;
				} else if (type == DbType.BOOKS) {
					HashMap<String, Book> map = (HashMap<String, Book>) temp;
					Book book = (Book) ob;
					map.put(book.getId(), book);
					ob = map;
				} else if (type == DbType.USERS) {
					HashMap<String, User> map = (HashMap<String, User>) temp;
					User user = (User) ob;
					map.put(user.getUsername(), user);
					ob = map;
				} else if (type == DbType.CHECKOUTRECORD) {
					HashMap<String, CheckoutRecord> map = (HashMap<String, CheckoutRecord>) temp;
					CheckoutRecord record = (CheckoutRecord) ob;
					map.put(record.getRecordId(), record);
					ob = map;
				}
			} else {
				if (type == DbType.MEMBERS) {
					HashMap<String, Member> map = new HashMap<String, Member>();
					Member member = (Member) ob;
					map.put(member.getMemberId(), member);
					ob = map;
				} else if (type == DbType.BOOKS) {
					HashMap<String, Book> map = new HashMap<String, Book>();
					Book book = (Book) ob;
					map.put(book.getId(), book);
					ob = map;
				} else if (type == DbType.USERS) {
					HashMap<String, User> map = new HashMap<String, User>();
					User user = (User) ob;
					map.put(user.getUsername(), user);
					ob = map;
				} else if (type == DbType.CHECKOUTRECORD) {
					HashMap<String, CheckoutRecord> map = new HashMap<String, CheckoutRecord>();
					CheckoutRecord record = (CheckoutRecord) ob;
					map.put(record.getRecordId(), record);
					ob = map;
				}
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
		return (HashMap<String, Book>) readFromStorage(DbType.BOOKS);
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
		HashMap<String, Book> books = (HashMap<String, Book>) readFromStorage(DbType.BOOKS);
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
		return (HashMap<String, User>) readFromStorage(DbType.USERS);
	}

	@Override
	public void saveUserMap(User user) {
		writeToStorage(DbType.USERS, user);
	}
	
	@Override
	public String userLogin(String username, String password) {
		HashMap<String, User> users = readUserMap();
		User user = users.get(username);
		if (user != null && user.getPassword().equals(password)) {
			return user.getAccessLevel().toString();
		}
		return null;
	}

	// member
	@Override
	public HashMap<String, Member> readMemberMap() {
		return (HashMap<String, Member>) readFromStorage(DbType.MEMBERS);
	}

	@Override
	public void saveNewMember(Member member) {
		writeToStorage(DbType.MEMBERS, member);

	}

	@Override
	public boolean updateMembers(Member m) {
		HashMap<String, Member> updateMap = (HashMap<String, Member>) readFromStorage(DbType.MEMBERS);
		if (updateMap != null) {
			if (updateMap.containsKey(m.getMemberId())) {
				updateMap.put(m.getMemberId(), m);
				saveNewMember(m);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<CheckoutRecord> searchMember(String id) {
		HashMap<String, CheckoutRecord> checkoutRecord = (HashMap<String, CheckoutRecord>) readFromStorage(DbType.CHECKOUTRECORD);
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
		return (HashMap<String, CheckoutRecord>) readFromStorage(DbType.CHECKOUTRECORD);
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
		HashMap<String, CheckoutRecord> bookMap = (HashMap<String, CheckoutRecord>) readFromStorage(DbType.CHECKOUTRECORD);
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
