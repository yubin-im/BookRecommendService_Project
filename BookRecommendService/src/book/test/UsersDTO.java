package book.test;

import java.util.List;

public class UsersDTO {
	private String userID;
	private int password;
	private String name;
	private String genre1;
	private String genre2;
	private String email;
	
	public UsersDTO(String userID, int password, String name, String genre1, String genre2, String email) {
		super();
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.genre1 = genre1;
		this.genre2 = genre2;
		this.email = email;
	}
	
	public UsersDTO(String userID, int password) {
		super();
		this.userID = userID;
		this.password = password;
	}
	public UsersDTO() {
		
	}

	public String getUserID() {
		return userID;
	}

	public int getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getGenre1() {
		return genre1;
	}
	public String getGenre2() {
		return genre2;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}
	public void setGenre2(String genre2) {
		this.genre2 = genre2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UsersDTO [userID=" + userID + ", password=" + password + ", name=" + name + ", genre1=" + genre1
				+ ", genre2=" + genre2 + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersDTO other = (UsersDTO) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	
	
	
}
