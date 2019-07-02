package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String memberId;
	private List<CheckoutRecord> checkoutRecords;
	
	public Member(String firstName, String lastName, Address address, String phoneNumber, String memberId) {
		super(firstName, lastName, address, phoneNumber);
		this.memberId = memberId;
		checkoutRecords = new ArrayList<CheckoutRecord>();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMemberId() {
		return memberId;
	}
	
	public List<CheckoutRecord> getCheckoutRecords() {
		return checkoutRecords;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		checkoutRecords.add(checkoutRecord);
	}

	@Override
	public String toString() {
		return super.getFirstName() + ", " + super.getLastName();
	}	
}
