package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;
import fr.unice.polytech.soa1.domain.Storage;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.Collection;

/**
 * Created by remy on 21/09/15.
 */
@Path("/custom")
@Produces(MediaType.APPLICATION_JSON)
public class Customization {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBulb(String name, Color color){
        if(Storage.read(name) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("\"Existing name " + name + "\"")
                    .build();
        }
        Storage.create(name, color);
        return Response.ok().build();
    }

    @GET
    public Response getMyBubl(){
        Collection<Bulb> coll = Storage.findAll();
        JSONArray result = new JSONArray();
        for (Bulb b : coll){
            result.put(b);
        }
        return Response.ok().entity(result.toString()).build();
    }

    @Path("/search/{name}")
    @GET
    public Response searchBulb(@PathParam("name") String name){
        if (Storage.read(name) == null){
            Response.status(Response.Status.NOT_FOUND).build();
        }
        String value = Storage.read(name).run();
        return Response.ok().entity("\""+value+"\"").build();
    }
}