package be.sfpd.rest.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import be.sfpd.rest.model.Comment;
import be.sfpd.rest.repository.MockDatabase;
import be.sfpd.rest.model.Article;

public class ArticleService {

    private Map<Long, Article> articles = MockDatabase.getArticles();

    private static final ArticleService instance = new ArticleService();

    public ArticleService() {
        //Article article1 = new Article(1L, LocalDate.now(), "Hello world");
        //Article article2 = new Article(2L, LocalDate.now(), "Hello Jersey");
		//Article article3 = new Article(3L, LocalDate.of(2000,1,1), "Hello New York");
        //addArticle(article1);
		//addArticle(article2);
		//addArticle(article3);
		//
		//commentArticle(article1, new Comment("I like this article : 8/10"));
		//commentArticle(article1, new Comment("I hate this article : 2/10"));
    }

    public List<Article> getArticles() {
        return articles.values().stream().collect(Collectors.toList());
    }

    public Optional<Article> getArticleById(Long id) {
        return Optional.ofNullable(articles.get(id));
    }

    public Article addArticle(Article article) {
        article.setId(MockDatabase.getNewArticleId());
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

    public Article removeArticle(Long id) throws IllegalIdException {
    	if(getArticleById(id) == null){
    		throw new IllegalIdException(id);
		}
        return articles.remove(id);
    }

    public Article commentArticle(Article article, Comment comment){
    	comment.setId(MockDatabase.getNewCommentId());
    	comment.setCreationDate(LocalDateTime.now());
    	article.getComments().add(comment);
    	return updateArticle(article);
	}

	public static ArticleService getInstance(){
    	return instance;
	}

	public Optional<Comment> getCommentOfArticle(Long articleId, Long commentId) {
    	return getArticleById(articleId)
				.flatMap(a -> a.getComments().stream()
					.filter(c -> c.getId() == commentId)
					 .findFirst()
				);



	}

	public Comment updateCommentOfArticle(Long articleId, Long commentId, Comment comment) throws IllegalIdException {
		Comment toUpdate = getCommentOfArticle(articleId, commentId)
				.orElseThrow(() -> new IllegalIdException(articleId, commentId));
		toUpdate.setText(comment.getText());
		return toUpdate;

	}
}
