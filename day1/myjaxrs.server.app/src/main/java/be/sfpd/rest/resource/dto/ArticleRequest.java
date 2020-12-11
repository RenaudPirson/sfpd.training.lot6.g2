package be.sfpd.rest.resource.dto;

import java.time.LocalDateTime;

import be.sfpd.rest.model.Article;

public class ArticleRequest {
	private String body;
	private LocalDateTime timestamp = LocalDateTime.now();

	public ArticleRequest() {
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
