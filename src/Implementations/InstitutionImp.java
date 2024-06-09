/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;

import Classes.Locations;
import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.PickingMap;
import Enums.VehicleStatus;
import Exception.ReportException;
import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.Report;
import java.time.LocalDateTime;

/**
 *
 * @author diogo
 */
public class InstitutionImp implements Institution {

    /**
     * The expand array constant
     */
    private static final int EXPAND_ARRAY = 2;

    /**
     * The name of the institution
     */
    private String name;

    /**
     * The number of aidboxes
     */
    private int nAidBoxes;

    /**
     * The number of vehicles
     */
    private int nVehicles;

    /**
     * The number of picking maps
     */
    private int nPickingMaps;

    private int nReports;

    /**
     * The array of vehicles
     */
    private Vehicle[] vehicles;

    /**
     * The array of aidboxes
     */
    private AidBox[] aidBoxes;

    private PickingMap[] pickingMaps;

    private Report[] reports;

    private Locations[] locations;

    public InstitutionImp(String name, AidBox[] aidBoxes) {

        this.name = name;
        this.aidBoxes = aidBoxes;

    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getNpickingMaps() {
        return this.nPickingMaps;
    }

    @Override
    public AidBox[] getAidBoxes() {
        return this.aidBoxes;
    }

    @Override
    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    private int searchAidBox(AidBox aidbox) {
        if (this.aidBoxes != null) {
            for (int i = 0; i < this.aidBoxes.length; i++) {
                if (aidbox.equals(this.aidBoxes[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Container getContainer(AidBox aidbox, ItemType it) throws ContainerException {

        Container[] aux = aidbox.getContainers();

        if (searchAidBox(aidbox) == -1) {
            throw new ContainerException("Aidbox does not exist");
        }

        for (int i = 0; i < aux.length; i++) {
            if (aux[i].getType() != it) {
                throw new ContainerException("Container type does not exist in this aidbox");
            }

        }
        return aidBoxes[searchAidBox(aidbox)].getContainer(it);
    }

    // distancia da base a aidbox ( a base esta sempre na primeira posiçao
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {

        if (aidbox == null) {
            throw new AidBoxException("Aidbox cannot be null");
        }

        for (int i = 0; i < this.locations.length; i++) {

            if (aidbox.getCode().equals(this.aidBoxes[i].getCode())) {
                return locations[i].getLocationTo()[0].getDistance();
            }

        }
        return -1;
    }

    private int searchVehicle(Vehicle vhcl) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vhcl.equals(this.vehicles[i])) {
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

        ((VehicleImp) vhcl).setStatus(VehicleStatus.ENABLED);
    }

    private void expandVehicleArray() {
        Vehicle aux[] = new Vehicle[this.nVehicles * EXPAND_ARRAY];

        for (int i = 0; i < this.vehicles.length; i++) {
            aux[i] = this.vehicles[i];
        }

        this.vehicles = aux;
    }

    @Override
    public boolean addVehicle(Vehicle vhcl) throws VehicleException {

        if (vhcl == null) {
            throw new VehicleException("Vehicle cannot be null");
        }

        if (searchVehicle(vhcl) != -1) {
            return false;
        }

        if (this.nVehicles == this.vehicles.length) {
            expandVehicleArray();
        }

        this.vehicles[this.nVehicles++] = vhcl;
        return true;

    }

    private void expandAidBoxArray() {

        AidBox aux[] = new AidBox[this.aidBoxes.length * EXPAND_ARRAY];

        for (int i = 0; i < this.nAidBoxes; i++) {
            aux[i] = this.aidBoxes[i];
        }

        this.aidBoxes = aux;
    }

    private boolean checkSameContainer(AidBox aidbox) {

        Container[] aux = aidbox.getContainers();
        if (aux == null) {
            return true; 
        }

        for (int i = 0; i < aux.length; i++) {
            for (int j = i + 1; j < aux.length; j++) {
                if (aux[i].getType() == aux[j].getType()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean addAidBox(AidBox aidbox) throws AidBoxException {

        if (aidbox == null) {
            throw new AidBoxException("Aidbox cannot be null");
        }

        

        if (this.nAidBoxes == this.aidBoxes.length) {
            expandAidBoxArray();
        }

        this.aidBoxes[this.nAidBoxes++] = aidbox;
        return true;
    }

    private boolean compareContainer(Container cntnr) {

        for (int i = 0; i < this.aidBoxes.length; i++) {
            Container[] aux = this.aidBoxes[i].getContainers();
            for (int j = 0; j < aux.length; j++) {
                if (cntnr.equals(aux[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException {

        if (compareContainer(cntnr) == false) {
            throw new ContainerException("The container doesnt exit in the aidbox");
        }

        if (msrmnt.getValue() < 0 || msrmnt.getValue() > ((ContainerImp) cntnr).getMaxCapacity()) {
            throw new MeasurementException("Invalid measurement");
        }

        for (int i = 0; i < this.aidBoxes.length; i++) {
            for (int j = 0; j < this.aidBoxes[i].getContainers().length; j++) {
                if (cntnr.equals(this.aidBoxes[i].getContainers()[j])) {
                    this.aidBoxes[i].getContainers()[j].addMeasurement(msrmnt);
                    return true;
                }
            }
        }
        return false;
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

    @Override
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
        for (int i = 0; i < pm.length - 1; i++) {
            if (((PickingMapImp) pm[i]).getDate().isAfter(((PickingMapImp) pm[i + 1]).getDate())) {
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
    public PickingMap[] getPickingMaps() {
        return this.pickingMaps;
    }

    @Override
    public PickingMap[] getPickingMaps(LocalDateTime ldt, LocalDateTime ldt1) {

        // para ficar um array exato
        int count = 0;
        for (int i = 0; i < this.pickingMaps.length; i++) {
            if (this.pickingMaps[i].getDate().isAfter(ldt) && this.pickingMaps[i].getDate().isBefore(ldt1)) {
                count++;
            }
        }

        // Auxiliar para copiar direitinho o array
        PickingMap[] aux = new PickingMap[count];
        int j = 0;
        for (int i = 0; i < this.pickingMaps.length; i++) {
            if (this.pickingMaps[i].getDate().isAfter(ldt) && this.pickingMaps[i].getDate().isBefore(ldt1)) {
                aux[j++] = this.pickingMaps[i];
            }
        }

        return aux;
    }

    private void expandReportArray() {

        Report aux[] = new Report[this.reports.length * EXPAND_ARRAY];

        for (int i = 0; i < this.aidBoxes.length; i++) {
            aux[i] = this.reports[i];
        }

        this.reports = aux;
    }

    public void addReport(Report report) throws ReportException {

        if (report == null) {
            throw new ReportException("The report cannot be null");
        }

        if (this.nReports == this.reports.length) {
            expandReportArray();
        }

        this.reports[this.nReports++] = report;

    }

    public Report[] getReport() {
        return this.reports;
    }

    public Report getLastReport() {
        return this.reports[this.nReports];
    }

    public void printReport(Report report) {

    }
}
