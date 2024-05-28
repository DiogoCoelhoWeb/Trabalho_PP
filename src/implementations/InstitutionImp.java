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
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.PickingMapException;
import java.time.LocalDateTime;

/**
 *
 * @author diogo
 */
public class InstitutionImp implements Institution {

    private static int EXPAND_ARRAY = 2;

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

    private int searchAidBox(AidBox aidbox){
        
        for ( int i = 0 ; i < this.aidBoxes.length; i++){
            if(aidbox.equals(this.aidBoxes[i])){
                return i;
            }
        }
        return -1;
    }
    
    private boolean hasContainer(ItemType it, AidBox aidbox) {

        Container[] aux = aidbox.getContainers();
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].getType() == it) {
                return true;
            }
        }
        return false;
    }

    
    @Override
    public Container getContainer(AidBox aidbox, ItemType it) throws ContainerException{
        
        if ( searchAidBox(aidbox) == -1){
            throw new ContainerException("Aidbox does not exist");
        }
        
        if (!hasContainer(it,aidbox)){
            throw new ContainerException("Container type does not exist in this aidbox");
        }
  
        return aidBoxes[searchAidBox(aidbox)].getContainer(it);
    }
    
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {

    }

    private int searchVehicle(Vehicle vhcl) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vhcl.equals(this.vehicles[i])) {
                return i;
            }
        }
        return -1;
    }

    private int searchContainer(Container container) {

        for (int i = 0; i < containers.length; i++) {
            if (container.equals(this.containers[i])) {
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

    @Override
    public void enableVehicle(Vehicle vhcl) throws VehicleException {

        if (searchVehicle(vhcl) == -1) {
            throw new VehicleException("Vehicle not found in this institution");
        }

        if (((VehicleImp) vhcl).getStatus() == VehicleStatus.ENABLED) {
            throw new VehicleException("Vehicle already enabled");
        }

        ((VehicleImp)vhcl).setStatus(VehicleStatus.ENABLED);
    }

    private void expandVehicleArray() {
        Vehicle aux[] = new Vehicle[this.nVehicles * EXPAND_ARRAY];

        for (int i = 0; i < this.vehicles.length; i++) {
            aux[i] = this.vehicles[i];
        }

        this.vehicles = aux;
    }

    public boolean addVehicle(Vehicle vhcl) throws VehicleException {

        if (vhcl == null) {
            throw new VehicleException("Vehicle cannot be null");
        }

        if (searchVehicle(vhcl) != -1) {
            return false; // e que tal criar uma exceçao aqui?
        }

        if (this.nVehicles == this.vehicles.length) {
            expandVehicleArray();
        }

        this.vehicles[this.nVehicles++] = vhcl;
        return true;

    }

    private void expandAidBoxArray() {

        AidBox aux[] = new AidBox[this.aidBoxes.length * EXPAND_ARRAY];

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

        if (checkSameContainer(aidbox) == false) {
            throw new AidBoxException("Aidbox has duplicated containers types");
        }

        if (this.nAidBoxes == this.aidBoxes.length) {
            expandAidBoxArray();
        }

        this.aidBoxes[this.nAidBoxes++] = aidbox;
        return true;
    }

    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException {

        if (searchContainer(cntnr) == -1) {
            throw new ContainerException("Container does not exist");
        }

        if (msrmnt.getValue() < 0 || msrmnt.getValue() > ((ContainerImp) cntnr).getMaxCapacity()) {
            throw new MeasurementException("Invalid measurement");
        }

        // W T F , "Adds a new measurement to the Institution considering the Aid Box and container" , OK SIM E COMO
        // É QUE SABE QUAL É O AIDBOX LOL
    }

    private int searchPickingMap(PickingMap pm) {
        for (int i = 0; i < this.pickingMaps.length; i++) {
            if (pm.equals(this.pickingMaps[i])) {
                return i;
            }
        }
        return -1;
    }

    private void expandPickingMap() {
        PickingMap[] aux = new PickingMap[this.nPickingMaps * EXPAND_ARRAY];
        for (int i = 0; i < this.pickingMaps.length; i++) {
            aux[i] = this.pickingMaps[i];
        }
        this.pickingMaps = aux;
    }

    public boolean addPickingMap(PickingMap pm) throws PickingMapException {

        if (pm == null) {
            throw new PickingMapException("Picking Map cannot be null");
        }

        if (searchPickingMap(pm) != -1) {
            return false;
        }

        if (this.nPickingMaps == this.pickingMaps.length) {
            expandPickingMap();
        }

        this.pickingMaps[this.nPickingMaps++] = pm;
        return true;

    }

    private int mostRecentPickingMap(PickingMap[] pm) {
        
        int mostRecentIndex = 0;
        for ( int i = 0 ; i < pm.length - 1; i++){
            if( ((PickingMapImp)pm[i]).getDate().isAfter(((PickingMapImp)pm[i + 1]).getDate())){
                mostRecentIndex = i;
            }
        }
        return mostRecentIndex;
    }

    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {

        if (this.nPickingMaps == 0) {
            throw new PickingMapException("There is no picking maps in this institution");
        }

        return this.pickingMaps[mostRecentPickingMap(this.pickingMaps)];
    }
    
    
    @Override
    public PickingMap[] getPickingMaps(){
        return this.pickingMaps;
    } 
    
    @Override
    public PickingMap[] getPickingMaps(LocalDateTime ldt, LocalDateTime ldt1){
        
    }
    
}
