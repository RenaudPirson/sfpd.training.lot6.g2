package be.sfpd.rest.resource.dto;

import java.time.LocalDateTime;
import java.util.List;

import be.sfpd.rest.model.Article;

public class ArticleListResponse {
	private  List<Article> articles;

	private  LocalDateTime timestamp = LocalDateTime.now();

	public ArticleListResponse() {
	}

	public ArticleListResponse(List<Article> articles) {
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
