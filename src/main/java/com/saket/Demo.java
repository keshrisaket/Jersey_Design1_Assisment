package com.saket;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/demo")
public class Demo {

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_HTML)
    public String getTest() {
        return "success";
    }
}
