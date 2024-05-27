/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.PickingMap;
import Enums.VehicleStatus;
import com.estg.core.Container;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;

/**
 *
 * @author diogo
 */
public class InstitutionImp implements Institution {

    private static int EXPAND_AIDBOX = 2;

    private String name;
    private int nAidBoxes;
    private int nContainers;
    private int nVehicles;
    private int nPickingMaps;

    private Vehicle[] vehicles;
    private AidBox[] aidBoxes;
    private Container[] containers;
    private PickingMap[] pickingMaps;

    // method constructor
    public InstitutionImp() {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public AidBox[] getAidBoxes() {
        return this.aidBoxes;
    }

    @Override
    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    private int searchVehicle(Vehicle vhcl) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vhcl.equals(this.vehicles[i])) {
                return i;
            }
        }
        return -1;
    }

    private int searchContainer(Container container){
        
        for ( int i = 0; i < containers.length; i++){
            if (container.equals(this.containers[i])){
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void disableVehicle(Vehicle vhcl) throws VehicleException {

        if (searchVehicle(vhcl) == -1) {
            throw new VehicleException("Vehicle not found in this institution");
        }

        if (((VehicleImp) vhcl).getStatus() == VehicleStatus.DISABLED) {
            throw new VehicleException("Vehicle already disabled");
        }

        ((VehicleImp) vhcl).setStatus(VehicleStatus.DISABLED);
    }

    public void enableVehicle(Vehicle vhcl) throws VehicleException {

        if (searchVehicle(vhcl) == -1) {
            throw new VehicleException("Vehicle not found in this institution");
        }

        if (((VehicleImp) vhcl).getStatus() == VehicleStatus.ENABLED) {
            throw new VehicleException("Vehicle already enabled");
        }

        ((VehicleImp) vhcl).setStatus(VehicleStatus.ENABLED);
    }
    
    public boolean addVehicle(Vehicle vhcl) throws VehicleException{
        
        if ( vhcl == null){
            throw new VehicleException("Vehicle cannot be null");
        }
        
        // CONTINUAR 
    } 

    private void expandAidBoxArray() {

        AidBox aux[] = new AidBox[this.aidBoxes.length * EXPAND_AIDBOX];

        for (int i = 0; i < this.aidBoxes.length; i++) {
            aux[i] = this.aidBoxes[i];
        }

        this.aidBoxes = aux;
    }

    private boolean checkSameContainer(AidBox aidbox) {

        Container[] aux = aidbox.getContainers();
        for (int i = 0; i < aux.length; i++) {
            for (int j = i + 1; j < aux.length; j++) {
                if (aux[i].getType() == aux[j].getType()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean addAidBox(AidBox aidbox) throws AidBoxException {

        if (aidbox == null) {
            throw new AidBoxException("Aidbox cannot be null");
        }
        
        if (checkSameContainer(aidbox) == false){
            throw new AidBoxException("Aidbox has duplicated containers types");
        }
        
        if (this.nAidBoxes == this.aidBoxes.length) {
            expandAidBoxArray();
        }
        
        this.aidBoxes[this.nAidBoxes++] = aidbox;
        return true;
    }
    
    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException{
        
        if (searchContainer(cntnr) == -1){
            throw new ContainerException("Container does not exist");
        }
        
        if (msrmnt.getValue() < 0 || msrmnt.getValue() > ((ContainerImp)cntnr).getMaxCapacity()){
            throw new MeasurementException("Invalid measurement");
        }
        
        // W T F , "Adds a new measurement to the Institution considering the Aid Box and container" , OK SIM E COMO
        // É QUE SABE QUAL É O AIDBOX LOL
    }
    
    
}
