package be.sfpd.rest.resource;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.sfpd.rest.model.Article;
import be.sfpd.rest.service.ArticleService;

@Path("articles")
public class ArticleResource {

    static ArticleService service = ArticleService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticle(@BeanParam ArticleBeanParam articleBeanParam) {

		List<Article> articles = service.getArticles();
		Stream<Article> articleStream = articles.stream().sorted(Comparator.comparing(Article::getId))
				.skip( articleBeanParam.getOffset() );
		if(articleBeanParam.getLimit() != null){
			articleStream = articleStream.limit(articleBeanParam.getLimit());
		}

		if(articleBeanParam.getYear() != null){
			articleStream = articleStream.filter(article -> article.getCreatedDate().getYear() == articleBeanParam.getYear().intValue());
		}

		return articleStream.collect(Collectors.toList());
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
