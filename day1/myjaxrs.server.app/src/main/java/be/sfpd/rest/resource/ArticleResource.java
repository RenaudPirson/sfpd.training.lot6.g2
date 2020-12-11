package be.sfpd.rest.resource;

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
import javax.ws.rs.core.Response;

import be.sfpd.rest.model.Article;
import be.sfpd.rest.resource.dto.ArticleListResponse;
import be.sfpd.rest.resource.dto.ArticleRequest;
import be.sfpd.rest.resource.dto.ArticleResponse;
import be.sfpd.rest.service.ArticleService;
import be.sfpd.rest.service.IllegalIdException;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    static ArticleService service = ArticleService.getInstance();

    @GET
    public Response getAllArticle(@BeanParam ArticleBeanParam articleBeanParam) {


    	List<Article> articles = service.getArticles();
		Stream<Article> articleStream = articles.stream().sorted(Comparator.comparing(Article::getId))
				.skip( articleBeanParam.getOffset() );
		if(articleBeanParam.getLimit() != null){
			articleStream = articleStream.limit(articleBeanParam.getLimit());
		}

		if(articleBeanParam.getYear() != null){
			articleStream = articleStream.filter(article -> article.getCreatedDate().getYear() == articleBeanParam.getYear().intValue());
		}

		List<Article> filteredArticles = articleStream.collect(Collectors.toList());

		return Response
				.ok(new ArticleListResponse(filteredArticles))
				.build();
	}

    @GET
    @Path("/{id}")
    public Response getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId)
				.map(ArticleResponse::new)
				.map(Response::ok)
				.orElse(Response.noContent())
				.build();
    }

    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addArticle(ArticleRequest articleRequest){
		Article article = service.addArticle(articleRequest);
		return Response.ok(new ArticleResponse(article)).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response editArticle(@PathParam("id")long id, ArticleRequest articleRequest){
		Article article = service.getArticleById(id).orElseThrow(() -> new IllegalIdException(id));
		article.setBody(articleRequest.getBody());
		article = service.updateArticle(article);
		return Response
				.ok(new ArticleResponse(article))
				.build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteArticle(@PathParam("id")long id){

		Article article = service.removeArticle(id);
		return Response.ok(new ArticleResponse(article)).build();
	}


}
