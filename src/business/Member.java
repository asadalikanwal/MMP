package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String memberId;
	private List<CheckoutRecord> checkoutRecords;
	
	public Member(String firstName, String lastName, Address address, String phoneNumber) {
		super(firstName, lastName, address, phoneNumber);
		checkoutRecords = new ArrayList<CheckoutRecord>();
	}

	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public List<CheckoutRecord> getCheckoutRecords() {
		return checkoutRecords;
	}
	
	public String getFullName() {
		return super.getFirstName()+" "+super.getLastName();
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		checkoutRecords.add(checkoutRecord);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(super.getFirstName()).append(":").
				append(super.getLastName()).append(":").
				append(super.getAddress()).append(":").
				append(super.getPhoneNumber()).append(":").
				append(memberId).toString();
	}	
}
