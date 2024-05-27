/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.AidBox;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import com.estg.core.ItemType;
import com.estg.core.Container;

/**
 *
 * @author diogo
 */
public class RouteImp implements Route {

    /**
     * The constant to increase the aidbox array
     */
    private static int EXPAND_AIDBOX = 2;

    /**
     * The constant to initialize the array
     */
    private static int INICIAL_AIDBOX = 2;

    /**
     * The number of aidboxes in the route
     */
    private int nAidBox;

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
    private AidBox[] aidboxes;

    /**
     * Constructor for the Route
     *
     * @param totalDistance Distance of the route
     * @param totalDuration Total duration of the route
     */
    public RouteImp(double totalDistance, double totalDuration) {
        this.nAidBox = 0;
        this.totalDuration = totalDistance;
        this.totalDistance = totalDistance;
        this.aidboxes = new AidBox[INICIAL_AIDBOX];
    }

    /**
     * Gets the total distance of the route
     * @return the totalDistance
     */
    @Override
    public double getTotalDistance() {
        return this.totalDistance;
    }

    /**
     * Gets the total duration to travel the route
     * @return the totalDuration
     */
    @Override
    public double getTotalDuration() {
        return this.totalDuration;
    }

    /**
     * Gets the vehicle of the route
     * @return the vehicle
     */
    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Gets the route by each aidbox
     * @return the route
     */
    @Override
    public AidBox[] getRoute() {
        return this.aidboxes;
    }

    /**
     * Checks if the aidbox is already at the array
     *
     * @param aidbox the Aid Box to check
     * @return int - i if aidbox exists in the array , otherwise return -1 if cannot
     * find i
     */
    private int searchAidBox(AidBox aidbox) {
        for (int i = 0; i < this.aidboxes.length; i++) { 
            if (aidbox.equals(this.aidboxes[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the route contains an Aid Box
     * @param aidbox the Aid Box to check
     * @return boolean - true if the route contains the Aid Box, false otherwise
     */
    @Override
    public boolean containsAidBox(AidBox aidbox) {
        return searchAidBox(aidbox) != -1;
    }

    /**
     * Increase the capacity of the array Aidbox[], twice the current size This
     * is creating a temporary array (aux) with the new size, and then copying
     * the old array to the new one, replacing it
     */
    private void expandAidBoxArray() {

        AidBox aux[] = new AidBox[this.aidboxes.length + EXPAND_AIDBOX];

        for (int i = 0; i < this.aidboxes.length; i++) {
            aux[i] = this.aidboxes[i];
        }

        this.aidboxes = aux;
    }

    /**
     * This method checks if a specific aidbox has the specific ItemType
     *
     * @param it The item type reference
     * @param aidbox To check the containers of this aid box
     * @return boolean - true if the aidbox contains at least one "it" (ItemType) 
     * , false otherwise
     */
    private boolean hasContainer(ItemType it, AidBox aidbox) {

        Container[] aux = aidbox.getContainers();
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].getType() == it) {
                return true;
            }
        }
        return false;
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

        if (aidbox == null) {
            throw new RouteException("Cannot add a null aidbox");
        }

        if (searchAidBox(aidbox) != -1) {
            throw new RouteException("Aidbox already at the route");
        }

        if (!hasContainer(this.vehicle.getSupplyType(), aidbox)) {
            throw new RouteException("Aidbox is not compatible with the vehicle designated to the route");
        }

        if (this.nAidBox == this.aidboxes.length) {
            expandAidBoxArray();
        }

        this.aidboxes[this.nAidBox++] = aidbox;
    }

    /**
     * This method removes a aidbox from the route
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

        if (searchAidBox(aidbox) == -1) {
            throw new RouteException("Aidbox does not exist in the route");
        }

        index = searchAidBox(aidbox);

        if (index == -1) {
            return null;
        }

        this.aidboxes[index] = this.aidboxes[this.nAidBox];
        this.aidboxes[this.nAidBox--] = null;
        return aidbox;

    }

    @Override
    public void insertAfter​(AidBox after, AidBox toInsert) throws RouteException {

    }

    /**
     * This method replaces a aidbox for another
     * @param aidbox receives the aidbox already at the route
     * @param aidbox1 the aid box that will substitute the current one
     * @throws RouteException - when aidbox is null , or if the aidbox is not 
     * at the route , or if the aidbox1 is already at the route
     */
    @Override
    public void replaceAidBox(AidBox aidbox, AidBox aidbox1) throws RouteException {
        int index;
        
        
        if (aidbox == null && aidbox1 == null){
            throw new RouteException("Cannot remove a null aidbox");
        }
        
        if (searchAidBox(aidbox) == -1) {
            throw new RouteException("Aidbox does not exist in the route");
        }
        
        if (searchAidBox(aidbox1) != -1){
            throw new RouteException("Aidbox to substitute is already at the route"); 
        }
        
        if (!hasContainer(this.vehicle.getSupplyType(), aidbox1)) {
            throw new RouteException("Aidbox cannot be replaced . Is not compatible with the vehicle designated to the route");
        }
        
        index = searchAidBox(aidbox);
        aidboxes[index] = aidbox1;
        
    }

}
