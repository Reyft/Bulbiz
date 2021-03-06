package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;
import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Storage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class Orders {

    @Path("/client/{id}/")
    @POST
    public Response addToCart(@QueryParam("form")    String form,
                                   @QueryParam("color")   String color,
                                   @QueryParam("quantity")   String qte,
                                   @PathParam("id")       String clientId) {

        int orderId = Storage.getIdOrderInProgress(Integer.parseInt(clientId));
        if(orderId == -1) {
            Order o = new Order(new Bulb(color,form),Integer.parseInt(qte));
            o.processThePrice();
            Storage.addAnOrder(Integer.parseInt(clientId), o);
        } else {
            Storage.addBulbToOrder(Integer.parseInt(clientId), new Bulb(color,form), Integer.parseInt(qte));
        }

        return Response.ok().entity("{\"id\" : " + Storage.getIdOrderInProgress(Integer.parseInt(clientId)) + "}").build();
    }

    @Path("/client/{id}/")
    @GET
    public Response getCart(@PathParam("id")       String clientId) {


        int orderId = Storage.getIdOrderInProgress(Integer.parseInt(clientId));
        if(orderId == -2) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Wrong Client ID\"")
                    .build();
        } else if (orderId == -1){
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"No Order in Progress\"")
                    .build();
        }
        Order lastOrder = Storage.getOrderInProgress(Integer.parseInt(clientId));
        Map<Bulb, Integer> listOrder = lastOrder.getList();
        String result = "{";
        for (Bulb mapKey : listOrder.keySet()) {
           result+="[\"color\" : \""+mapKey.getColor()+"\",";
           result+="\"form\" : \""+mapKey.getForm()+"\",";
           result+="\"quantity\" : "+listOrder.get(mapKey)+"],";
        }

        result += "}";
        return Response.ok().entity(result).build();
    }

    @Path("/client/{id}/")
    @DELETE
    public Response deleteCart(@PathParam("id")       String clientId) {

        int orderId = Storage.getIdOrderInProgress(Integer.parseInt(clientId));
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

    @Path("/{orderId}/client/{clientId}/")
    @GET
    public Response getSpecificOrder(@PathParam("orderId")       String orderId,
                             @PathParam("clientId")       String clientId) {

        Order order = Storage.getOrder(Integer.parseInt(orderId), Integer.parseInt(clientId));
        return Response.ok().entity(order.getList().toString()).build();
    }
}