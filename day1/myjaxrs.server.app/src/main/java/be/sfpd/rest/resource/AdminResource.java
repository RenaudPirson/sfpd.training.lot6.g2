package be.sfpd.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import be.sfpd.rest.repository.MockDatabase;
import be.sfpd.rest.service.ArticleService;

@Path("admin")
public class AdminResource {
	@GET
	@Path("/resetDB")
	public Response resetDb(){
		MockDatabase.resetDB();
		return Response.ok().build();
	}
}
