package be.sfpd.rest.resource.config;

import org.glassfish.jersey.server.ResourceConfig;

import be.sfpd.rest.resource.provider.AuthenticationFilter;

public class MyApplicationConfig extends ResourceConfig {

    public MyApplicationConfig() {
        packages("io.swagger.v3.jaxrs2.integration.resources,be.sfpd.rest");
        register(AuthenticationFilter.class);
    }
}