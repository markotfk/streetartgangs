package org.streetartgangs.entities;

import java.io.Serializable;
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
public class UserLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dbId;

    private Integer id;
    
    private Integer userId;

    private Double latitude;
    
    private Double longitude;
    
    private Long created;
    
    private Integer gang;

    public Integer getGang() {
        return gang;
    }

    public void setGang(Integer gang) {
        this.gang = gang;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    

    public UserLocation() 
    {
        created = System.currentTimeMillis();
    }
    
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
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
        if (!(object instanceof UserLocation)) {
            return false;
        }
        UserLocation other = (UserLocation) object;
        if ((this.dbId == null && other.dbId != null) || (this.dbId != null && !this.dbId.equals(other.dbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.streetartgangs.entities.UserLocation[ id=" + dbId + " ]";
    }
    
}
