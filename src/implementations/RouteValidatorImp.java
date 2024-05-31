/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import com.estg.core.AidBox;
import com.estg.pickingManagement.Route;

/**
 *
 * @author diogo
 */
public class RouteValidatorImp {

    public boolean validate(Route route, AidBox aidbox){
        
        
        if (route == null){
            return false;
        }
        
        for ( int i = 0; i < route.getRoute().length; i++){
            if (aidbox.equals(route.getRoute()[i])){
            return true;
            }
        }
        return false;
    }
}

