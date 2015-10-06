package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;
import fr.unice.polytech.soa1.domain.Color;
import fr.unice.polytech.soa1.domain.Form;
import fr.unice.polytech.soa1.domain.Storage;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by remy on 06/10/15.
 */
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class Stock {

    @Path("/")
    @POST
    public Response addBulbInStock(@QueryParam("form")    String form,
                                   @QueryParam("color")   String color,
                                   @QueryParam("quantity")   String qte){
        Bulb b = new Bulb(form, color);
        Storage.addInStock(b, Integer.parseInt(qte));
        return Response.ok().build();
    }

    @Path("/")
    @GET
    public Response getBulbInStock(){
        JSONArray result = new JSONArray();
        Map temp = new HashMap<String, Integer>();
        for(Form form : Form.values()){
            for(Color color : Color.values()){
                temp.put(""+color.toString()+","+form.toString(), Storage.checkQuantity(color, form));
            }
        }
        return Response.ok().entity(result.put(temp).toString(2)).build();
    }
}
