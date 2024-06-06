package Files;

import Classes.Location;
import Classes.Locations;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImportJSONDISTANCES {

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
            aux.setDistance(((Long) location.get("duration")).doubleValue());
            
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
}