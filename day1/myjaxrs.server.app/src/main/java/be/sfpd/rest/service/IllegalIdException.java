package be.sfpd.rest.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalIdException extends RuntimeException implements ExceptionMapper<IllegalIdException> {

	public IllegalIdException(){
		super();
	}

	public IllegalIdException(Long articleId) {
		super("article with id " + articleId + " does not exist");
	}

	public IllegalIdException(Long articleId, Long commentId) {
		super("comment with id " + commentId + " for article with id " + articleId + " does not exist");
	}

	@Override
	public Response toResponse(IllegalIdException e) {

		return Response.status(Response.Status.NOT_FOUND)
				.type(MediaType.TEXT_PLAIN)
				.entity(e.getMessage())
				.build();
	}

}
