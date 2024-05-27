/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import com.estg.core.AidBox;
import com.estg.core.GeographicCoordinates;
import com.estg.core.Container;
import java.util.Objects;

/**
 *
 * @author diogo
 */
public class AidBoxImp implements AidBox {
    
    private final int MAX_CONTAINER = 4;
    
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
     * Constructor for the Equipment
     * 
     * @param code code of the aidbox
     * @param zone zone where is located
     * @param refLocal description of the place in which the Aid Box is installed.
     * @param geographicCoordinates geographicCoordinates of the aidbox 
     * @param container ................................ 
     */
    public AidBoxImp(String code, String zone, String refLocal ,
            GeographicCoordinates geographicCoordinates , Container container ){
        
        this.code = code;
        this.zone = zone;
        this.refLocal = refLocal;
        this.geographicCoordinates = geographicCoordinates;
        this.container = new Container[MAX_CONTAINER]; // to do : Perguntar ao stor sobre a capacidade de contentores
    }
    
    /**
     * Gets the code of the aidbox
     * @return String - the code
     */
    @Override
    public String getCode(){
        return this.code;
    }
    
    /**
     * Gets the zone of the aidbox
     * @return String - the zone
     */
    @Override
    public String getZone(){
        return this.zone;
    }
    
    /**
     * Gets the description of the place in which the aidbox is installed
     * @return String - the refLocal
     */
    @Override
    public String getRefLocal(){
        return this.refLocal;
    }
    
    /**
     * Gets the Geographic Coordinates of the aidbox
     * @return GeographicCoordinates - the geographicCoordinates
     */
    @Override
    public GeographicCoordinates getCoordinates(){
        return this.geographicCoordinates;
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
