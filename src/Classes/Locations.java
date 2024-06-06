/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import org.json.simple.JSONObject;

/**
 *
 * @author diogo
 */
public class Locations {

    private String from;
    private Location[] locationTo;

    public Locations(String from) {
        this.from = from;
    }


    
    public void setNameFrom(String nameFrom) {
        this.from = nameFrom;
    }

    public void setLocationTo(Location[] locationTo) {
        this.locationTo = locationTo;
    }

    
    
    public String getNameFrom() {
        return from;
    }

    public Location[] getLocationTo() {
        return locationTo;
    }

    public void setLocation(Location[] location) {
        this.locationTo = location;
    }

    
    
}
