package be.sfpd.blog.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.sfpd.blog.model.Profile;
import be.sfpd.blog.service.ProfileService;

@Path("profiles")
public class ProfileResource {
	ProfileService profileService = new ProfileService();

	@GET
	@Produces(APPLICATION_JSON)
	public Collection<Profile> getProfiles(){
		return profileService.getProfiles();
	}

	@GET
	@Produces(APPLICATION_JSON)
	@Path("/{userName}")
	public Profile getProfile(@PathParam("userName")String name){
		return profileService.getProfileByName(name)
				.orElse(null);
	}
}
