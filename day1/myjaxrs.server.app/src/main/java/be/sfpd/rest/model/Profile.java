package be.sfpd.rest.model;

import java.time.LocalDate;

public class Profile {

	private String userName;
	private String email;
	private long id;
	private LocalDate creationDate;

	public Profile(String userName, String email, long id, LocalDate creationDate) {
		this.userName = userName;
		this.email = email;
		this.id = id;
		this.creationDate = creationDate;
	}

	public Profile(String userName, String email) {
		this.userName = userName;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
}
