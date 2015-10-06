package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Storage;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by remy on 01/10/15.
 */
@Path("/display")
@Produces(MediaType.APPLICATION_JSON)
public class Display {

    @Path("/users")
    @GET
    public Response getNbUser(){
        String answer = "{'numberOfUser' : '";
        answer += Storage.getNbOfUsers();
        answer += "'}";
        return Response.ok().entity(answer).build();
    }

    @Path("/bulbs")
    public Response getNbBulb(){
        String answer = "{'numberOfBulb' : '";
        answer += Storage.getTotalStock();
        answer += "'}";
        return Response.ok().entity(answer).build();
    }
}
