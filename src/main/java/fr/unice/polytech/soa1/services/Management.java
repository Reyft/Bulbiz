package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;
import fr.unice.polytech.soa1.domain.Storage;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by remy on 06/10/15.
 */
@Path("/management")
@Produces(MediaType.APPLICATION_JSON)
public class Management {

    @Path("add")
    @POST
    public Response addBulbInStock(@QueryParam("form")    String form,
                                   @QueryParam("color")   String color,
                                   @QueryParam("quantity")   String qte){
        Bulb b = new Bulb(form, color);
        Storage.addInStock(b, Integer.parseInt(qte));
        return Response.ok().build();
    }
}
