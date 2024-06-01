/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import com.estg.core.Measurement;
import implementations.MeasurementImp;
import java.io.FileReader;
import java.time.LocalDateTime;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author diogo
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LocalDateTime now = LocalDateTime.now();
        
        Measurement measurement1 = new MeasurementImp(10.5, now);
        Measurement measurement2 = new MeasurementImp(15.2, now.plusHours(1));
    }
    
}
