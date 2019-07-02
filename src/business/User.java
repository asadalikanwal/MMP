package business;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private AccessLevel accessLevel;
	public User(String username, String password, AccessLevel accessLevel) {
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	
	public String toString() {
		return username + ", " + accessLevel;
	}
}
