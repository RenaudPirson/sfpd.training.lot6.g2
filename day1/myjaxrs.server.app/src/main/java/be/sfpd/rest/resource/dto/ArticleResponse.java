package be.sfpd.rest.resource.dto;

import java.time.LocalDateTime;

import be.sfpd.rest.model.Article;

public class ArticleResponse {
	private Article article;
	private LocalDateTime timestamp = LocalDateTime.now();

	public ArticleResponse() {
	}

	public ArticleResponse(Article article) {
		this.article = article;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
