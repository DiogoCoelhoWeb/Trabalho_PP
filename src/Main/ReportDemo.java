/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Classes.*;
import com.estg.core.*;
import com.estg.io.*;
import com.estg.pickingManagement.*;
import Enums.*;
import Files.*;
import Implementations.*;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;
import java.time.*;

public class ReportDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PickingMapException {

        // carregar as aidboxes 
        // carregar as mediçoes
        // Podemos colocar um menu para se escolher os valores e criar veiculo
        Vehicle vehicle1 = new VehicleImp(500, VehicleStatus.ENABLED);
        Vehicle vehicle2 = new VehicleImp(300, VehicleStatus.ENABLED);
        Vehicle vehicle3 = new VehicleImp(500, VehicleStatus.ENABLED);
        RefrigeratedVehicles vehicle4 = new RefrigeratedVehicles(450, VehicleStatus.ENABLED, 10000);

        // cria a inst
        Institution isnt = new InstitutionImp("Santa casa", null);
        // cria um picking map para a ins
        PickingMap pm = new PickingMapImp(LocalDateTime.now());
        
        isnt.addPickingMap(pm);
        
        RouteValidator v1 = new RouteValidatorImp();
        Strategy str = new StrategyImp("Estrategia");

        try {
            isnt.addVehicle(vehicle1);
            isnt.addVehicle(vehicle2);
            isnt.addVehicle(vehicle3);
            isnt.addVehicle(vehicle4);
        } catch (VehicleException e) {
            e.getMessage();
        }

        RouteGenerator routeGen = new RouteGeneratorImp();
        Report report = new ReportImp(0, 0, 0, 0, 0, 0, LocalDateTime.now());
        isnt.getPickingMaps().setRoutes(routeGen.generateRoutes(isnt, str, v1, report));
        
        
        // imprimir report
        
        // imprimir pickingmap 
        
        // imprimir o resto para verificar


    }

}
