/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.Vehicle;
/**
 *
 * @author diogo
 */
public class InstitutionImp implements Institution {
    
    private String name;
    private VehicleImp[] vehicles;
    private AidBoxImp[] aidBox;
    
    // method constructor
    public InstitutionImp(){
        
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public AidBox[] getAidBoxes(){
        return this.aidBox;
    }
    
    @Override 
    public Vehicle[] getVehicles(){
        return this.vehicles;
    }
    
    @Override 
    public void disableVehicle(Vehicle vhcl) throws VehicleException{
       
    }
}
