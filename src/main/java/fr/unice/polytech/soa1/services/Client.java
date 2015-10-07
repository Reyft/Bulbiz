package fr.unice.polytech.soa1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Storage;
import fr.unice.polytech.soa1.domain.User;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

/**
 * Created by remy on 28/09/15.
 */
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class Client {
    private ObjectMapper mapper = new ObjectMapper();

    @Path("/")
    @POST
    public Response createAccount(@QueryParam("name")    String name){
        User u = new User(name);
        Storage.addUser(u);
        try {
            String answer = mapper.writeValueAsString(u);
            return Response.ok().entity(answer).build();
        } catch (JsonProcessingException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/{id}")
    @GET
    public Response checkAccount(@PathParam("id") String userId){
        User u = Storage.getUser(Integer.parseInt(userId));
        if (u == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            String answer = mapper.writeValueAsString(u);
            return Response.ok().entity(u).build();
        } catch (JsonProcessingException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/{id}")
    @PUT
    public Response setAccount(@PathParam("id")       String clientId,
                               @QueryParam("name")    String name){
        User u = Storage.editUser(Integer.parseInt(clientId), name);
        if (u != null) {
            try {
                String anwser = mapper.writeValueAsString(u);
                return Response.ok().entity(anwser).build();
            } catch (JsonProcessingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("/{id}")
    @DELETE
    public Response delAccount(@PathParam("id")       String clientId){
        Storage.delUser(Integer.parseInt(clientId));
        return Response.ok().build();
    }

    @Path("/{clientId}/orders")
    @GET
    public Response getOrders(@PathParam("clientId")       String clientId) {

        List<Order> orders = Storage.getListOrder(Integer.parseInt(clientId));
        String result = "{";
        for(Order o : orders) {
            result+= "[idOrder : " +o.getId()+", addressOrder :";
            result += o.getAddress() + ", More Details : /orders/"+o.getId()+"/client/"+clientId+"/ ],";
        }
        result+= "}";
        return Response.ok().entity(result).build();
    }
}
