/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import Classes.RefrigeratedVehicles;
import com.estg.core.AidBox;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;

/**
 *
 * @author diogo
 */
public class RouteValidatorImp implements RouteValidator {

    @Override
    public boolean validate(Route route, AidBox aidbox) {

        if (route == null || aidbox == null) {
            return false;
        }

        // verifica se existe a aidbox na route
        if (!route.containsAidBox(aidbox)) {
            return false;
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
