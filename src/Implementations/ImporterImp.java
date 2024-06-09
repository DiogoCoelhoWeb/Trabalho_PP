/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementations;

import Classes.Readings;
import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import com.estg.io.Importer;
import Files.ImportJSONAPI;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
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
    public void importData​(Institution institution) throws java.io.FileNotFoundException, java.io.IOException, InstitutionException {

        if (institution == null) {
            throw new InstitutionException("Institution cannot be null");
        }

        ImportJSONAPI importerAPI = new ImportJSONAPI();

        Readings[] readings = new Readings[importerAPI.getReadingsJSONObjectArray().length];
        AidBox[] aidBoxes = new AidBoxImp[importerAPI.getAidBoxJSONObjectArray().length];

        aidBoxes = importerAPI.getAidBoxJSONObjectArray();
        readings = importerAPI.getReadingsJSONObjectArray();

        for (int i = 0; i < aidBoxes.length; i++) {
            try {
                institution.addAidBox(aidBoxes[i]);
            } catch (AidBoxException e) {
                e.getMessage();
            }
        }

        for (int i = 0; i < institution.getAidBoxes().length; i++) {
            for (int j = 0; j < institution.getAidBoxes()[i].getContainers().length; j++) {
                for (int z = 0; z < readings.length; z++) {
                    if (institution.getAidBoxes()[i].getContainers()[j].equals(readings[z].getCodeContainer())) {
                        Measurement aux = new MeasurementImp(readings[z].getValue(), readings[z].getDate());
                        try {
                            institution.getAidBoxes()[i].getContainers()[j].addMeasurement(aux);
                        } catch (MeasurementException e) {
                            e.getMessage();
                        }
                    }
                }
            }

        }
    }

}
