/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;

import Enums.VehicleStatus;
import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.Report;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Strategy;
import com.estg.pickingManagement.Vehicle;
import java.time.LocalDateTime;

/**
 *
 * @author diogo
 */
public class RouteGeneratorImp implements RouteGenerator {

    public Route[] generateRoutes(Institution instn, Strategy strtg, RouteValidator rv, Report report) throws PickingMapException {
        
        // O relatorio entra vazio , logo os parametros tem que ser vazios
        int nUsedVehicles = 0;
        int nPickedContainers = 0;
        double totalDistance = 0;
        double totalDuration = 0;
        int notPickedContainers = 0;
        int notUsedVehicles = 0;

        ReportImp reportImp = new ReportImp(0, 0, 0, 0, 0, 0, LocalDateTime.now());
        Route[] routes = strtg.generate(instn, rv);

        for (int i = 0; i < routes.length; i++) {
            Route route = routes[i];
            Vehicle vehicle = route.getVehicle();
            AidBox[] aidBoxes = route.getRoute();
            
            if ( ((VehicleImp)vehicle).getStatus() != VehicleStatus.DISABLED && vehicle != null){
                reportImp.setUsedVehicles(nUsedVehicles++ + 1);
            }else{
                reportImp.setNotUsedVehicles(notUsedVehicles++ + 1);
            }
            
            nPickedContainers += aidBoxes.length;
            reportImp.setPickedContainers(nPickedContainers);
            
            totalDistance += route.getTotalDistance();
            totalDuration += route.getTotalDuration();
            reportImp.setTotalDistance(totalDistance);
            reportImp.setTotalDuration(totalDuration);
        }
        
        reportImp.setDate(LocalDateTime.now());
        
        return routes;
        
    }

}
