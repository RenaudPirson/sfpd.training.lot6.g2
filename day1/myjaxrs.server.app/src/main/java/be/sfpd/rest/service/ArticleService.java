package be.sfpd.rest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import be.sfpd.rest.repository.MockDatabase;
import be.sfpd.rest.model.Article;

public class ArticleService {

    private Map<Long, Article> articles = MockDatabase.getArticles();

    public ArticleService() {
        Article article1 = new Article(1L, LocalDate.now(), "Hello world");
        Article article2 = new Article(2L, LocalDate.now(), "Hello Jersey");
		Article article3 = new Article(3L, LocalDate.of(2000,1,1), "Hello New York");
        addArticle(article1);
		addArticle(article2);
		addArticle(article3);
    }

    public List<Article> getArticles() {
        return articles.values().stream().collect(Collectors.toList());
    }

    public Optional<Article> getArticleById(Long id) {
        return Optional.ofNullable(articles.get(id));
    }

    public Article addArticle(Article article) {
        article.setId((long) (articles.size() + 1));
        articles.put(article.getId(), article);
        return article;
    }

    public Article updateArticle(Article article) {
        if (article.getId() <= 0) {
            return null;
        }
        articles.put(article.getId(), article);
        return article;
    }

    public Article removeArticle(Long id) {
        return articles.remove(id);
    }
}
