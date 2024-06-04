/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.AidBox;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import com.estg.core.Container;
import com.estg.core.exceptions.AidBoxException;
import Classes.Locations;

/**
 *
 * @author diogo
 */
public class RouteImp implements Route {

    /**
     * The constant to increase the aidbox array
     */
    private static final int EXPAND_ARRAY = 2;

    /**
     * The constant to initialize the array
     */
    private static int INICIAL_AIDBOX = 2;

    /**
     * The number of aidboxes in the route
     */
    private int nAidBoxes;

    /**
     * The total Distance of the route
     */
    private double totalDistance;

    /**
     * The total Duration of the route
     */
    private double totalDuration;

    /**
     * The vehicle of the route
     */
    private Vehicle vehicle;

    /**
     * The array with all the aidboxes of the route
     */
    private AidBox[] aidBoxes;

    /**
     * 
     */
    private Locations[] locations;
    
    /**
     * Constructor for the Route
     *
     */
    public RouteImp() {
        this.nAidBoxes = 0;
        this.aidBoxes = new AidBox[INICIAL_AIDBOX];
    }

    /**
     * Gets the total distance of the route
     *
     * @return the totalDistance
     */
    @Override
    public double getTotalDistance() {
        double totalDistance = 0;
        
        for ( int i = 0; i < this.aidBoxes.length ; i++){
            totalDistance += ((AidBoxImp)this.aidBoxes[i]).getValueDistance(this.aidBoxes[i], this.aidBoxes[i+1]);
        }
        return totalDistance;
    }

    /**
     * Gets the total duration to travel the route
     *
     * @return the totalDuration
     */
    @Override
    public double getTotalDuration() {
        double totalDuration = 0;
        
        for ( int i = 0; i < this.aidBoxes.length ; i++){
            totalDuration += ((AidBoxImp)this.aidBoxes[i]).getValueDuration(this.aidBoxes[i], this.aidBoxes[i+1]);
        }
        return totalDuration;
    }

    /**
     * Gets the vehicle of the route
     *
     * @return the vehicle
     */
    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Gets the route by each aidbox
     *
     * @return the route
     */
    @Override
    public AidBox[] getRoute() {
        return this.aidBoxes;
    }

