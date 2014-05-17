/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.streetartgangs.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.streetartgangs.entities.GangsterLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
public class GansterBean implements GansterBeanLocal {

    @PersistenceContext(unitName = "Gangsters-ejbPU")
    protected EntityManager em;
    
    protected static final Logger logger = Logger.getLogger(GansterBean.class.getName());
    
    @Override
    public void add(GangsterLocation location) {
        try {
            em.persist(location);
        } catch (Exception e) {
           logger.log(Level.SEVERE, "GansterBean add error:{0}", e.getMessage());
           throw e;
        }
        
    }
    @Override
    public List<GangsterLocation> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(GangsterLocation.class));
        List<GangsterLocation> all = em.createQuery(cq).getResultList();
        return all;
    }
}
