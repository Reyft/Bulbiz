package fr.unice.polytech.soa1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.soa1.domain.Order;
import fr.unice.polytech.soa1.domain.Payement;
import fr.unice.polytech.soa1.domain.Storage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by remy on 21/09/15.
 */
@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class Transaction {

    @Path("/price/client/{userId}")
    @GET
    private Response showOrder(@PathParam("userId") String userId){
        Order o = Storage.getOrderInProgress(Integer.parseInt(userId));
        if (o != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String answer = mapper.writeValueAsString(o);
                return Response.ok().entity(answer).build();
            } catch (JsonProcessingException e){
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        return Response.status(Status.NO_CONTENT).build();
    }

    @Path("/pay/client/{userId}/order/{orderId}")
    @POST
    private Response pay(@PathParam("userId") String userId,
                         @PathParam("orderId") String orderId,
                         @QueryParam("numero") String numero,
                         @QueryParam("date") String date,
                         @QueryParam("sc") String sc){
        if (Payement.paye(userId, orderId, numero, date, sc)){
            Response.ok().entity("\"Payement done\"").build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
}
