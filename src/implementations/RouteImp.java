/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import com.estg.core.AidBox;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
/**
 *
 * @author diogo
 */
public class RouteImp implements Route {
    
    
     /**
     * The total Distance of the route
     */
    private double totalDistance;
    
     /**
     * The total Duration of the route
     */
    private double totalDuration;
    
    /**
     * The vehicle of the route
     */
    private Vehicle vehicle;
    
     /**
     * The array with all the aidboxes of the route
     */
    private AidBox[] aidbox; 
    
    
    
    /**
     * Constructor for the Route
     * @param totalDistance Distance of the route 
     * @param totalDuration Total duration of the route
     */
    public RouteImp(double totalDistance, double totalDuration){
        this.totalDuration = totalDistance;
        this.totalDistance = totalDistance;
    }
    
    
    /**
     * Gets the total distance of the route
     * @return the totalDistance
     */
    @Override
    public double getTotalDistance(){
        return this.totalDistance;
    }
    
    /**
     * Gets the total duration to travel the route
     * @return the totalDuration
     */
    @Override
    public double getTotalDuration(){
        return this.totalDuration;
    }
    
    /**
     * Gets the vehicle of the route
     * @return the vehicle
     */
    @Override
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    
    /**
     * Gets the route by each aidbox
     * @return the route
     */
    @Override
    public AidBox[] getRoute(){
        return this.aidbox;
    }
    
    /**
     * Checks if the aidbox is already at the array
     * @param aidbox the Aid Box to check
     * @return i if aidbox exists in the array , otherwise return -1 if cannot find i
     * 
     */
    private int searchAidBox(AidBox aidbox){
        for ( int i = 0 ; 0 < this.aidbox.length ; i++){
            if (aidbox.equals(this.aidbox[i])){
                return i;
            }
        }
        return -1;
    }
   
    /**
     * Checks if the route contains an Aid Box
     * @param aidbox the Aid Box to check
     * @return true if the route contains the Aid Box, false otherwise
     */
    @Override
    public boolean containsAidBox(AidBox aidbox){
        if (searchAidBox(aidbox) != -1){
            return true;
        }
        return false;
    }
    
    
       
    
}
