/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.Institution;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Strategy;
import Classes.RefrigeratedVehicles;
import Enums.VehicleStatus;
import com.estg.core.AidBox;
import com.estg.core.ItemType;
import java.time.LocalDate;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;

/**
 *
 * @author diogo
 */
public class StrategyImp implements Strategy {

    private String name;
    private ItemType typeOfRoute;

    public StrategyImp(String name) {
        this.name = name;
    }

    @Override
    public Route[] generate(Institution instn, RouteValidator rv) {
        int index = 0;

        // As aidboxes da instituiçao para fazer marcaçao ponto a ponto
        Route[] routes = new Route[instn.getAidBoxes().length];

        for (int i = 0; i < instn.getAidBoxes().length; i++) {
            Vehicle vehicle = chooseVehicle(instn.getVehicles(), instn.getAidBoxes()[i]);

            if (vehicle != null) {
                RouteImp route = new RouteImp(((VehicleImp) vehicle));
                try {
                    route.addAidBox(instn.getAidBoxes()[i]);
                } catch (RouteException e) {
                }
                if (rv.validate(route, instn.getAidBoxes()[i])) {
                    routes[index] = route;
                    index++;
                }
            }
            else{
                return null;
            }
        }

        Route[] generateRoutes = new Route[index];

        for (int i = 0; i < index; i++) {
            generateRoutes[i] = routes[i];
        }
        
        return generateRoutes;
    }

    private Vehicle chooseVehicle(Vehicle[] vehicle, AidBox aidbox) {

        for (int i = 0; i < vehicle.length; i++) {
            if (((VehicleImp) vehicle[i]).getStatus() == VehicleStatus.ENABLED && vehicle[i].getMaxCapacity() >= ((AidBoxImp) aidbox).totalWeightAidbox()) {
                return vehicle[i];
            }
        }
        return null;
    }
}
