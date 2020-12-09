package be.sfpd.blog.resource;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Path("articles")
public class ArticleResource {

    ArticleService service = new ArticleService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticle() {
        return service.getArticles();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }

    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String addArticle(Article article){
		System.out.println(article);
    	article.setCreatedDate(new Date());
    	service.addArticle(article);
		return "article added";
	}

	//@POST
	//@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	//@Produces(MediaType.TEXT_PLAIN)
	//public String addArticle(String body){
	//	Article article = new Article();
	//	article.setBody(body);
	//	article.setCreatedDate(new Date());
	//	service.addArticle(article);
	//	System.out.println(article);
	//	return "article added";
	//}



}
