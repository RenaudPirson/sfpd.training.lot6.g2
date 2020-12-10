package be.sfpd.rest.repository;

import be.sfpd.rest.model.Article;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    public static Map<Long, Article> articles = new HashMap<>();

    public static Map<Long, Article> getArticles() {
        return articles;
    }

    public static Article getArticleById(Long id) {
        return articles.get(id);
    }
}
