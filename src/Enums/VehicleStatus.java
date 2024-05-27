/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author diogo
 */
public enum VehicleStatus {
    ENABLED, DISABLED;
    
    public static String vehicleStatusToString(VehicleStatus vs){
        switch(vs){
            case ENABLED:
                return "This vehicle is enabled";
            case DISABLED:
                return "This vehicle is disabled";
            default:
                return "Unknown Status";
        }
    }
}
