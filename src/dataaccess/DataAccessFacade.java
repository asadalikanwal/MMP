package dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

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
					map.put(record.getMemberId(), record);
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
					map.put(record.getMemberId(), record);
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
			e.printStackTrace();
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

	@Override
	public HashMap<String, Book> readBooksMap() {
		return (HashMap<String, Book>) readFromStorage(DbType.BOOKS);
	}

	@Override
	public HashMap<String, User> readUserMap() {
		// TODO Auto-generated method stub

		return (HashMap<String, User>) readFromStorage(DbType.USERS);
	}

	@Override
	public HashMap<String, Member> readMemberMap() {
		return (HashMap<String, Member>) readFromStorage(DbType.MEMBERS);
	}

	@Override
	public void saveNewMember(Member member) {
		writeToStorage(DbType.MEMBERS, member);

	}

	@Override
	public void updateMembers(HashMap<String, Member> members) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Member> readCheckoutRecordMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewBook(Book book) {
		writeToStorage(DbType.BOOKS, book);
	}

	@Override
	public void saveUserMap(User user) {
		writeToStorage(DbType.USERS, user);
	}

	@Override
	public void saveCheckoutRecord(CheckoutRecord record) {
		writeToStorage(DbType.CHECKOUTRECORD, record);
	}

}