    /**
     * Checks if the aidbox is already at the array of the route
     *
     * @param aidbox the Aid Box to check
     * @return int - i if aidbox exists in the array , otherwise return -1 if
     * cannot find i
     */
    private int searchAidBoxAtRoute(AidBox aidbox) {
        for (int i = 0; i < this.aidBoxes.length; i++) {
            if (aidbox.equals(this.aidBoxes[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the route contains an Aid Box
     *
     * @param aidbox the Aid Box to check
     * @return boolean - true if the route contains the Aid Box, false otherwise
     */
    @Override
    public boolean containsAidBox(AidBox aidbox) {
        return searchAidBoxAtRoute(aidbox) != -1;
    }

    /**
     * Increase the capacity of the array Aidbox[], twice the current size This
     * is creating a temporary array (aux) with the new size, and then copying
     * the old array to the new one, replacing it
     */
    private void expandAidBoxArray() {

        AidBox aux[] = new AidBox[this.aidBoxes.length * EXPAND_ARRAY];

        for (int i = 0; i < this.aidBoxes.length; i++) {
            aux[i] = this.aidBoxes[i];
        }

        this.aidBoxes = aux;
    }

    /**
     * private boolean hasContainer(ItemType it){
     *
     * for (int i = 0 ; i < this.aidboxes.length; i++){ Container[] aux =
     * aidboxes[i].getContainers(); for (int j = 0; j < aux.length; j++){ if
     * (aux[j].getType() == it){ return true; } } } return false; }
     */
    /**
     * This method add a new aidbox to the route
     *
     * @param aidbox receives the aidbox to add
     * @throws RouteException when aidbox is null , or if already exists , or if
     * the aidbox has containers that isnt the same type as the vehicle of the
     * route
     */
    @Override
    public void addAidBox(AidBox aidbox) throws RouteException {
        Container aux = aidbox.getContainer(this.vehicle.getSupplyType());

        if (aidbox == null) {
            throw new RouteException("Cannot add a null aidbox");
        }

        if (searchAidBoxAtRoute(aidbox) != -1) {
            throw new RouteException("Aidbox already at the route");
        }

        if (!((ContainerImp) aux).hasContainer(this.vehicle.getSupplyType(), aidbox)) {
            throw new RouteException("Aidbox is not compatible with the vehicle designated to the route");
        }

        if (this.nAidBoxes == this.aidBoxes.length) {
            expandAidBoxArray();
        }

        this.aidBoxes[this.nAidBoxes++] = aidbox;
    }

    /**
     * This method removes a aidbox from the route
     *
     * @param aidbox receives the aidbox to remove
     * @throws RouteException - when aidbox is null , or if the aidbox to remove
     * is not in the route
     * @return aidbox - the removed aidbox
     */
    @Override
    public AidBox removeAidBox(AidBox aidbox) throws RouteException {
        int index;

        if (aidbox == null) {
            throw new RouteException("Cannot remove a null aidbox");
        }

        if (searchAidBoxAtRoute(aidbox) == -1) {
            throw new RouteException("Aidbox does not exist in the route");
        }

        index = searchAidBoxAtRoute(aidbox);

        this.aidBoxes[index] = this.aidBoxes[this.nAidBoxes];
        this.aidBoxes[this.nAidBoxes--] = null;
        return aidbox;

    }

    

    @Override
    public void insertAfter​(AidBox after, AidBox toInsert) throws RouteException {

        if (after == null || toInsert == null) {
            throw new RouteException("Aidbox cannot be null");
        }

        if (!containsAidBox(after)) {
            throw new RouteException("Aid Box to insert before is not in the route");
        }

        if (containsAidBox(toInsert)) {
            throw new RouteException("Aid Box to insert is already in the route");
        }

        if (toInsert.getContainer(this.vehicle.getSupplyType()) == null) {
            throw new RouteException("Aid Box to insert is not compatible with the vehicle");
        }

        int index = searchAidBoxAtRoute(after);
        
        if ( this.nAidBoxes == this.aidBoxes.length ){
            expandAidBoxArray();
        }
        
        for (int i = this.nAidBoxes ; i > index ; i--){
            this.aidBoxes[i+1] = this.aidBoxes[i];
        }
        
        this.aidBoxes[index + 1] = toInsert;
        this.nAidBoxes++;
    }

    /**
     * This method replaces a aidbox for another
     *
     * @param aidbox receives the aidbox already at the route
     * @param aidbox1 the aid box that will substitute the current one
     * @throws RouteException - when aidbox is null , or if the aidbox is not at
     * the route , or if the aidbox1 is already at the route
     */
    @Override
    public void replaceAidBox(AidBox aidbox, AidBox aidbox1) throws RouteException {

        Container aux = aidbox1.getContainer(this.vehicle.getSupplyType());

        if (aidbox == null || aidbox1 == null) {
            throw new RouteException("Cannot remove a null aidbox");
        }

        if (searchAidBoxAtRoute(aidbox) == -1) {
            throw new RouteException("Aidbox does not exist in the route");
        }

        if (searchAidBoxAtRoute(aidbox1) != -1) {
            throw new RouteException("Aidbox to substitute is already at the route");
        }

        if (!((ContainerImp) aux).hasContainer(this.vehicle.getSupplyType(), aidbox1)) {
            throw new RouteException("Aidbox cannot be replaced . Is not compatible with the vehicle designated to the route");
        }

        aidBoxes[searchAidBoxAtRoute(aidbox)] = aidbox1;

    }

    protected double totalWeigthRoute() {

        double totalWeigth = 0;

        for (int i = 0; i < this.aidBoxes.length; i++) {
            totalWeigth += ((ContainerImp) this.aidBoxes[i].getContainer(this.vehicle.getSupplyType())).lastMeasurement();
        }
        return totalWeigth;
    }
}
