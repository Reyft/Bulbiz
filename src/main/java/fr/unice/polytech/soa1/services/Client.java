package fr.unice.polytech.soa1.services;

import fr.unice.polytech.soa1.domain.Bulb;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by remy on 28/09/15.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class Client {
    public void createAccount(){}

    public void setAccount(){}

    public void connexion(){}

    public List<Bulb> viewList(){
        return new ArrayList<Bulb>();
    }
}
