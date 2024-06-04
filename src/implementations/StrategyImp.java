/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;
import com.estg.core.Institution;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Strategy;
import Classes.RefrigeratedVehicles;
import com.estg.core.ItemType;
import java.time.LocalDate;
/**
 *
 * @author diogo
 */
public class StrategyImp implements Strategy{
    
    private String name;
    private ItemType typeOfRoute;
    
    public StrategyImp(String name, ItemType typeOfRoute){
        this.name = name;
    }
    
    @Override
    public Route[] generate(Institution instn, RouteValidator rv){
        
        AidBoxImp[] auxAidbox = (AidBoxImp[]) instn.getAidBoxes();
        
        for ( int i = 0 ; i < auxAidbox.length; i++ ){
            if(rv.validate(route, aidbox))
        }
        
        
        
        for ( int i = 0 ; i < instn.getAidBoxes().length; i++){
            if (instn.getAidBoxes()[i].getContainer(ItemType.PERISHABLE_FOOD) != null){
                
                if(instn.getAidBoxes()[i].getContainer(ItemType.PERISHABLE_FOOD).getMeasurements(LocalDate.now())[0].getValue() > 0){
                    
                }
                
            }
        }
        
        
    }
}
