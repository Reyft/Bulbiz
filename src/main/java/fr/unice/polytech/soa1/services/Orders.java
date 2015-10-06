package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;
import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Storage;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;


@Produces(MediaType.APPLICATION_JSON)
public class Orders {

    @Path("/order/{id}/")
    @POST
    public Response addToOrder(@QueryParam("form")    String form,
                                   @QueryParam("color")   String color,
                                   @QueryParam("quantity")   String qte,
                                   @PathParam("id")       String clientId) {

        int orderId = Storage.getStateOrderInProgress(Integer.parseInt(clientId));
        if(orderId == -2) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Wrong Client ID\"")
                    .build();
        } else if (orderId == -1){

            Order o = new Order(new Bulb(color,form),Integer.parseInt(qte));
            Storage.addAnOrder(Integer.parseInt(clientId), o);
        } else {
            Storage.addBulbToOrder(Integer.parseInt(clientId), new Bulb(color,form), Integer.parseInt(qte));
        }
        return Response.ok().build();
    }

    @Path("/order/{id}/")
    @GET
    public Response getOrderInProgress(@PathParam("id")       String clientId) {

        Order lastOrder = Storage.getOrderInProgress(Integer.parseInt(clientId));
        int orderId = Storage.getStateOrderInProgress(Integer.parseInt(clientId));
        if(orderId == -2) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Wrong Client ID\"")
                    .build();
        } else if (orderId == -1){
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"No Order in Progress\"")
                    .build();
        }
        return Response.ok().entity(lastOrder.getList().toString()).build();
    }

    @Path("/order/{id}/")
    @DELETE
    public Response deleteOrder(@PathParam("id")       String clientId) {

        int orderId = Storage.getStateOrderInProgress(Integer.parseInt(clientId));
        if(orderId == -2) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Wrong Client ID\"")
                    .build();
        } else if (orderId == -1){
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"No Order in Progress\"")
                    .build();
        }
        Storage.deleteInProgress(Integer.parseInt(clientId));
        return Response.ok().build();
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBulb(String name, Color color){
        if(Storage.read(name) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Existing name " + name + "\"")
                    .build();
        }
        Storage.create(name, color);
        return Response.ok().build();
    }*/


    /*@GET
    public Response getMyBubl(){
        Collection<Bulb> coll = Storage.findAll();
        JSONArray result = new JSONArray();
        for (Bulb b : coll){
            result.put(b);
        }
        return Response.ok().entity(result.toString()).build();
    }*/

    /*@Path("/search/{name}")
    @GET
    public Response searchBulb(@PathParam("name") String name){
        if (Storage.read(name) == null){
            Response.status(Response.Status.NOT_FOUND).build();
        }
        String value = Storage.read(name).run();
        return Response.ok().entity("\""+value+"\"").build();
    }*/
}