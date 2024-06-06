/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;
import java.time.LocalDateTime;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.PickingMap;
/**
 *
 * @author diogo
 */
public class PickingMapImp implements PickingMap {
    
    /**
     * The date of the Picking Map
     */
    private LocalDateTime date;
    
    /**
     * The array of routes
     */
    private Route[] routes;
    
    /**
     * Constructor for the Equipment
     * 
     * @param date date of the picking map 
     * @param routes array of the routes
     */
    public PickingMapImp(LocalDateTime date, Route[] routes){
        this.date = date;
        this.routes = routes;
    }
    
    /**
     * Gets the date
     * @return LocalDateTime - the date of the picking map
     */
    public LocalDateTime getDate(){
        return this.date;
    } 
    
    /**
     * Gets routes of the picking map
     * @return Route - the routes of the picking map 
     */
    public Route[] getRoutes(){
        return this.routes;
    }
}
