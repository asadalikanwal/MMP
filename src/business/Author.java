package business;

import java.io.Serializable;

import gui.AddBookController;

public class Author extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String shortBio;
	private String credentials;
	
	public Author(String firstName, String lastName, Address address, String phoneNumber, String shortBio, String credentials) {
		super(firstName, lastName, address, phoneNumber);
		this.shortBio = shortBio;
		this.credentials = credentials;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getShortBio() {
		return shortBio;
	}
	
	public String getCredentials() {
		return credentials;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(super.getFirstName()).append(" ").
		append(super.getLastName()).toString();
//		return sb.append(super.getFirstName()).append(":").
//				append(super.getLastName()).append(":").
//				append(super.getAddress()).append(":").
//				append(shortBio).append(":").
//				append(credentials).toString();
	}	
}
