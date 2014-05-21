package org.streetartgangs.beans;

import java.util.List;
import javax.ejb.Local;
import org.streetartgangs.entities.UserLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Local
public interface UserLocationBeanLocal {
    
    public void add(UserLocation location);
    public List<UserLocation> findAll();
}
