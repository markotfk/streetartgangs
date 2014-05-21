package org.streetartgangs.beans;

import javax.ejb.Local;
import org.streetartgangs.entities.User;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Local
public interface UserBeanLocal {
    
    public void addUser(User user);
    
    public User find(Long id);
}
