/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.streetartgangs.beans.UserLocationBeanLocal;
import org.streetartgangs.entities.UserLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
@Path("v1/userlocations")
public class UserLocationREST {

    @EJB
    private UserLocationBeanLocal ub;
    
    public UserLocationREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void add(UserLocation entity) {
        ub.add(entity);
    }
    
    @GET
    public List<UserLocation> getAll() {
        return ub.findAll();
    }
}
