package be.sfpd.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.sfpd.rest.model.Article;
import be.sfpd.rest.model.Comment;
import be.sfpd.rest.service.ArticleService;

@Path("articles/{articleId}/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	static ArticleService service = ArticleService.getInstance();

	@GET
	public List<Comment> getAllComments(@PathParam("articleId") Long articleId){
		return service.getArticleById(articleId)
				.map(Article::getComments)
				.orElse(null);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment addComment(@PathParam("articleId") Long articleId, Comment comment){
		Article article = service.getArticleById(articleId)
				.orElseThrow(() -> new IllegalArgumentException("Article " + articleId + " does not exist"));
		service.commentArticle(article, comment);
		return comment;
	}

	//@GET
	//@Path("/{commentId}")
}
