package be.sfpd.rest.repository;

import be.sfpd.rest.model.Article;
import be.sfpd.rest.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    public static Map<Long, Article> articles = new HashMap<>();
    public static Map<Long, Profile> profiles = new HashMap<>();

    public static Map<Long, Article> getArticles() {
        return articles;
    }

    public static Article getArticleById(Long id) {
        return articles.get(id);
    }

    public static Map<Long, Profile> getProfiles(){
    	return profiles;
	}

	public static void resetDB(){
    	articles.clear();
    	profiles.clear();
	}
}
