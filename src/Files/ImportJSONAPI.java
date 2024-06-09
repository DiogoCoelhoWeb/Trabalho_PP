/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import Classes.Readings;
import Exception.NullJSONObjectException;
import com.estg.core.Container;
import Implementations.AidBoxImp;
import Implementations.ContainerImp;
import Implementations.GeographicCoordinatesImp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.estg.io.HTTPProvider;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author diogo
 */
public class ImportJSONAPI {

    public ImportJSONAPI() {

    }

    public AidBoxImp[] getAidBoxJSONObjectArray() {

        HTTPProvider provider = new HTTPProvider();
        String jsonString = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes");
        JSONArray jsonArray = parseJsonArray(jsonString);

        if (jsonArray == null) {
            return null;
        }

        AidBoxImp[] aidBoxes = new AidBoxImp[jsonArray.size()];

        // Loop through each object in the jsonArray
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            JSONArray containers = (JSONArray) jsonObject.get("Contentores");
            Container[] contentorArray = new Container[containers.size()];

            for (int j = 0; j < containers.size(); j++) {
                JSONObject container = (JSONObject) containers.get(j);

                contentorArray[j] = new ContainerImp((String) container.get("codigo"), ((Long) container.get("capacidade")).doubleValue(), null);

            }

            aidBoxes[i] = new AidBoxImp((String) jsonObject.get("Codigo"), (String) jsonObject.get("Zona"), null, new GeographicCoordinatesImp((double) jsonObject.get("Latitude"), (double) jsonObject.get("Longitude")), null);
        }
        return aidBoxes;
    }

    public double getDistanceToAidBoxFromJSON(String from, String to) throws NullJSONObjectException {
        HTTPProvider provider = new HTTPProvider();

        String url = "https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=" + from + "&to=" + to;
        String jsonString = provider.getFromURL(url);

        JSONObject jsonObject = parseJsonString(jsonString);

        if (jsonObject == null) {
            throw new NullJSONObjectException("Invalid AidBox in the to or from field");
        }

        return (double) jsonObject.get("distance");
    }

    public double getDurationToAidBoxFromJSON(String from, String to) throws NullJSONObjectException {
        HTTPProvider provider = new HTTPProvider();

        String url = "https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=" + from + "&to=" + to;
        String jsonString = provider.getFromURL(url);

        JSONObject jsonObject = parseJsonString(jsonString);

        if (jsonObject == null) {
            throw new NullJSONObjectException("Invalid AidBox in the to or from field");
        }

        return (double) jsonObject.get("duration");
    }

    public Readings[] getReadingsJSONObjectArray() {
        HTTPProvider provider = new HTTPProvider();
        String jsonString = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/readings");
        JSONArray jsonArray = parseJsonArray(jsonString);

        if (jsonArray == null) {
            return null;
        }

        Readings[] readings = new Readings[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            String dateTimeString = (String) jsonObject.get("data");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeString, formatter);
            LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();

            readings[i] = new Readings(
                    (String) jsonObject.get("contentor"),
                    LocalDateTime.parse((String) jsonObject.get("data")),
                    ((Long) jsonObject.get("valor")).doubleValue()
            );
        }
        return readings;
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
