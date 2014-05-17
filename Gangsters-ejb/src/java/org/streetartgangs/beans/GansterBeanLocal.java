/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.streetartgangs.beans;

import java.util.List;
import javax.ejb.Local;
import org.streetartgangs.entities.GangsterLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Local
public interface GansterBeanLocal {
    
    public void add(GangsterLocation location);
    public List<GangsterLocation> findAll();
}
