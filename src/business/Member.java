package business;

import java.io.Serializable;
import java.time.LocalDate;

public class Member extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int memberId;
	private CheckoutRecord checkoutRecord;

	public Member(String firstName, String lastName, Address address, String phoneNumber, int memberId,
			CheckoutRecord checkoutRecord) {
		super(firstName, lastName, address, phoneNumber);
		this.memberId = memberId;
		this.checkoutRecord = checkoutRecord;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}
}
