package com.github.dearrudam;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/api/")
public class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject get(@Context UriInfo uri) {
        return Json.createObjectBuilder()
                .add("data", "hello")
                .add("uri", UriBuilder.fromResource(HelloResource.class).build().toString())
                .build();
    }

}