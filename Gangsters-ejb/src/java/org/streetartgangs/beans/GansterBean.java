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
import org.streetartgangs.entities.Gangster;

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
    public void add(Gangster ganster) {
        try {
            em.persist(ganster);
        } catch (Exception e) {
           logger.log(Level.SEVERE, "GansterBean add error:{0}", e.getMessage());
           throw e;
        }
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Gangster find(Long id) {
        return em.find(Gangster.class, id);
    }

    @Override
    public List<Gangster> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Gangster.class));
        List<Gangster> list = em.createQuery(cq).getResultList();
        return list;
    }
}
