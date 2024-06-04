


/**
 * Corrigir : 
 * 
 * 
 * 
 * 
 */














package Main;

import Classes.Location;
import Classes.Locations;
import com.estg.core.GeographicCoordinates;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import implementations.AidBoxImp;
import implementations.ContainerImp;
import implementations.GeographicCoordinatesImp;
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
    public static void main(String[] args) throws MeasurementException, ContainerException {

        Location location1 = new Location("Base", 4558, 415);
        Location location2 = new Location("CAIXF32", 8558, 715);
        Location location3 = new Location("CAIXF33", 0, 0);
        Location location4 = new Location("CAIXF34", 2426, 337);
        Location location5 = new Location("CAIXF35", 8218, 785);
        Location location6 = new Location("CAIXF36", 6746, 694);
        Location location7 = new Location("CAIXF37", 7754, 798);
        Location location8 = new Location("CAIXF38", 5234, 593);
        Location location9 = new Location("CAIXF39", 3494, 345);
        Location location10 = new Location("CAIXF40", 7691, 647);
        Location location11 = new Location("CAIXF41", 10888, 929);
        Location location12 = new Location("CAIXF42", 9377, 809);
        Location location13 = new Location("CAIXF43", 7867, 665);
        Location location14 = new Location("CAIXF44", 8618, 684);
        Location location15 = new Location("CAIXF45", 11311, 915);
        Location location16 = new Location("CAIXF46", 10956, 1024);
        Location location17 = new Location("CAIXF47", 11126, 917);
        Location location18 = new Location("CAIXF48", 1291, 241);
        Location location19 = new Location("CAIXF49", 7128, 669);
        Location location20 = new Location("CAIXF50", 8813, 941);
        Location location21 = new Location("CAIXF51", 8067, 844);

        Locations locations1 = new Locations("CAIXF33", new Location[]{
            location1, location2, location3, location4, location5, location6, location7, location8, location9, location10,
            location11, location12, location13, location14, location15, location16, location17, location18, location19, location20, location21
        });

        Location location22 = new Location("Base", 7558, 615);
        Location location23 = new Location("CAIXF32", 5104, 564);
        Location location24 = new Location("CAIXF33", 12155, 1059);
        Location location25 = new Location("CAIXF34", 15852, 1125);
        Location location26 = new Location("CAIXF35", 19532, 1426);
        Location location27 = new Location("CAIXF36", 18060, 1335);
        Location location28 = new Location("CAIXF37", 11988, 1049);
        Location location29 = new Location("CAIXF38", 12552, 1132);
        Location location30 = new Location("CAIXF39", 10671, 1049);
        Location location31 = new Location("CAIXF40", 7822, 838);
        Location location32 = new Location("CAIXF41", 9882, 1001);
        Location location33 = new Location("CAIXF42", 8371, 881);
        Location location34 = new Location("CAIXF43", 6861, 737);
        Location location35 = new Location("CAIXF44", 3459, 464);
        Location location36 = new Location("CAIXF45", 620, 209);
        Location location37 = new Location("CAIXF46", 0, 0);
        Location location38 = new Location("CAIXF47", 5724, 593);
        Location location39 = new Location("CAIXF48", 12159, 1051);
        Location location40 = new Location("CAIXF49", 14705, 1267);
        Location location41 = new Location("CAIXF50", 14598, 1388);
        Location location42 = new Location("CAIXF51", 11590, 1031);

        Locations locations2 = new Locations("CAIXF46", new Location[]{
            location22, location23, location24, location25, location26, location27, location28, location29, location30,
            location31, location32, location33, location34, location35, location36, location37, location38, location39, location40, location41, location42
        });

        Locations[] locations = new Locations[]{locations1, locations2};

        MeasurementImp measurement1 = new MeasurementImp(10.5, LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        MeasurementImp measurement2 = new MeasurementImp(25.0, LocalDateTime.of(2022, 6, 15, 14, 30, 0));
        MeasurementImp measurement3 = new MeasurementImp(5.2, LocalDateTime.of(2022, 3, 20, 10, 15, 0));

        // Exemplo 1
        ContainerImp container1 = new ContainerImp("N106", 600, ItemType.NON_PERISHABLE_FOOD);
        ContainerImp container2 = new ContainerImp("V0EK", 900, ItemType.PERISHABLE_FOOD);
        ContainerImp container3 = new ContainerImp("M69E", 500, ItemType.MEDICINE);
        GeographicCoordinatesImp coordinates1 = new GeographicCoordinatesImp(41.336092, -8.239746);
        AidBoxImp aidBox1 = new AidBoxImp("CAIXF33", "Rande", "R", coordinates1, locations);

        // Exemplo 2
        ContainerImp container4 = new ContainerImp("NQE0", 600, ItemType.NON_PERISHABLE_FOOD);
        ContainerImp container5 = new ContainerImp("V9E0", 750, ItemType.PERISHABLE_FOOD);
        ContainerImp container6 = new ContainerImp("MQQY", 900, ItemType.MEDICINE);
        GeographicCoordinatesImp coordinates2 = new GeographicCoordinatesImp(41.322428, -8.145332);
        AidBoxImp aidBox2 = new AidBoxImp("CAIXF46", "Lixa", "R", coordinates2, locations);
        
        try{
        container4.addMeasurement(measurement3);
        }catch(MeasurementException e){
            e.printStackTrace();
        }
        try {
            aidBox2.addContainer(container4);
            aidBox2.addContainer(container5);
            aidBox2.addContainer(container6);
        } catch (ContainerException e) {
            System.err.println("Erro ao adicionar container: " + e.getMessage());
        }
        String s = aidBox2.toString();

        System.out.println(s);
    }

}
