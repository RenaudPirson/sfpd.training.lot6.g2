package be.sfpd.rest.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Comment {
	private long id;
	private String text;
	private LocalDateTime creationDate;

	public Comment(){
		//ForJSonB
	}

	public Comment(String text) {
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
