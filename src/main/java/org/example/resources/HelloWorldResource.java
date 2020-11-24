package org.example.resources;

import org.example.domain.Contact;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/helloworld")
public class HelloWorldResource {

    @GET
    public Response helloworld(@QueryParam("name") String name) {
        String n = name != null ? name : "world";
        return Response.ok().entity("Hello " + n + "!").build();
    }

    @GET @Path("{name}")
    public Response helloworldName(@PathParam("name") String name) {
        return Response.ok().entity("Hello " + name + "!").build();
    }



    @POST
    public Response post(String body, @Context UriInfo uriInfo){
        String b = body != null ? body : "404: body not found";
        UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("/test_path");
        return Response.created(ub.build()).entity("Post with message: " + b).build();
    }

}
