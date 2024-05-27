/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import com.estg.core.ItemType;
import implementations.VehicleImp;

/**
 *
 * @author diogo
 */
public class RefrigeratedVehicles extends VehicleImp{
    
    private int maxKms;
    
    public RefrigeratedVehicles(int maxCapacity,boolean status, int maxKms){
        super(ItemType.PERISHABLE_FOOD, maxCapacity, status);
        this.maxKms = maxKms;    
    }
}
