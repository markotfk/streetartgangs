/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.streetartgangs;

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
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonParser;
import org.jboss.logging.Logger;
import org.streetartgangs.beans.GansterBeanLocal;
import org.streetartgangs.entities.GangsterLocation;

/**
 *
 * @author Marko Karjalainen <markotfk@gmail.com>
 */
@Stateless
@LocalBean
public class GansterDataGatherTimerBean {

    @EJB 
    GansterBeanLocal gb;
    
    private final Logger logger = Logger.getLogger(GansterDataGatherTimerBean.class.getName());
    
    private URL urlBase;

    public GansterDataGatherTimerBean() {
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
        
        /*
        //Load venues or redirect
     jQuery(document).ready(function(){

  var endpoint = "http://vm0063.virtues.fi/gangsters/";
       $.ajax({
         type: "GET",
         url: endpoint,
         dataType: 'json',
         beforeSend: function (xhr) {
         }
       }).done(function( data ) {

for (var i = data.length - 1; i >= 0; i--) { //from all the gangsters in the list

var gangster = data[i].username; // I want these details on one line in text file or table every 1 or 2 min
var latitude = data[i].latitude;
var longitude = data[i].longitude;
var gang = data[i].color;

}
}).fail(function( jqXHR, textStatus ) {
       //TODO fix this
         alert("Error: something went wrong while loading the profile: "+ textStatus);
       });
}*/
    
}
