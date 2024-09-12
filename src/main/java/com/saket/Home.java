package com.saket;


import beans.Employee;
import beans.User;
import dao.Employee_Dao;
import dao.User_Dao;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;



@Path("/home")
public class Home {


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @HeaderParam("AUTHORIZATION") String authorizationHeader,
            Employee employee) {

        String email = null;
        String pwd = null;
        System.out.println(authorizationHeader);

        // Check if the Authorization header is provided
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            // Extract the base64-encoded part from the header
            String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
            System.out.println(base64Credentials);
            // Decode the credentials
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            // credentials = username:password
            String[] values = credentials.split(":", 2);
            email = values[0];
            pwd = values[1];
        }


        System.out.println(employee + " " + email + " " + pwd);

        // Check if the user exists with the provided email and password
        if (User_Dao.checkUserExist(email, pwd)) {
            Employee_Dao employee_dao = new Employee_Dao();

            // Save employee details
            Response response = employee_dao.saveEmpDetail(employee);

            // Return response after saving
            return response;
        } else {
            // Return unauthorized response if user does not exist
            Response.ResponseBuilder responseBuilder = Response.status(Response.Status.UNAUTHORIZED);
            responseBuilder.entity("Operation Failed: Unauthorized User");
            return responseBuilder.build();
        }
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        System.out.println(user);
        User_Dao user_dao = new User_Dao();
        Response res = user_dao.saveUserDetail(user);
        return res;
    }

}
