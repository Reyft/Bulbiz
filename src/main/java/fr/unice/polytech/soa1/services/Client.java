package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;
import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Storage;
import fr.unice.polytech.soa1.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by remy on 28/09/15.
 */
@Produces(MediaType.APPLICATION_JSON)
public class Client {

    @Path("/client/")
    @POST
    public Response createAccount(@QueryParam("name")    String name){
        Storage.addUser(new User(name));
        return Response.ok().build();
    }

    @Path("/client/{id}")
    @PUT
    public Response setAccount(@PathParam("id")       String clientId,
                               @QueryParam("name")    String name){
        Storage.editUser(Integer.parseInt(clientId), name);
        return Response.ok().build();
    }

    @Path("/client/{id}")
    @DELETE
    public Response delAccount(@PathParam("id")       String clientId){
        Storage.delUser(Integer.parseInt(clientId));
        return Response.ok().build();
    }

    @Path("/client/{clientId}/orders")
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
