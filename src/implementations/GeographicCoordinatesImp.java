/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import com.estg.core.GeographicCoordinates;
/**
 *
 * @author diogo
 */
public class GeographicCoordinatesImp implements GeographicCoordinates {
    
    /**
     * The latitude 
     */
    private double latitude;
    
    /**
     * The longitudee
     */
    private double longitude;
    
    
    /**
     * Constructor for the GeographicCoordinates
     * @param latitude Distance of the route 
     * @param longitude Total duration of the route
     */
    public GeographicCoordinatesImp(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    
    /**
     * Gets the latitude
     * @return the latitude
     */
    @Override
    public double getLatitude(){
        return this.latitude;
    }
    
    
    /**
     * Gets the longitude
     * @return the longitude
     */
    @Override
    public double getLongitude(){
        return this.longitude;
    }
    
    public String toString(){
        String s = " ";
        s += "Latitute:" + this.latitude + "\n";
        s += "longitude:" + this.longitude + "\n";
        return s;
    }
    
}
