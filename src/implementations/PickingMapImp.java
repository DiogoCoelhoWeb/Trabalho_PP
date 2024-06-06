/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import java.time.LocalDateTime;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.PickingMap;
/**
 *
 * @author diogo
 */
public class PickingMapImp implements PickingMap {
    
    private static int INICIALIZE_ROUTE = 5;
    
    /**
     * The date of the Picking Map
     */
    private LocalDateTime date;
    
    /**
     * The array of routes
     */
    private Route[] routes;
    
    private int nRoutes;
    
    /**
     * Constructor for the Equipment
     * 
     * @param date date of the picking map 
     * @param routes array of the routes
     */
    public PickingMapImp(LocalDateTime date, Route[] routes , int size){
        this.date = date;
        this.routes = new Route[size];
        this.nRoutes = 0;
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
    
    public void setRoutes(Route[] routes){
        
        for ( int i = 0; i < routes.length; i++){
            if ( routes[i] != null){
                this.nRoutes++;
            }
        }
        
        if(this.nRoutes == this.routes.length){
            this.routes = new Route[nRoutes];
        }
        
        this.routes = routes;
    }
}
