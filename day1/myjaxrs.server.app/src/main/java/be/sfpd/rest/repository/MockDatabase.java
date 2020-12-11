package be.sfpd.rest.repository;

import be.sfpd.rest.model.Article;
import be.sfpd.rest.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    public static Map<Long, Article> articles = new HashMap<>();
    public static Map<Long, Profile> profiles = new HashMap<>();
	private static Long COMMENT_SEQUENCE = 1l;
	private static Long ARTICLE_SEQUENCE = 1l;

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
		COMMENT_SEQUENCE = 1l;
		ARTICLE_SEQUENCE = 1l;
	}

	public static Long getNewCommentId(){
    	return COMMENT_SEQUENCE ++;
	}

	public static Long getNewArticleId(){
    	return ARTICLE_SEQUENCE ++;
	}
}
