/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.AidBox;
import com.estg.core.GeographicCoordinates;
import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import java.util.Objects;

/**
 *
 * @author diogo
 */
public class AidBoxImp implements AidBox {

    /**
     * The expand array constant
     */
    private static int EXPAND_ARRAY = 2;

    /**
     * The number of many enums has in ItemType and inicialize the array
     */
    private int inicializeContainer;

    /**
     * The code of the aidbox
     */
    private String code;

    /**
     * The zone where is located
     */
    private String zone;

    /**
     * The description of the place in which the Aid Box is installed.
     */
    private String refLocal;

    /**
     * The geographicCoordinates where is located the aidbox
     */
    private GeographicCoordinates geographicCoordinates;

    /**
     * The details of the container
     */
    private Container[] container;

    /**
     * The number of containers
     */
    private int nContainers;

    private int distance;
    private int duration;

    /**
     * Constructor for the Equipment
     *
     * @param code code of the aidbox
     * @param zone zone where is located
     * @param refLocal description of the place in which the Aid Box is
     * installed.
     * @param geographicCoordinates geographicCoordinates of the aidbox
     * @param distance
     * @param duration
     */
    public AidBoxImp(String code, String zone, String refLocal,
            GeographicCoordinates geographicCoordinates, int distance, int duration) {

        this.distance = distance;
        this.duration = duration;
        this.code = code;
        this.zone = zone;
        this.refLocal = refLocal;
        this.geographicCoordinates = geographicCoordinates;
        this.inicializeContainer = ItemType.values().length; // vai saber quantas enums tem
        this.container = new Container[inicializeContainer]; //sera necessario expandir dinamicamente , pois podera ter um tipo novo no futuro
        this.nContainers = 0;
    }

    /**
     * Gets the code of the aidbox
     *
     * @return String - the code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the zone of the aidbox
     *
     * @return String - the zone
     */
    @Override
    public String getZone() {
        return this.zone;
    }

    /**
     * Gets the description of the place in which the aidbox is installed
     *
     * @return String - the refLocal
     */
    @Override
    public String getRefLocal() {
        return this.refLocal;
    }

    /**
     * Gets the Geographic Coordinates of the aidbox
     *
     * @return GeographicCoordinates - the geographicCoordinates
     */
    @Override
    public GeographicCoordinates getCoordinates() {
        return this.geographicCoordinates;
    }

    /**
     * Gets the array of the existing containers
     *
     * @return Container[] - a copy of the existing containers
     */
    @Override
    public Container[] getContainers() {
        return this.container;
    }

    /**
     * Gets the container with a specific ItemType
     *
     * @param it - The item type
     * @return Container - the container with the specific itemtype
     */
    @Override
    public Container getContainer(ItemType it) {

        for (int i = 0; i < this.container.length; i++) {
            if (this.container[i].getType() == it) {
                return this.container[i];
            }
        }
        return null;
    }

    /**
     * This method expands the container if necessary
     */
    private void expandContainerArray() {

        Container[] aux = new Container[this.nContainers * EXPAND_ARRAY];

        for (int i = 0; i < this.container.length; i++) {
            aux[i] = this.container[i];
        }

        this.container = aux;
    }

    /**
     * This method adds a container in the array
     *
     * @param cntnr - The new container to add
     * @return boolean - false if already exists the exact same container in the
     * aidbox , false otherwise
     * @throws ContainerException - if the cntnr is null or if already contains
     * inside the aidbox the same item type
     */
    @Override
    public boolean addContainer(Container cntnr) throws ContainerException {

        if (cntnr == null) {
            throw new ContainerException("Container cannot be null");
        }

        if (((ContainerImp) cntnr).checkTypeContainer(this.container, cntnr.getType())) {
            throw new ContainerException("Container type already exist in the aidbox");
        }

        for (int i = 0; i < this.container.length; i++) {
            if (cntnr.equals(this.container[i])) {      // o codigo do container é unico??? se nao for alterar o equals.
                return false;
            }
        }

        if (this.nContainers == this.container.length) {
            expandContainerArray();
        }

        this.container[this.nContainers++] = cntnr;

        return true;

    }

    public double getDistance(){
        return this.distance;
    }
    
    public double getDuration(){
        return this.duration;
    }
    
    /**
     *
     * @param aidbox
     * @return
     * @throws AidBoxException
     */
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        if (aidbox == null) {
            throw new AidBoxException("aidbox cannot be null");
        }
        
        return this.distance - ((AidBoxImp)aidbox).getDistance();
        

    }

    @Override
    public double getDuration(AidBox aidbox) throws AidBoxException {
        
        if (aidbox == null) {
            throw new AidBoxException("aidbox cannot be null");
        }
        
        return this.duration - ((AidBoxImp)aidbox).getDuration();
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AidBoxImp other = (AidBoxImp) obj;
        return Objects.equals(this.geographicCoordinates, other.geographicCoordinates);
    }

}
