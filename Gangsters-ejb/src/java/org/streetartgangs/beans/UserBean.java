package org.streetartgangs.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.streetartgangs.entities.StreetArtUser;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "Gangsters-ejbPU")
    protected EntityManager em;
    
    @Override
    public void addUser(StreetArtUser user) {
        if (user == null) {
            throw new NullPointerException("user");
        }
        if (find(user.getDbId()) == null) {
            em.persist(user);
        }
    }
    
    @Override
    public StreetArtUser find(Long id) {
        if (id == null) {
            return null;
        }
        return em.find(StreetArtUser.class, id);
    }
}
