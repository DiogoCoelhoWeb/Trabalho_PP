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
import com.estg.core.exceptions.InstitutionException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;
import java.io.IOException;
import java.time.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        // carregar as aidboxes 
        // carregar as mediçoes
        // Podemos colocar um menu para se escolher os valores e criar veiculo
        Vehicle vehicle1 = new VehicleImp(500, VehicleStatus.ENABLED);
        Vehicle vehicle2 = new VehicleImp(300, VehicleStatus.ENABLED);
        Vehicle vehicle3 = new VehicleImp(500, VehicleStatus.ENABLED);
        RefrigeratedVehicles vehicle4 = new RefrigeratedVehicles(450, VehicleStatus.ENABLED, 10000);

        // cria a inst
        Institution instn = new InstitutionImp("Santa casa", null);
        Importer importer = new ImporterImp();
        
        try {
            importer.importData​(instn);
        } catch (IOException ex) {

        } catch (InstitutionException ex) {
            
        }
        
        PickingMap pm = new PickingMapImp(LocalDateTime.now());
        
        try {
            instn.addPickingMap(pm);
        } catch (PickingMapException ex) {

        }
        
        RouteValidator v1 = new RouteValidatorImp();
        Strategy str = new StrategyImp("Estrategia");

        try {
            instn.addVehicle(vehicle1);
            instn.addVehicle(vehicle2);
            instn.addVehicle(vehicle3);
            instn.addVehicle(vehicle4);
        } catch (VehicleException e) {
            e.getMessage();
        }

        RouteGenerator routeGen = new RouteGeneratorImp();
        Report report = new ReportImp(0, 0, 0, 0, 0, 0, LocalDateTime.now());
        try {
            ((PickingMapImp)instn.getPickingMaps()[instn.getPickingMaps().length]).setRoutes(routeGen.generateRoutes(instn,str,v1,report));
        } catch (PickingMapException ex) {
            
        }
        
        report = ((InstitutionImp)instn).getLastReport();
        String s = ((ReportImp)report).toString();
        System.out.print(s);
        // imprimir report
        
        // imprimir pickingmap 
        
        // imprimir o resto para verificar


    }

}
