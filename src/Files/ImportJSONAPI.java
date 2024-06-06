/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import Classes.Location;
import Classes.Locations;
import Classes.Readings;
import com.estg.core.Container;
import implementations.AidBoxImp;
import implementations.ContainerImp;
import implementations.GeographicCoordinatesImp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.estg.io.HTTPProvider;
import java.time.LocalDateTime;

/**
 *
 * @author diogo
 */
public class ImportJSONAPI {

    public JSONObject[] getAidBoxJSONObjectArray() {
        HTTPProvider provider = new HTTPProvider();
        String jsonString = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes");
        JSONArray jsonArray = parseJsonArray(jsonString);
        if (jsonArray == null) {
            return null;
        }

        JSONObject[] aidBoxJsonArray = new JSONObject[jsonArray.size()];

        // Loop through each object in the jsonArray
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            AidBoxImp aidBox = new AidBoxImp(
                    (String) jsonObject.get("Codigo"),
                    (String) jsonObject.get("Zona"),
                    null,
                    new GeographicCoordinatesImp((double) jsonObject.get("Latitude"), (double) jsonObject.get("Longitude")),
                    new Locations[0] // falta adicionar as localizaçoes
            );

            JSONArray containers = (JSONArray) jsonObject.get("Contentores");
            Container[] contentorArray = new Container[containers.size()];
            for (int j = 0; j < containers.size(); j++) {
                JSONObject container = (JSONObject) containers.get(j);
                Container aux = new ContainerImp(
                        (String) container.get("codigo"),
                        ((Long) container.get("capacidade")).doubleValue(),
                        null
                );

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
            aidBoxJson.put("Latitude", aidBox.getCoordinates().getLatitude());
            aidBoxJson.put("Longitude", aidBox.getCoordinates().getLongitude());

            JSONArray contentoresJson = new JSONArray();
            for (Container contentor : aidBox.getContainers()) {
                JSONObject contJson = new JSONObject();
                contJson.put("codigo", contentor.getCode());
                contJson.put("capacidade", ((ContainerImp) contentor).getMaxCapacity());
                contentoresJson.add(contJson);
            }
            aidBoxJson.put("Contentores", contentoresJson);

            aidBoxJsonArray[i] = aidBoxJson;
        }
        return aidBoxJsonArray;
    }

    public JSONObject[] getDistancesJSONObject(String from, String to) {
        HTTPProvider provider = new HTTPProvider();
        String url = "https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=" + from + "&to=" + to;
        String jsonString = provider.getFromURL(url);
        JSONObject jsonObject = parseJsonString(jsonString);
        if (jsonObject == null) {
            return null;
        }

        JSONArray to1 = (JSONArray) jsonObject.get("to");
        JSONObject[] locationJsonArray = new JSONObject[to1.size()];
        for (int j = 0; j < to1.size(); j++) {
            JSONObject location = (JSONObject) to1.get(j);
            Location aux = new Location(
                    (String) location.get("name"),
                    ((Long) location.get("distance")).doubleValue(),
                    ((Long) location.get("duration")).doubleValue());

            aux.setDistance(((Long) location.get("distance")).doubleValue());
            aux.setDuration(((Long) location.get("duration")).doubleValue());

            // Convert Location object back to JSONObject
            JSONObject locationJson = new JSONObject();
            locationJson.put("name", aux.getName());
            locationJson.put("distance", aux.getDistance());
            locationJson.put("duration", aux.getDuration());

            locationJsonArray[j] = locationJson;
        }

        return locationJsonArray;
    }

    public JSONObject[] processContainerData() {
        HTTPProvider provider = new HTTPProvider();
        String url = "https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=";
        String jsonString = provider.getFromURL(url);

        // Parse JSON string into JSONArray
        JSONObject jsonObject = parseJsonString(jsonString);
        if (jsonObject == null) {
            return null;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ContainerData containerData = new ContainerData(
                    jsonObject.getString("_id"),
                    jsonObject.getString("contentor"),
                    jsonObject.getString("data"),
                    jsonObject.getInt("valor")
            );

            // Convert ContainerData object back to JSONObject
            JSONObject containerJson = new JSONObject();
            containerJson.put("_id", containerData.getId());
            containerJson.put("contentor", containerData.getContentor());
            containerJson.put("data", containerData.getData());
            containerJson.put("valor", containerData.getValor());

            containerJsonArray[i] = containerJson;
        }

        return containerJsonArray;
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
