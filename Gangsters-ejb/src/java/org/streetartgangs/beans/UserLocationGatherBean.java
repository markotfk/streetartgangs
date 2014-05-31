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
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonParser;
import org.streetartgangs.entities.StreetArtUser;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
@LocalBean
public class UserLocationGatherBean {

    @EJB 
    UserBeanLocal ub;
    
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
            List<StreetArtUser> gangsters = null;
            StreetArtUser instance = null;
            while (parser.hasNext()) 
            {
                JsonParser.Event event = parser.next();
                switch(event) {
                    case START_ARRAY:
                        gangsters = new ArrayList<>();
                        break;
                    case END_ARRAY:
                    case START_OBJECT:
                        instance = new StreetArtUser();
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
                       System.out.println(event.toString());
                       break;
                    case KEY_NAME:
                       System.out.print(event.toString() + " " +
                                        parser.getString() + " - ");
                       break;
                    case VALUE_STRING:
                    case VALUE_NUMBER:
                       System.out.println(event.toString() + " " +
                                          parser.getString());
                       break;
                 }
            }
            if (gangsters != null) 
            {
                for (StreetArtUser g : gangsters) 
                {
                    ub.addUser(g);
                }
            }
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, "gatherData error: {0}", e.getMessage());
        }
    }
}
