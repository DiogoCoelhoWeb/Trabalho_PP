/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Enums.VehicleStatus;
import com.estg.core.ItemType;
import implementations.VehicleImp;

/**
 *
 * @author diogo
 */
public class RefrigeratedVehicles extends VehicleImp{
    
    private double maxKms;
    
    public RefrigeratedVehicles(int maxCapacity, VehicleStatus status, double maxKms){
        super(maxCapacity, status);
        super.setItemType(ItemType.PERISHABLE_FOOD);
        this.maxKms = maxKms;    
    }
    
    public double getMaxKms(){
        return this.maxKms;
    }
}
