package org.streetartgangs.beans;

import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.jboss.logging.Logger;

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
            this.urlBase = new URL("http://vm0063.virtues.fi/gangsters/");
        } catch (MalformedURLException ex) {
            logger.log(Logger.Level.ERROR, "GansterDataGatherTimerBean() error:" + ex.getMessage());
        }
    }
    
    /*@Schedule(minute = "0-59", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "0-23", dayOfWeek = "*")
    public void gatherData() {
        try {
            HttpURLConnection urlConn = (HttpURLConnection)urlBase.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.setConnectTimeout(45000);
            StringBuilder sb = new StringBuilder();
            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            BufferedReader bRead = new BufferedReader(new InputStreamReader(in));
            JsonParser parser = Json.createParser(bRead);
            List<Gangster> gangsters = null;
            Gangster instance = null;
            while (parser.hasNext()) 
            {
                JsonParser.Event event = parser.next();
                switch(event) {
                    case START_ARRAY:
                        gangsters = new ArrayList<>();
                        break;
                    case END_ARRAY:
                    case START_OBJECT:
                        instance = new Gangster();
                        break;
                    case END_OBJECT:
                        if (gangsters != null) 
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
                for (Gangster g : gangsters) 
                {
                    gb.add(g);
                }
            }
            
        } catch (IOException e) {
            logger.log(Logger.Level.ERROR, "gatherData error: " + e.getMessage());
    }
        */
    
}
