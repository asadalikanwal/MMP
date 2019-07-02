package business;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String accessLevel;
	public User(String username, String password, String accessLevel) {
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
	public String getAccessLevel() {
		return accessLevel;
	}
}
