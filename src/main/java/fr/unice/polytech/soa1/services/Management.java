package fr.unice.polytech.soa1.services;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by remy on 01/10/15.
 */
@Path("/management")
@Produces(MediaType.APPLICATION_JSON)
public class Management {
    public int getNbUser(){
        return 0;
    }

    public int getNbBulb(){
        return 0;
    }

    public double getTotalSalePrice(){
        return 0.0;
    }

    public double getTotalPriceForUser(){
        return 0.0;
    }
}
