package org.streetartgangs.beans;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonParser;
import org.streetartgangs.entities.StreetArtUser;
import org.streetartgangs.entities.UserLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
@LocalBean
public class UserLocationGatherBean {

    @EJB 
    UserLocationBeanLocal ub;
    
    private final int unknown = 0;
    private final int id = 1;
    private final int userId = 2;
    private final int latitude = 3;
    private final int longitude = 4;
    private final int gang = 5;
    
    private int state = 0;
    
    private final Logger logger = Logger.getLogger(UserLocationGatherBean.class.getName());
    
    private URL urlBase;

    public UserLocationGatherBean() {
        try {
            this.urlBase = new URL("http://vm0063.virtues.fi/gangsters/?format=json");
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE, "GansterDataGatherTimerBean() error:{0}", ex.getMessage());
        }
    }
    
    @Schedule(minute = "0-59", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "0-23", dayOfWeek = "*")
    public void gatherData() {
        try {
            HttpURLConnection urlConn = (HttpURLConnection)urlBase.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.setRequestProperty("Content-type", "application/json");
            urlConn.setConnectTimeout(45000);
            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            BufferedReader bRead = new BufferedReader(new InputStreamReader(in));
            JsonParser parser = Json.createParser(bRead);
            List<UserLocation> gangsters = null;
            UserLocation instance = null;
            while (parser.hasNext()) 
            {
                JsonParser.Event event = parser.next();
                switch(event) {
                    case START_ARRAY:
                        gangsters = new ArrayList<>();
                        break;
                    case END_ARRAY:
                    case START_OBJECT:
                        instance = new UserLocation();
                        break;
                    case END_OBJECT:
                        if (gangsters != null && instance != null) 
                        {
                            gangsters.add(instance);
                            instance = null;
                        }
                        break;
                    case VALUE_FALSE:
                    case VALUE_NULL:
                    case VALUE_TRUE:
                        handleValue(event.toString(), instance, gangsters);
                       break;
                    case KEY_NAME:
                        handleKey(parser.getString());
                        break;
                    case VALUE_STRING:
                        handleValue(parser.getString(), instance, gangsters);
                        break;
                    case VALUE_NUMBER:
                        handleValue(parser.getInt(), instance, gangsters);
                        break;                       
                    default:
                        break;
                 }
            }
            ub.addMany(gangsters);
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, "gatherData error: {0}", e.getMessage());
        }
        
    }
    
    private void handleValue(Object val, UserLocation user, List<UserLocation> gangsters) {
        switch (state) {
            case id:
                user.setId((Integer)val);
                break;
            case userId:
                user.setUserId((Integer)val);
                break;
            case longitude:
                user.setLongitude(Double.parseDouble((String)val));
                break;
            case latitude:
                user.setLatitude(Double.parseDouble((String)val));
                break;
            case gang:
                user.setGang((Integer)val);
                break;
            default:
                break;
        }
        gangsters.add(user);
    
    }
    
    
    private void handleKey(String key) {
        if (key == null) {
            return;
        }
        switch (key) {
            case "id":
                state = id;
                break;
            case "userId":
                state = userId;
                break;
            case "latitude":
                state = latitude;
                break;
            case "longitude":
                state = longitude;
                break;
            case "gang":
                state = gang;
                break;
            default:
                state = unknown;
                break;
        }
    }
}
