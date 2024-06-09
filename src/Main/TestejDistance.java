/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Classes.Readings;
import Files.ImportJSONAPI;
import Files.ImportJSONDISTANCES;
import Implementations.AidBoxImp;
import com.estg.core.AidBox;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author diogo
 */
public class TestejDistance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        ImportJSONAPI b = new ImportJSONAPI();
        
        AidBox[] a = new AidBoxImp[100000];
        a = b.getAidBoxJSONObjectArray();
        
        for(int i = 0 ; i < a.length; i++){
            System.out.println(((AidBoxImp)a[i]).toString());
        
        }
        
        
        Readings[] c = new Readings[10000];
        c = b.getReadingsJSONObjectArray();
        
        for(int i = 0 ; i < c.length; i++){
            System.out.println(c[i].toString());
        
        }
    }
}