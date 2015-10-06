package fr.unice.polytech.soa1.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by remy on 21/09/15.
 */
@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class Transaction {

    @Path("/{orderId}/price/client/{userId}")
    @GET
    private Response getPrice(){
        // TODO
        return null;
    }

    @Path("/{orderId}/pay/client/{userId}")
    @POST
    private Response pay(){
        // TODO
        return null;
    }
}
