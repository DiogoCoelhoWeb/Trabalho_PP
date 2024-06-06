/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.Institution;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Strategy;
import com.estg.core.AidBox;
import com.estg.core.ItemType;
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.exceptions.RouteException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class StrategyImp implements Strategy {

    private String name;

    public StrategyImp(String name) {
        this.name = name;

    }

    @Override
    public Route[] generate(Institution instn, RouteValidator rv) {
        RouteImp currentRoute = null;
        Route[] routes = new Route[instn.getAidBoxes().length];
        int routeIndex = 0;
        int vehicleIndex = 0;

        for (int i = 0; i < instn.getAidBoxes().length; i++) {
            AidBox aidBox = instn.getAidBoxes()[i];
            if (currentRoute == null) { // so faz este if para saber se é a primeira rota 
                if (vehicleIndex >= instn.getVehicles().length) {
                    break; // faz o break se nao existir veiculos para a situaçao 
                }
                currentRoute = new RouteImp((VehicleImp) instn.getVehicles()[vehicleIndex]);
                vehicleIndex++;
            }

            try {
                currentRoute.addAidBox(aidBox);
            } catch (RouteException e) {
                System.out.println(e.getMessage());
                continue; // faz skip a aidbox se nao conseguir adicionar
            }

            if (!rv.validate(currentRoute, aidBox)) {
                // se a rota nao é valida com aquela aidbox , começa de novo
                currentRoute = null;
                i--; // Para recomeçar com a mesma aidbox mas outro veiculo 
                continue;
            }

            // Se tudo correr bem , adiciona uma nova rota 
            routes[routeIndex] = currentRoute;
            routeIndex++;
        }

        
        Route[] finalRoutes = new Route[routeIndex];
        for (int i = 0; i < routeIndex; i++) {
            finalRoutes[i] = routes[i];
        }
        
        return finalRoutes;
    }
}
