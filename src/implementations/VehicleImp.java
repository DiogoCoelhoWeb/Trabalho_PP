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
    private ItemType loadedType;
    
    /**
     * The total Distance of the route
     */
    private int maxCapacity;
    private boolean status;
    private ItemType itemType;
    
    public VehicleImp (ItemType itemType , int maxCapacity, boolean status){
        this.itemType = itemType;
        this.maxCapacity = maxCapacity;
        this.status = status;
    }
    
    @Override
    public ItemType getSupplyType(){
        return this.itemType;
    }
    
    @Override
    public double getMaxCapacity(){
        return this.maxCapacity;
    }
    
    
    // This method enables or disables the vehicle;
    public void setStatus(boolean status){
        this.status = status;
    }
    
    public boolean getStatus(){
        return this.status;
    }
    
    
    
}