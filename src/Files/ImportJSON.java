/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import Classes.Locations;
import com.estg.core.Container;
import implementations.AidBoxImp;
import implementations.ContainerImp;
import implementations.GeographicCoordinatesImp;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author diogo
 */
public class ImportJSON {

    public JSONObject getAidBoxJSONObject(String jsonString) {
        JSONObject jsonObject = parseJsonString(jsonString);
        if (jsonObject == null) {
            return null;
        }

        AidBoxImp aidBox = new AidBoxImp(
                (String) jsonObject.get("Codigo"),
                (String) jsonObject.get("Zona"),
                null, // refLocal is not provided in the JSON
                new GeographicCoordinatesImp((double) jsonObject.get("Latitude"), (double) jsonObject.get("Longitude")),
                new Locations[0] // locations is not provided in the JSON
        );

        JSONArray contentores = (JSONArray) jsonObject.get("Contentores");
        Container[] contentorArray = new Container[contentores.size()];
        for (int j = 0; j < contentores.size(); j++) {
            JSONObject cont = (JSONObject) contentores.get(j);
            Container contentor = new ContainerImp(
                    (String) cont.get("codigo"),
                    ((Long) cont.get("capacidade")).doubleValue(),
                    null);

            ((ContainerImp) contentor).setCode((String) cont.get("codigo"));
            ((ContainerImp) contentor).setMaxCapacity(((Long) cont.get("capacidade")).doubleValue());
            contentorArray[j] = contentor;
        }
        aidBox.setContainers(contentorArray);

        // Convert AidBoxImp object back to JSONObject
        JSONObject aidBoxJson = new JSONObject();
        aidBoxJson.put("Codigo", aidBox.getCode());
        aidBoxJson.put("Zona", aidBox.getZone());
        aidBoxJson.put("RefLocal", aidBox.getRefLocal());
        aidBoxJson.put("Latitude", aidBox.getCoordinates().getLatitude()); // Assuming GeographicCoordinatesImp has getLatitude and getLongitude methods
        aidBoxJson.put("Longitude", aidBox.getCoordinates().getLongitude());

        JSONArray contentoresJson = new JSONArray();
        for (int i = 0; i < aidBox.getContainers().length; i++) {
            Container contentor = aidBox.getContainers()[i];
            JSONObject contJson = new JSONObject();
            contJson.put("codigo", contentor.getCode());
            contJson.put("capacidade", ((ContainerImp) contentor).getMaxCapacity());
            contentoresJson.add(contJson);
        }
        aidBoxJson.put("Contentores", contentoresJson);

        return aidBoxJson;
    }

    private JSONObject parseJsonString(String jsonString) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
