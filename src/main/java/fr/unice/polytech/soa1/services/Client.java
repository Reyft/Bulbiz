package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Storage;
import fr.unice.polytech.soa1.domain.User;
import org.json.JSONArray;

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

    @POST
    public Response createAccount(@QueryParam("name")    String name){
        Storage.addUser(new User(name));
        return Response.ok().build();
    }

    @Path("/{id}")
    @GET
    public Response checkAccount(@PathParam("id") String userId){
        User u = Storage.getUser(Integer.parseInt(userId));
        if (u == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        JSONArray json = new JSONArray();
        json.put(u.getId());
        json.put(u.getName());
        return Response.ok().entity(json.toString(2)).build();
    }

    @Path("/{id}")
    @PUT
    public Response setAccount(@PathParam("id")       String clientId,
                               @QueryParam("name")    String name){
        Storage.editUser(Integer.parseInt(clientId), name);
        return Response.ok().build();
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
        String result = "";
        for(Order o : orders) {
            result+= "[idOrder : " +o.getId()+", addressOrder :";
            result += o.getAddress() + ", More Details : /orders/"+o.getId()+"/client/"+clientId+"/ ],";
        }
        return Response.ok().entity(result).build();
    }
}
