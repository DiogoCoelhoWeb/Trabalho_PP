/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import Classes.Location;
import Classes.Locations;
import com.estg.core.Measurement;
import com.estg.core.Container;
import Implementations.AidBoxImp;
import Implementations.ContainerImp;
import Implementations.GeographicCoordinatesImp;
import java.time.LocalDateTime;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
                null,
                new GeographicCoordinatesImp((double) jsonObject.get("Latitude"), (double) jsonObject.get("Longitude")),
                new Locations[0] // falta adicionar as localizaçoes
        );

        JSONArray Containers = (JSONArray) jsonObject.get("Contentores");
        Container[] contentorArray = new Container[Containers.size()];
        for (int j = 0; j < Containers.size(); j++) {
            JSONObject container = (JSONObject) Containers.get(j);
            Container aux = new ContainerImp(
                    (String) container.get("codigo"),
                    ((Long) container.get("capacidade")).doubleValue(),
                    null);

            ((ContainerImp) aux).setCode((String) container.get("codigo"));
            ((ContainerImp) aux).setMaxCapacity(((Long) container.get("capacidade")).doubleValue());
            contentorArray[j] = aux;
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

    public JSONObject getDistancesJSONObject(String jsonString) {

        JSONObject jsonObject = parseJsonString(jsonString);
        if (jsonObject == null) {
            return null;
        }

        Locations from = new Locations(
                (String) jsonObject.get("from")
        );

        JSONArray to = (JSONArray) jsonObject.get("to");
        Location[] locationArray = new Location[to.size()];
        for (int j = 0; j < to.size(); j++) {
            JSONObject location = (JSONObject) to.get(j);
            Location aux = new Location(
                    (String) location.get("name"),
                    ((Long) location.get("distance")).doubleValue(),
                    ((Long) location.get("duration")).doubleValue());

            aux.setDistance(((Long) location.get("distance")).doubleValue());
            aux.setDuration(((Long) location.get("duration")).doubleValue());

            locationArray[j] = aux;
        }
        from.setLocation(locationArray);

        JSONObject distancesJson = new JSONObject();
        distancesJson.put("from", from.getNameFrom());

        JSONArray toLocations = new JSONArray();
        for (int i = 0; i < from.getLocationTo().length; i++) {
            Location location = from.getLocationTo()[i];
            JSONObject locationJson = new JSONObject();
            locationJson.put("name", location.getName());
            locationJson.put("distance", location.getDistance());
            locationJson.put("duration", location.getDuration());
            toLocations.add(locationJson);
        }
        distancesJson.put("to", toLocations);

        return distancesJson;
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

    public JSONArray parseJsonArray(String jsonString) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONArray) parser.parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
