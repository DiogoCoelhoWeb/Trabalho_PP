/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

package project;
import com.estg.core.ItemType;
import com.estg.pickingManagement.Vehicle;
/**
 *
 * @author diogo
 */
public class VehicleImp implements Vehicle{
    
    /**
     * To check if has items loaded or not
     */
    private boolean loaded;
    
    /**
     * The total Distance of the route
     */
    private int maxCapacity;
    private boolean status;
    private ItemType vehicleType;
    
    public VehicleImp (ItemType vehicleType , int maxCapacity, boolean status){
        this.vehicleType = vehicleType;
        this.maxCapacity = maxCapacity;
        this.status = status;
    }
    
    @Override
    public ItemType getSupplyType(){
        return this.vehicleType;
    }
    
    @Override
    public double getMaxCapacity(){
        return this.maxCapacity;
    }
    
    
    // This method enables or disables the vehicle;
    public void setStatus(boolean status){
        this.status = status;
    }
}