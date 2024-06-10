package Implementations;

import Classes.Readings;
import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import com.estg.io.Importer;
import Files.ImportJSONAPI;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class ImporterImp implements Importer {

    public ImporterImp() {

    }

    @Override
    public void importData(Institution institution) throws java.io.FileNotFoundException, java.io.IOException, InstitutionException {

        if (institution == null) {
            throw new InstitutionException("Institution cannot be null");
        }

        ImportJSONAPI importerAPI = new ImportJSONAPI();

        
        
        try {
            Readings[] readings = new Readings[1000];
            AidBox[] aidBoxes = new AidBoxImp[1000];

            aidBoxes = importerAPI.getAidBoxJSONObjectArray();
            readings = importerAPI.getReadingsJSONObjectArray();

            for (int i = 0; i < aidBoxes.length; i++) {
                for (int j = 0; j < aidBoxes[i].getContainers().length; j++) {
                    for (int z = 0; z < readings.length; z++) {
                        if (aidBoxes[i].getContainers()[j].getCode().equals(readings[z].getCodeContainer())) {
                            Measurement aux = new MeasurementImp(readings[z].getValue(), readings[z].getDate());
                            try {
                                aidBoxes[i].getContainers()[j].addMeasurement(aux);
                            } catch (MeasurementException e) {
                                e.getMessage();
                            }
                        }
                    }
                }

            }

            for ( int i = 0 ; i < aidBoxes.length; i++){
                institution.addAidBox(aidBoxes[i]);
            }
        } catch (ContainerException e) {
            e.printStackTrace();
        } catch (AidBoxException ex) {
            Logger.getLogger(ImporterImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
