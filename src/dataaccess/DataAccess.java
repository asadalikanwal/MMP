package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckoutRecord;
import business.Member;
import business.User;

public interface DataAccess {
	public HashMap<String,Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, Member> readMemberMap();
	public HashMap<String, Member> readCheckoutRecordMap();
	public void saveNewMember(Member member);
	public void saveNewBook(Book book);
	public void saveUserMap(User user);
	public void updateMembers(HashMap<String, Member> members);
	public void saveCheckoutRecord(CheckoutRecord record);
}
