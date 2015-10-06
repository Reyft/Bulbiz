package fr.unice.polytech.soa1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Storage;
import org.json.JSONArray;

import javax.ws.rs.*;
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
    @GET
    public Response getNbBulb(){
        String answer = "{'numberOfBulb' : '";
        answer += Storage.getTotalStock();
        answer += "'}";
        return Response.ok().entity(answer).build();
    }

    @Path("/order/{userId}/{orderId}")
    @GET
    public Response getSpecificOrder(@PathParam("userId") String userId,
                                     @PathParam("orderId") String orderId){

        Order o = Storage.getOrder(Integer.parseInt(orderId), Integer.parseInt(userId));
        ObjectMapper mapper = new ObjectMapper();
        String answer;
        try {
            answer = mapper.writeValueAsString(o);
        } catch (JsonProcessingException json){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().entity(answer).build();
    }
}
