package fr.unice.polytech.soa1.services;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by remy on 21/09/15.
 */
@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class Transaction {
    private double getPrice(){
        return 0.0;
    }

    private void pay(){}
}
