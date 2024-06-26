/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;

import Classes.RefrigeratedVehicles;
import com.estg.core.AidBox;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;

/**
 *
 * @author diogo
 */
public class RouteValidatorImp implements RouteValidator {

   
    
    public RouteValidatorImp(){

    }
    
    @Override
    public boolean validate(Route route, AidBox aidbox) {

        if (route == null || aidbox == null) {
            return false;
        }

        
        // Verificar se a aidbox tem pelos menos 1 tipo de contentor que o veiculo da rota pode carregar
        for (int i = 0; i < aidbox.getContainers().length; i++) {
            if (route.getVehicle().getSupplyType() != aidbox.getContainers()[i].getType()) {
                return false;
            }
        }

        // Verificar se a aidbox tem artigos para levantar 
        for (int i = 0; i < aidbox.getContainers().length; i++) {
            for (int j = 0; j < aidbox.getContainers()[i].getMeasurements().length; j++) {
                if (aidbox.getContainers()[i].getMeasurements()[j].getValue() <= 0) {
                    return false;
                }
            }
        }

        // Verificar se o veiculo consegue aguentar com o peso total da rota 
        if (route.getVehicle().getMaxCapacity() < ((RouteImp) route).totalWeigthRoute()) {
            return false;
        }

        // verificar se o RefrigeratedVehicles consegue fazer o totaldistance 
        if (route.getVehicle() instanceof RefrigeratedVehicles) {
            if (route.getTotalDistance() > ((RefrigeratedVehicles) route.getVehicle()).getMaxKms()) {
                return false;
            }
        }

        return true;
    }
}
