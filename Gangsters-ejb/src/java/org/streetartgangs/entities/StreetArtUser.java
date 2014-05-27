package org.streetartgangs.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Entity
@XmlRootElement
public class StreetArtUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dbId;

    @Column(unique=true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String username;

    private String email;
    
    private Long created;

    public StreetArtUser() {
        created = System.currentTimeMillis();
    }
    
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long id) {
        this.dbId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbId != null ? dbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StreetArtUser)) {
            return false;
        }
        StreetArtUser other = (StreetArtUser) object;
        if ((this.dbId == null && other.dbId != null) || (this.dbId != null && !this.dbId.equals(other.dbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.streetartgangs.entities.User[ id=" + dbId + " ]";
    }
    
}
