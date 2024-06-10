/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Classes.Readings;
import Files.ImportJSONAPI;
import Files.ImportJSONDISTANCES;
import Implementations.AidBoxImp;
import Implementations.ContainerImp;
import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.exceptions.ContainerException;
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
    public static void main(String[] args) throws ParseException, ContainerException {

        ImportJSONAPI b = new ImportJSONAPI();

        Readings[] readings = new Readings[b.getReadingsJSONObjectArray().length];
        
        AidBox[] a = new AidBoxImp[100000];
        a = b.getAidBoxJSONObjectArray();

        
        
        int tamanho = ((AidBoxImp) a[0]).getContainers().length;
        System.out.println("" + readings[0].getValue());

    }
}
