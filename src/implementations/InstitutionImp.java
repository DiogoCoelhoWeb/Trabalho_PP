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
/**
 *
 * @author diogo
 */
public class InstitutionImp implements Institution {
    
    private String name;
    private int nAidBoxes;
    private int nVehicles;
    private int nPickingMaps;
    
    private Vehicle[] vehicles;
    private AidBox[] aidBoxes;
    private PickingMap[] pickingMaps;
    
    // method constructor
    public InstitutionImp(){
        
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public AidBox[] getAidBoxes(){
        return this.aidBoxes;
    }
    
    @Override 
    public Vehicle[] getVehicles(){
        return this.vehicles;
    }
    
    @Override 
    public void disableVehicle(Vehicle vhcl) throws VehicleException{
       
        if (vhcl == null){
            throw new VehicleException("Vehicle cannot be null");
        }
        
    }
}
