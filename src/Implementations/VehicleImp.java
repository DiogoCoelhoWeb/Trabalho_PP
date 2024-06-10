/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;

import com.estg.core.ItemType;
import com.estg.pickingManagement.Vehicle;
import Enums.VehicleStatus;

/**
 *
 * @author diogo
 */
public class VehicleImp implements Vehicle {

    /**
     * The max capacity of the vehicle
     */
    private int maxCapacity;

    /**
     * The vehicle status ( enable or disabled )
     */
    private VehicleStatus status;

    /**
     * The type of storage the vehicle can carry
     */
    private ItemType itemType;

    /**
     * The constructor of vehicle
     *
     * @param itemType - type of storage the vehicle can carry
     * @param maxCapacity - max capacity of the vehicle
     * @param status - vehicle status ( enable or disabled )
     */
    public VehicleImp(int maxCapacity, VehicleStatus status, ItemType it) {
        this.maxCapacity = maxCapacity;
        this.status = status;
        this.itemType = it;
    }

    /**
     * Gets the itemtype of the vehicle
     *
     * @return ItemType - the itemtype
     */
    @Override
    public ItemType getSupplyType() {
        return this.itemType;
    }

    /**
     * Gets the max capacity of the vehicle
     *
     * @return double - the max capacity
     */
    @Override
    public double getMaxCapacity() {
        return this.maxCapacity;
    }

    /**
     * sets the status of the vehicle
     *
     * @param status - The status of the vehicle
     */
    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    /**
     * Gets the status of the vehicle
     *
     * @return VehicleStatus - the status of the vehicle
     */
    public VehicleStatus getStatus() {
        return this.status;
    }
    
    public void setItemType(ItemType it){
        this.itemType = it;
    }
    
    

}
