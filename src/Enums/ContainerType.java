/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author diogo
 */
public enum ContainerType {
    PERISHABLE_FOOD, NON_PERISHABLE_FOOD, CLOTHING, MEDICINE;
    
    public static String contrainerTypeToString(ContainerType CT){
        switch(CT){
                case PERISHABLE_FOOD:
                    return "Container type - PERISHABLE FOOD";
                case NON_PERISHABLE_FOOD:
                    return "Container type - NON PERISHABLE FOOD";
                case CLOTHING:
                    return "Container type - CLOTHING";
                case MEDICINE:
                    return "Container type - MEDICINE";
                default:
                    return "Unknown container type";
        } 
    }
}
