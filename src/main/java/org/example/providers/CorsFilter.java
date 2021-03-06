package org.example.providers;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//        System.out.println("/n" + "-- req info --");
//        log(requestContext.getUriInfo(), requestContext.getHeaders());
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//        System.out.println("-- res info --");
//        log(requestContext.getUriInfo(), responseContext.getHeaders());
    }

//    private void log(UriInfo uriInfo, MultivaluedMap<String, ?> headers) {
//        System.out.println("Path: " + uriInfo.getPath());
//        headers.entrySet().forEach(h -> System.out.println(h.getKey() + ": " + h.getValue()));
//    }
}
