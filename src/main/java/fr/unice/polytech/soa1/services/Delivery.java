package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Storage;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by remy on 21/09/15.
 */

@Produces(MediaType.APPLICATION_JSON)
public class Delivery {
    @Path("/delivery/{clientId}")
    @POST
    public Response addADestination(@PathParam("id")        String clientId,
                                    @QueryParam("address")  String address){
        int status = Storage.addAddress(Integer.parseInt(clientId), address);
        if(status == -2) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Wrong Client ID\"")
                    .build();
        } else if (status == -1){
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"No Order in Progress\"")
                    .build();
        }
        return Response.ok().build();
    }

    @Path("/delivery/{orderId}/client/{clientId}")
    @GET
    public Response followDelivery(@PathParam("orderId")        String orderId,
                                   @PathParam("clientId")       String clientId){
        String status = Storage.getStatus(Integer.parseInt(clientId), Integer.parseInt(orderId));
        return Response.ok().entity("{status :" + status + "}").build();
    }
}
