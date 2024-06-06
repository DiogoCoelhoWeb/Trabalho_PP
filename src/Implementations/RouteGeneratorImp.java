/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;

import Exception.ReportException;
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

    @Override
    public Route[] generateRoutes(Institution instn, Strategy strtg, RouteValidator rv, Report report) throws PickingMapException {

        // O relatorio entra vazio , logo os parametros tem que ser vazios
        int nUsedVehicles = 0;
        int nPickedContainers = 0;
        double totalDistance = 0;
        double totalDuration = 0;
        int notPickedContainers = 0;
        int notUsedVehicles = 0;

        report = new ReportImp(0, 0, 0, 0, 0, 0, LocalDateTime.now());
        Route[] routes = strtg.generate(instn, rv);

        for (int i = 0; i < routes.length; i++) {
            Route route = routes[i];
            Vehicle vehicle = route.getVehicle();
            AidBox[] aidBoxes = route.getRoute();

            nUsedVehicles++;
            nPickedContainers += aidBoxes.length;
            notPickedContainers += aidBoxes[i].getContainers().length - 1;
            totalDistance += route.getTotalDistance();
            totalDuration += route.getTotalDuration();

        }

        ((ReportImp) report).setDate(LocalDateTime.now());
        ((ReportImp) report).setPickedContainers(nPickedContainers);
        ((ReportImp) report).setNotPickedContainers(notPickedContainers);
        ((ReportImp) report).setTotalDistance(totalDistance);
        ((ReportImp) report).setTotalDuration(totalDuration);
        ((ReportImp) report).setUsedVehicles(nUsedVehicles);
        notUsedVehicles = instn.getVehicles().length - nUsedVehicles;
        ((ReportImp) report).setNotUsedVehicles(notUsedVehicles);

        PickingMapImp aux = new PickingMapImp(LocalDateTime.now(), routes, routes.length);
        try {
            instn.addPickingMap(aux);
        } catch (PickingMapException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            ((InstitutionImp) instn).addReport(report);
        } catch (ReportException e) {
            System.out.println(e.getMessage());
        }

        return routes;

    }

}
