package be.sfpd.rest.repository;

import be.sfpd.rest.model.Article;
import be.sfpd.rest.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    public static Map<String, Article> articles = new HashMap<>();
    public static Map<String, Profile> profiles = new HashMap<>();

    public static Map<String, Article> getArticles() {
        return articles;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

    public static void resetDB() {
        articles.clear();
        profiles.clear();
    }

}
