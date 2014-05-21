package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.streetartgangs.beans.UserBeanLocal;
import org.streetartgangs.entities.User;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
@Path("v1/users")
public class UserREST  {
    
    @EJB
    private UserBeanLocal ub;

    public UserREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        ub.addUser(entity);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Long id) {
        return ub.find(id);
    }
    
}
