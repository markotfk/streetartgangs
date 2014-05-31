package org.streetartgangs.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.streetartgangs.entities.UserLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
public class UserLocationBean implements UserLocationBeanLocal {

    @PersistenceContext(unitName = "Gangsters-ejbPU")
    protected EntityManager em;
    
    protected static final Logger logger = Logger.getLogger(UserLocationBean.class.getName());
    
    @Override
    public void add(UserLocation location) {
        try {
            em.persist(location);
        } catch (Exception e) {
           logger.log(Level.SEVERE, "UserLocationBean add error:{0}", e.getMessage());
           throw e;
        }
        
    }
    @Override
    public List<UserLocation> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserLocation.class));
        List<UserLocation> all = em.createQuery(cq).getResultList();
        return all;
    }

    @Override
    public void addMany(List<UserLocation> locations) {
        if (locations == null) {
            return;
        }
        for (UserLocation l : locations) {
            try {
                em.persist(l);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "UserLocationBean addMany error: {0}", e.getMessage());
            }
        }
    }
}
