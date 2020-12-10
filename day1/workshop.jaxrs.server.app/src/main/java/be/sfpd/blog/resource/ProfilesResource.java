package be.sfpd.rest.resource;

import be.sfpd.rest.model.Profile;
import be.sfpd.rest.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("profiles")
public class ProfilesResource {

    final ProfileService profileService = new ProfileService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getAllProfiles() {
        return profileService.getProfiles();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Profile createProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfileByName(@PathParam("name") String name) {
        return profileService.getProfileByName(name);
    }
}
