package business;

import java.util.List;
import java.util.Scanner;

import dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
		DataAccessFacade daf = new DataAccessFacade();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter username: ");
		String user = in.next();
		System.out.println("Enter password: ");
		String pass = in.next();
		String accessLevel = daf.userLogin(user, pass).toString();
		if (accessLevel.equals("LIBRARIAN")) {
			System.out.println("Enter member ID: ");
			String memberID = in.next();
			if (daf.srcMember(memberID) != null) {
				System.out.println("Member found!\nDo you want to print the checkout-record?(y/n):");
				String answer = in.next();
				if (answer.equalsIgnoreCase("y")) {
					List<CheckoutRecord> records = daf.searchMember(memberID);
					for (CheckoutRecord checkoutRecord : records) {
						System.out.println("Member: " + checkoutRecord.getMember().getFullName() + ", Book-checkedout: "
								+ checkoutRecord.getBookTitle() + ", Due-date: " + checkoutRecord.getDueDate());
					}
				} else {
					System.out.println("Bye!");
				}

			} else {
				System.out.println("Member not found!");
			}
		} else {
			System.out.println("You are not a LIBRARIAN!");
		}
	}
}
