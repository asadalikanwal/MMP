package business;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	private String street;
	private String city;
	private String zip;
	private String state;

	public Address(String street, String city, String zip, String state) {
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getZip() {
		return zip;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(street).append(":").append(city).append(":").append(zip).append(":").append(state).toString();
	}
}
