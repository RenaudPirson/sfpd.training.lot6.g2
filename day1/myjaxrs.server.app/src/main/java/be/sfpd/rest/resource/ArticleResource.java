package be.sfpd.blog.resource;

import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleService;

@Path("articles")
public class ArticleResource {

    static ArticleService service = new ArticleService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticle() {
        return service.getArticles();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId).orElse(null);
    }

    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String addArticle(Article article){
    	article.setCreatedDate(LocalDate.now());
    	service.addArticle(article);
		return "article added";
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String editArticle(@PathParam("id")long id, Article newArticle){
		Article article = service.getArticleById(id)
				.orElseThrow(() ->new IllegalArgumentException("Article with id " + id + " does not exist"));
		article.setBody(newArticle.getBody());
		service.updateArticle(article);
		return "article modified";
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteArticle(@PathParam("id")long id){
    	service.removeArticle(id);
    	return "article deleted";
	}


}
