package org.streetartgangs.beans;

import javax.ejb.Local;
import org.streetartgangs.entities.StreetArtUser;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Local
public interface UserBeanLocal {
    
    public void addUser(StreetArtUser user);
    
    public StreetArtUser find(Long id);
}
